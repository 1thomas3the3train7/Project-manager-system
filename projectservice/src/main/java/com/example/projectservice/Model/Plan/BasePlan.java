package com.example.projectservice.Model.Plan;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@Table(name = "plan")
@NoArgsConstructor
public class BasePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
}
