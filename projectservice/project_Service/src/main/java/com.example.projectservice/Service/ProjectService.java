package com.example.projectservice.Service;

import com.example.projectservice.DTO.PassportDTO;
import com.example.projectservice.DTO.ProjectDTO;
import com.example.projectservice.Repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final PassportService passportService;

    public ProjectService(ProjectRepository projectRepository, PassportService passportService) {
        this.projectRepository = projectRepository;
        this.passportService = passportService;
    }

    public void createProject(final ProjectDTO projectDTO){
        final PassportDTO passportDTO = projectDTO.getPassportDTO();
    }
}
