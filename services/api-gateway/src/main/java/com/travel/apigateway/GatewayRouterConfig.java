package com.travel.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class GatewayRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> userServiceRoutes() {
        return route("user-service")
                .route(path("/user/**"), http())
                .route(path("/login"), http())
                .filter(lb("user-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> myScheduleServiceRoutes() {
        return route("myschedule-service")
                .route(path("/myschedule/**"), http())
                .filter(lb("myschedule-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> placeServiceRoutes() {
        return route("place-service")
                .route(path("/list/**"), http())
                .route(path("/leisureListAjax"), http())
                .route(path("/restaurantListAjax"), http())
                .route(path("/stayListAjax"), http())
                .filter(lb("place-service"))
                .build();
    }
}
