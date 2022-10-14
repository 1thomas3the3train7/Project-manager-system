package com.example.projectservice.Model.User;

import com.example.projectservice.Model.Project.DetailedProject;
import com.example.projectservice.Model.Task.DetailedTask;
import com.example.projectservice.Model.UserGroup.DetailedUserGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class DetailedUsers extends Users {
    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_and_user",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<DetailedTask> tasks;
    @OneToOne(mappedBy = "users")
    private DetailedProject project;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_group",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<DetailedUserGroup> userGroups;
}
