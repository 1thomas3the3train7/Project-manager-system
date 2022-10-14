package com.example.projectservice.Model.Passport;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@Table(name = "passport")
public class BasePassport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Date dateStart;
    private Date dateEnd;

    public BasePassport(String description, Date dateStart, Date dateEnd) {
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
}
