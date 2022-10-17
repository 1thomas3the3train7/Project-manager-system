package com.example.authservice;

import com.example.authservice.DTO.ProjectDTO;
import com.example.authservice.DTO.UserSearchDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final Gson gson = new Gson();
    public String ProjectCreateValid(final String request){
        if(request == null){
            return "not valid request";
        }
        final ProjectDTO projectDTO = gson.fromJson(request, ProjectDTO.class);
        if(projectDTO.getName() == null){
            return "not valid request";
        }
        return null;
    }
    private String ProjectCreate(final ProjectDTO projectDTO){
        return null;
    }
}
