package com.example.projectservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int project_id;
    private String email;
    private String firstName;
    //id from UserService
    private int user_id;
}
