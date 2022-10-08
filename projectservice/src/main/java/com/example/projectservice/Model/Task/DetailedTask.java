package com.example.projectservice.Model.Task;

import com.example.projectservice.Model.Cost.DetailedCost;
import com.example.projectservice.Model.User.DetailedUsers;
import com.example.projectservice.Model.UserGroup.DetailedUserGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "task")
public class DetailedTask extends BaseTask {
    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_and_user",joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<DetailedUsers> users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "task_and_cost",joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "cost_id"))
    private DetailedCost cost;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_and_group",joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<DetailedUserGroup> userGroups;
}
