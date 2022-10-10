package com.example.authservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String email;
    @JsonIgnore
    private String password;
    private RoleDTO[] roles;
}
