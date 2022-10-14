package com.example.projectservice.Service;

import com.example.projectservice.DTO.PassportDTO;
import com.example.projectservice.Model.Passport.DetailedPassport;
import com.example.projectservice.Utils.UtilsDto;
import org.springframework.stereotype.Service;

@Service
public class PassportService {
    private final UtilsDto utilsDto;

    public PassportService(UtilsDto utilsDto) {
        this.utilsDto = utilsDto;
    }

    public DetailedPassport createPassport(final PassportDTO passportDTO){
        return utilsDto.DtoToPassportEntity(passportDTO);
    }
}
