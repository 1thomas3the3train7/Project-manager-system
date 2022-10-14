package com.example.projectservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
    private String name;
    private String description;
    private UserDTO user;
    private UserDTO[] users;
    private CostDTO costDTO;
}
