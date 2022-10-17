package com.example.projectservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDTO {
    private String dateStart;
    private String dateEnd;
    private String name;
    private String description;
    private String planDescription;
    private String customer;
    private PassportDTO passportDTO;
    private int budgetMoney;
}
