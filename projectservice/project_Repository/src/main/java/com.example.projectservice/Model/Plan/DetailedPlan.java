package com.example.projectservice.Model.Plan;

import com.example.projectservice.Model.Project.DetailedProject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "plan")
public class DetailedPlan extends BasePlan {
    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
    @OneToOne(mappedBy = "plan")
    private DetailedProject project;

    public DetailedPlan(String description) {
        super(description);
    }
}
