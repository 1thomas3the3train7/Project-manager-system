package com.example.projectservice.Model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private int userService_id;
    private String firstName;

    public User(String email, int userService_id) {
        this.email = email;
        this.userService_id = userService_id;
    }
}
