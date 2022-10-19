package com.example.userservice;


import com.example.userservice.Model.Role.DetailedRole;
import com.example.userservice.Model.Role.ShortRole;
import com.example.userservice.Model.User.DetailedUser;
import com.example.userservice.Repository.RoleRepository;
import com.example.userservice.Repository.UserRepository;
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
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public void run(String... args) throws Exception {
		DetailedRole detailedRole = new DetailedRole();
		detailedRole.setName("ROLE_USER");
		roleRepository.save(detailedRole);
		DetailedUser detailedUser = new DetailedUser();
		detailedUser.setEmail("email");
		userRepository.saveUser(detailedUser);
		for(int i = 0;i < 100;i++){
			DetailedUser detailedUser1 = new DetailedUser();
			detailedUser1.setEmail("email" + i);
			detailedUser1.setFirstName("name" + i);
			userRepository.saveUser(detailedUser1);
		}
	}
}
