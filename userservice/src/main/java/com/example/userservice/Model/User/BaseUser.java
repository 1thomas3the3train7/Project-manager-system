package com.example.userservice.Model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private boolean banned;
    //TODO enable and banned logic
    public BaseUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public BaseUser(String email, String password, String firstName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
    }
}
