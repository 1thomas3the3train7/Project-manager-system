package com.example.authservice.controller;

import com.example.authservice.UserService;
import com.example.authservice.dto.JwtDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/get")
    public String getUser(){
        return userService.getUserByEmail("email");
    }
    @PostMapping(value = "/users/login")
    public ResponseEntity<JwtDTO> login(@RequestBody String request){
        return ResponseEntity.ok(userService.loginUser(request));
    }
}
