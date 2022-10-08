package com.example.authservice.Config;

import com.example.authservice.Service.JwtAuth;
import com.example.authservice.Service.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtFilter implements WebFilter {
    private final JwtUtils jwtUtils;

    public JwtFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        final String token = getBearerToken(exchange);
        if(token != null && jwtUtils.validateAccessToken(token)){
            final Claims claims = jwtUtils.getAccessClaims(token);
            final JwtAuth jwtAuth = jwtUtils.generate(claims);

            if(jwtAuth.getRoles() == null){
                return chain.filter(exchange);
            }

            jwtAuth.setAuthenticated(true);
            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(jwtAuth));

        }
        return chain.filter(exchange);
    }
    private String getBearerToken(ServerWebExchange exchange){
        final String[] token = exchange.getRequest().getHeaders().get("Authorization").get(0).split(" ");
        if(token[0].equals("Bearer")){
            return token[1];
        }
        return null;
    }
}
