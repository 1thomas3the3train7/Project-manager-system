package com.example.userservice.Model.Role;

import com.example.userservice.Model.User.ShortUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class ShortRole extends BaseRole {
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_role",joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<ShortUser> users;
}
