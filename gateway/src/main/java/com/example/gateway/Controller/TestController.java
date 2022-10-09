package com.example.gateway.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {
    @PostMapping(value = "/test")
    public Mono<String> test(){
        return Mono.just("test");
    }
}
