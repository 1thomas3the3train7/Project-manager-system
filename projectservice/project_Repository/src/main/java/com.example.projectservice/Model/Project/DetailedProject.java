package com.example.projectservice.Model.Project;

import com.example.projectservice.Model.Budget.DetailedBudget;
import com.example.projectservice.Model.Passport.DetailedPassport;
import com.example.projectservice.Model.Plan.DetailedPlan;
import com.example.projectservice.Model.User.DetailedUser;
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
@Table(name = "project")
public class DetailedProject extends BaseProject {
    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "passport_id",referencedColumnName = "id")
    private DetailedPassport passport;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "plan_id",referencedColumnName = "id")
    private DetailedPlan plan;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "budget_id",referencedColumnName = "id")
    private DetailedBudget budget;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_and_user",joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<DetailedUser> users;

    public DetailedProject(String name, String customer) {
        super(name, customer);
    }
}
