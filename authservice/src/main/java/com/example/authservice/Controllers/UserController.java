package com.example.authservice.Controllers;

import com.example.authservice.DTO.JwtDTO;
import com.example.authservice.Repository.RedisRepository;
import com.example.authservice.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;

@RestController
public class UserController {
    private final UserService userService;
    private final RedisRepository redisRepository;

    public UserController(UserService userService, RedisRepository redisRepository) {
        this.userService = userService;
        this.redisRepository = redisRepository;
    }

    @GetMapping(value = "/users/get")
    public String getUser(){
        ReactiveSecurityContextHolder.getContext().subscribe(System.out::println);
        return userService.getUserByEmail("email");
    }
    @PostMapping(value = "/users/login")
    public Mono<JwtDTO> login(@RequestBody String request){
        return Mono.just(userService.loginUser(request));
    }
    @PostMapping(value = "/admin")
    public Mono<String> admin(Principal principal){
        System.out.println(principal.getName());
        System.out.println("asdasd");
        return Mono.just("admin");
    }
    @PostMapping(value = "/admin1")
    public Mono<String> admin1(){
        System.out.println("admin1");
        return Mono.just("admin1");
    }
    @PostMapping(value = "/auth")
    public Mono<String> auth(){
        return Mono.just("auth");
    }
    @PostMapping(value = "/getrefreshtoken")
    public Mono<JwtDTO> getAccessToken(@RequestBody String request){
        return Mono.just(userService.getAccessAndRefreshToken(request));
    }
    @PostMapping(value = "/register")
    public Mono<String> register(@RequestBody String request){
        return Mono.just(userService.registerAndValid(request));
    }
    @PostMapping(value = "/mono")
    public Mono<JwtDTO> getw(){
        return Mono.fromCallable(() -> {
            JwtDTO jwtDTO = new JwtDTO();
            /*jwtDTO.setAccessToken(redisRepository.get("key1").subscribe());*/
            jwtDTO.setRefreshToken("refresh");
            return jwtDTO;
        });
    }
}
