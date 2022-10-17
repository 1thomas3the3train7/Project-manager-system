package com.example.projectservice.Model.Project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Table(name = "project")
@Getter
@Setter
@NoArgsConstructor
public class BaseProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String customer;

    public BaseProject(String name, String customer) {
        this.name = name;
        this.customer = customer;
    }
}
