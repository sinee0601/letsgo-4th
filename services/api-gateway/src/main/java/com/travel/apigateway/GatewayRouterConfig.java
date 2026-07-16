package com.travel.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class GatewayRouterConfig {
    @Bean
    public RouterFunction<ServerResponse> userRoutes(){
        return route("user-service")
                .GET("/users/**", http())
                .before(uri("http://localhost:7001"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> itemRoutes(){
        return route("item-service")
                .GET("/items/**", http())
                .before(uri("http://localhost:7002"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderRoutes(){
        return route("order-service")
                .GET("/orders/**", http())
                .before(uri("http://localhost:7003"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> chatRoutes(){
        return route("chatbot-service")
                .GET("/chat/**", http())
                .before(uri("http://localhost:8000"))
                .build();
    }
}
