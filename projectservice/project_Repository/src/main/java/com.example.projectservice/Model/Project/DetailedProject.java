package com.example.projectservice.Model.Project;

import com.example.projectservice.Model.Budget.DetailedBudget;
import com.example.projectservice.Model.Passport.DetailedPassport;
import com.example.projectservice.Model.Plan.DetailedPlan;
import com.example.projectservice.Model.User.DetailedUsers;
import com.example.projectservice.Model.User.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id",referencedColumnName = "id")
    private DetailedPassport passport;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id",referencedColumnName = "id")
    private DetailedPlan plan;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id",referencedColumnName = "id")
    private DetailedBudget budget;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private DetailedUsers users;
}
