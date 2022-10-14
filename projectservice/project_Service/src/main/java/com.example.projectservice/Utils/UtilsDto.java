package com.example.projectservice.Utils;

import com.example.projectservice.DTO.PassportDTO;
import com.example.projectservice.Model.Passport.BasePassport;
import com.example.projectservice.Model.Passport.DetailedPassport;
import org.springframework.stereotype.Service;

@Service
public class UtilsDto {
    public PassportDTO PassportEntityToDto(final BasePassport basePassport){
        return new PassportDTO(basePassport.getDescription(),
                basePassport.getDateStart(),basePassport.getDateEnd());
    }
    public DetailedPassport DtoToPassportEntity(final PassportDTO passportDTO){
        return new DetailedPassport(passportDTO.getDescription(),
                passportDTO.getDateStart(),passportDTO.getDateEnd());
    }
}
