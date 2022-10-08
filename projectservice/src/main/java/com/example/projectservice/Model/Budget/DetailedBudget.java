package com.example.projectservice.Model.Budget;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "budget")
@Entity
public class DetailedBudget extends BaseBudget {
    @UpdateTimestamp
    private Date dateUpdate;
    @CreationTimestamp
    private Date dateCreate;
}
