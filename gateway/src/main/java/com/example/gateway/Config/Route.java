package com.example.gateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class Route {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(r -> r
                        .path("/user/get")
                        .filters(f -> f.setPath("/users/get"))
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/login")
                        .filters(f -> f.setPath("/users/login"))
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/admin")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/admin1")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/auth")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/getrefreshtoken")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/mono")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/register")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/test2")
                        .uri("lb://authservice"))
                .build();
    }
}
