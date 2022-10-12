package com.example.authservice;

import com.example.authservice.DTO.UserDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class DtoUtils {
    private final static Gson gson = new Gson();

    public UserDTO toUserDTO(final String req){
        try {
            return gson.fromJson(req, UserDTO.class);
        } catch (Exception exc){
            System.out.println("Illegal exc");
            return null;
        }
    }
}
