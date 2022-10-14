package com.example.authservice.Config;

import com.example.authservice.JwtAuth;
import com.example.authservice.JwtUtils;
import io.jsonwebtoken.Claims;
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
        try {
            final String token = getBearerToken(exchange);
            System.out.println(exchange.getRequest().getPath());
            System.out.println(exchange.getRequest().getHeaders());
            System.out.println(token);
            System.out.println(token == null);
            if(token == null){
                System.out.println("token null");
                return chain.filter(exchange);
            }
            if(jwtUtils.validateAccessToken(token)){
                final Claims claims = jwtUtils.getAccessClaims(token);
                final JwtAuth jwtAuth = jwtUtils.generate(claims);

                if(jwtAuth.getRoles() == null){
                    System.out.println("f roles");
                    return chain.filter(exchange);
                }

                jwtAuth.setAuthenticated(true);
                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(jwtAuth));

            }
            System.out.println("end");
            return chain.filter(exchange);
        } catch (NullPointerException n){
            n.printStackTrace();
            System.out.println("null exc in filter");
            return chain.filter(exchange);
        }
    }
    private String getBearerToken(ServerWebExchange exchange){
        try{
            final List<String> tok = exchange.getRequest().getHeaders().get("Authorization");
            System.out.println(tok);
            if(tok == null){
                return null;
            }
            final String[] token = tok.get(0).split(" ");
            if(token[0].equals("Bearer")){
                return token[1];
            }
            return null;
        } catch (NullPointerException n){
            n.printStackTrace();
            return null;
        }
    }
}
