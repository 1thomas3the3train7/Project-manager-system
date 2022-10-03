package com.example.authservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDTO {
    private Integer id;
    private String name;

    public RoleDTO(String name) {
        this.name = name;
    }
}
