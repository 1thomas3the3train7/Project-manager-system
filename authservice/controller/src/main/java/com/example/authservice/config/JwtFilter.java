package com.example.authservice.config;

import com.example.authservice.JwtAuth;
import com.example.authservice.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtFilter implements WebFilter {
    private final JwtUtils jwtUtils;

    public JwtFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        System.out.println(exchange.getRequest().getPath());
        System.out.println(exchange.getRequest().getHeaders().get("Bearer"));
        final String token = exchange.getRequest().getHeaders().get("Bearer").get(0);
        if (token != null && jwtUtils.validateAccessToken(token)) {
            final Claims claims = jwtUtils.getAccessClaims(token);
            System.out.println(claims);
            final JwtAuth jwtAuth = jwtUtils.generate(claims);
            jwtAuth.setAuthenticated(true);
            System.out.println(jwtAuth);
        }
        return chain.filter(exchange);
    }
}
