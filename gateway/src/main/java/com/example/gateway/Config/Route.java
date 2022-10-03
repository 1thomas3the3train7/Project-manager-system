package com.example.gateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                .build();
    }
}
