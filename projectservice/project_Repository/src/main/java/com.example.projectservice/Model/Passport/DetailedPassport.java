package com.example.projectservice.Model.Passport;

import com.example.projectservice.Model.Project.DetailedProject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "passport")
public class DetailedPassport extends BasePassport {
    public DetailedPassport(String description, Date dateStart, Date dateEnd) {
        super(description, dateStart, dateEnd);
    }

    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
    @OneToOne(mappedBy = "passport")
    private DetailedProject project;
}
