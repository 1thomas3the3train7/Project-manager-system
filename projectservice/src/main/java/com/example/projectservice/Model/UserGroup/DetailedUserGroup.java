package com.example.projectservice.Model.UserGroup;

import com.example.projectservice.Model.Task.DetailedTask;
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
@Table(name = "user_group")
public class DetailedUserGroup extends UserGroup {
    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_and_group",joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<DetailedTask> tasks;
}
