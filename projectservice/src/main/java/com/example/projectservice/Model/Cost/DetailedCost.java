package com.example.projectservice.Model.Cost;

import com.example.projectservice.Model.Budget.DetailedBudget;
import com.example.projectservice.Model.Task.DetailedTask;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cost")
public class DetailedCost extends BaseCost {
    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "budget_and_cost",joinColumns = @JoinColumn(name = "cost_id"),
            inverseJoinColumns = @JoinColumn(name = "budget_id"))
    private DetailedBudget budget;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_and_cost",joinColumns = @JoinColumn(name = "cost_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<DetailedTask> tasks;
}
