package com.example.authservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoleDTO {
    private Integer id;
    private String name;

    public RoleDTO(String name) {
        this.name = name;
    }

}
