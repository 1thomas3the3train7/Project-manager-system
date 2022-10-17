package com.example.projectservice.Service;

import com.example.projectservice.DTO.ProjectDTO;
import com.example.projectservice.Model.Passport.DetailedPassport;
import com.example.projectservice.Repository.PassportRepository;
import com.example.projectservice.Utils.UtilsDto;
import com.example.projectservice.Validate.DateParse;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class PassportService {
    private final UtilsDto utilsDto;
    private final DateParse date;
    private final PassportRepository passportRepository;

    public PassportService(UtilsDto utilsDto, DateParse date, PassportRepository passportRepository) {
        this.utilsDto = utilsDto;
        this.date = date;
        this.passportRepository = passportRepository;
    }

    public DetailedPassport createPassport(final ProjectDTO projectDTO){
        final DetailedPassport detailedPassport = new DetailedPassport(projectDTO.getDescription());
        try {
            final Date dateStart = date.parseStandartFormat(projectDTO.getDateStart());
            detailedPassport.setDateStart(dateStart);
            if(projectDTO.getDateEnd() != null){
                final Date dateEnd = date.parseStandartFormat(projectDTO.getDateEnd());
                detailedPassport.setDateEnd(dateEnd);
            }
        } catch (ParseException p){
            p.printStackTrace();
            System.out.println("parse exc");
        }
        return detailedPassport;
    }
}
