package com.example.userservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private boolean banned;
    private RoleDTO[] roles;
    private int project_id;
    private int user_id;

    public UserDTO(String email, String password, String firstName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
    }

    public UserDTO(int id, String email, String password, String firstName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
    }

    public UserDTO(int id, String email, String password, String firstName,
                   String lastName, boolean enabled, boolean banned, RoleDTO[] roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.banned = banned;
        this.roles = roles;
    }
}
