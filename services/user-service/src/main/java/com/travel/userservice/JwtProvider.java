package com.travel.userservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.private-key}")
    private String privateKeyBase64;

    @Value("${jwt.access-token-validity-ms}")
    private long validityMs;

    private Algorithm algorithm;

    @PostConstruct
    void init() throws Exception {
        byte[] der = Base64.getDecoder().decode(privateKeyBase64);
        RSAPrivateKey privateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(der));
        algorithm = Algorithm.RSA256(null, privateKey);
    }

    public String createToken(UserEntity user) {
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(user.getUserId())
                .withClaim("uid", user.getUserEncodedId())
                .withClaim("username", user.getName())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plusMillis(validityMs)))
                .sign(algorithm);
    }
}
