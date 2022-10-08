package com.example.projectservice.Model.Plan;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "plan")
@NoArgsConstructor
public class DetailedPlan extends BasePlan {
    @UpdateTimestamp
    private Date dateUpdate;
    @CreationTimestamp
    private Date dateCreate;
}
