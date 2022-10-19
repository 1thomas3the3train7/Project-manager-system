package com.example.authservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchDTO {
    private String email;
    private String name;
    private int page;
    private int age;
}
