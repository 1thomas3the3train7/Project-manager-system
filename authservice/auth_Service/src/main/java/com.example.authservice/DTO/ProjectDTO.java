package com.example.authservice.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectDTO {
    private String dateStart;
    private String dateEnd;
    private String name;
    private String description;
    private String planDescription;
    private String customer;
    private int budgetMoney;
}
