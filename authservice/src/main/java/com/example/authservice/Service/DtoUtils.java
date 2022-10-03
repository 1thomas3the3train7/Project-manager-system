package com.example.authservice.Service;

import com.example.authservice.DTO.UserDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class DtoUtils {
    private final Gson gson = new Gson();

    public UserDTO toUserDTO(final String req){
        return gson.fromJson(req, UserDTO.class);
    }
}
