package com.example.projectservice.Model.Project;

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
@NoArgsConstructor
@Table(name = "project")
public class DetailedProject extends BaseProject{
    @UpdateTimestamp
    private Date dateUpdate;
    @CreationTimestamp
    private Date dateCreate;
}
