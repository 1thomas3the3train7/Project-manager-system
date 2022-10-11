package com.example.userservice.Model.Role;

import com.example.userservice.Model.User.DetailedUser;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class DetailedRole extends BaseRole {
    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_role", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<DetailedUser> users;
}
