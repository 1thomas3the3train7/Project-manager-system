package com.example.userservice;

import com.example.userservice.Model.Role.ShortRole;
import com.example.userservice.Model.User.ShortUser;
import com.example.userservice.Repository.RoleRepository;
import com.example.userservice.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserserviceApplicationTests {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}
	@Test
	void forRepo(){
		ShortRole shortRole = new ShortRole();ShortRole shortRole1 = new ShortRole();
		shortRole.setName("ROLE_ADMIN");shortRole1.setName("ROLE_USER");
		roleRepository.save(shortRole);
		roleRepository.save(shortRole1);
		ShortUser shortUser = new ShortUser();
		shortUser.setEmail("emailtest1");
		shortUser.setPassword("password");
		userRepository.saveUser(shortUser);
		roleRepository.appendUserAndRole(shortUser,shortRole);
		roleRepository.appendUserAndRole(shortUser,shortRole1);
		ShortUser shortUser1 = userRepository.getShortUserAndRoleByEmail("emailtest1");
		shortUser1.getRoles().forEach(c -> System.out.println(c.getName()));
	}

}
