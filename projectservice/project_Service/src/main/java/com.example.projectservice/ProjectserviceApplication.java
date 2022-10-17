package com.example.projectservice;

import com.example.projectservice.DTO.ProjectDTO;
import com.example.projectservice.Model.Passport.DetailedPassport;
import com.example.projectservice.Model.Project.DetailedProject;
import com.example.projectservice.Repository.ProjectRepository;
import com.example.projectservice.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;

@SpringBootApplication
@EnableEurekaClient
public class ProjectserviceApplication implements CommandLineRunner {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectRepository projectRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
