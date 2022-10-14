package com.example.projectservice;

import com.example.projectservice.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProjectserviceApplication implements CommandLineRunner {
	@Autowired
	private ProjectService projectService;

	public static void main(String[] args) {
		SpringApplication.run(ProjectserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
