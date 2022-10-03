package com.example.authservice.Controllers;

import com.example.authservice.DTO.JwtDTO;
import com.example.authservice.DTO.UserDTO;
import com.example.authservice.Service.UserService;
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
