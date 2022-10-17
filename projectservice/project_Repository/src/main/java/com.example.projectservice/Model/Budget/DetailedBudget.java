package com.example.projectservice.Model.Budget;

import com.example.projectservice.Model.Cost.DetailedCost;
import com.example.projectservice.Model.Project.DetailedProject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "budget")
@Entity
public class DetailedBudget extends BaseBudget {
    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
    @OneToOne(mappedBy = "budget")
    private DetailedProject project;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "budget_and_cost",joinColumns = @JoinColumn(name = "budget_id"),
            inverseJoinColumns = @JoinColumn(name = "cost_id"))
    private List<DetailedCost> costs;

    public DetailedBudget(Integer money) {
        super(money);
    }
}
