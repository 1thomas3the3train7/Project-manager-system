package com.example.userservice;

import com.example.userservice.Model.User.DetailedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserserviceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		DetailedUser detailedUser = new DetailedUser();
		detailedUser.setEmail("email");
		userRepository.saveUser(detailedUser);
	}
}
