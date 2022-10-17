package com.example.userservice.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSearchDTO {
    private String email;
    private String name;
    private int page;
    private int age;
}
