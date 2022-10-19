package com.example.authservice.Controllers;


import com.example.authservice.DTO.ProjectDTO;
import com.example.authservice.Service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createProject(@RequestBody ProjectDTO projectDTO){
        return null;
    }

}
