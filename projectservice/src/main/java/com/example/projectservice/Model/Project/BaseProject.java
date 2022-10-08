package com.example.projectservice.Model.Project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@Table(name = "project")
@NoArgsConstructor
public class BaseProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
