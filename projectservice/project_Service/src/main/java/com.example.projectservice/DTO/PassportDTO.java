package com.example.projectservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PassportDTO {
    private String description;
    private Date dateStart;
    private Date dateEnd;
    public PassportDTO(String description, Date dateStart, Date dateEnd) {
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
}
