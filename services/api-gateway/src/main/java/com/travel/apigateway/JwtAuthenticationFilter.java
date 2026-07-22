package com.travel.apigateway;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String USER_HEADER = "X-User-Id";
    private static final AntPathMatcher MATCHER = new AntPathMatcher();
    private static final List<String> PUBLIC_PATHS = List.of(
            "/login", "/user/**", "/place/**");

    @Value("${jwt.public-key}")
    private String publicKeyBase64;

    private Algorithm algorithm;

    @PostConstruct
    void init() throws Exception {
        byte[] der = Base64.getDecoder().decode(publicKeyBase64);
        RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(der));
        algorithm = Algorithm.RSA256(publicKey, null);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        String uid = resolveUserId(request.getHeader(HttpHeaders.AUTHORIZATION));

        if (uid == null && !isPublic(request.getRequestURI())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\":\"인증이 필요합니다.\"}");
            return;
        }

        chain.doFilter(new IdentityRequestWrapper(request, uid), response);
    }

    private String resolveUserId(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        try {
            DecodedJWT jwt = JWT.require(algorithm).build().verify(authHeader.substring(7));
            return jwt.getClaim("uid").asString();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private boolean isPublic(String uri) {
        return PUBLIC_PATHS.stream().anyMatch(pattern -> MATCHER.match(pattern, uri));
    }

    static class IdentityRequestWrapper extends HttpServletRequestWrapper {
        private final String uid;

        IdentityRequestWrapper(HttpServletRequest request, String uid) {
            super(request);
            this.uid = uid;
        }

        @Override
        public String getHeader(String name) {
            if (USER_HEADER.equalsIgnoreCase(name)) {
                return uid;
            }
            return super.getHeader(name);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            if (USER_HEADER.equalsIgnoreCase(name)) {
                return uid == null ? Collections.emptyEnumeration() : Collections.enumeration(List.of(uid));
            }
            return super.getHeaders(name);
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            List<String> names = new ArrayList<>(Collections.list(super.getHeaderNames()));
            names.removeIf(USER_HEADER::equalsIgnoreCase);
            if (uid != null) {
                names.add(USER_HEADER);
            }
            return Collections.enumeration(names);
        }
    }
}
