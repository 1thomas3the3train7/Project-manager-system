package com.example.userservice.Service;

import com.example.userservice.Repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DtoUtils dtoUtils;

    private final Gson gson = new Gson();

    public UserService(UserRepository userRepository, DtoUtils dtoUtils) {
        this.userRepository = userRepository;
        this.dtoUtils = dtoUtils;
    }
    public String getUserByEmail(final String email){
        return gson.toJson(dtoUtils.shortUserToDto(userRepository.getShortUserAndRoleByEmail(email)));
    }
}
