package com.example.userservice;

import com.example.userservice.DTO.UserDTO;
import com.example.userservice.DTO.UserSearchDTO;
import com.example.userservice.Model.Role.ShortRole;
import com.example.userservice.Model.User.DetailedUser;
import com.example.userservice.Model.User.ShortUser;
import com.example.userservice.Repository.RoleRepository;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Service.DtoUtils;
import com.example.userservice.Service.UserService;
import com.example.userservice.Validate.DateParse;
import com.google.gson.Gson;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;

@SpringBootTest
class UserserviceApplicationTests {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DtoUtils dtoUtils;
	@Autowired
	private UserService userService;
	@Autowired
	private DateParse dateParse;

	private final Gson gson = new Gson();

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
	@Test
	void Repo2(){
		for(int i = 0; i < 100;i++){
			ShortUser shortUser = new ShortUser();
			shortUser.setFirstName("name" + i);
			userRepository.saveUser(shortUser);
		}
		userRepository.getShortUsersByName("name",1)
				.forEach(s -> System.out.println(s.getFirstName()));
		userRepository.getShortUsersByName("name",2)
				.forEach(s -> System.out.println(s.getFirstName()));
		userRepository.getShortUsersByName("name",3)
				.forEach(s -> System.out.println(s.getFirstName()));
		userRepository.getShortUsersByName("5",1)
				.forEach(s -> System.out.println(s.getFirstName()));
	}
	@Test
	void forUtils1(){

	}
	@Test
	void forsearch1(){
		for(int i = 0;i < 10;i++){
			DetailedUser detailedUser = new DetailedUser();
			detailedUser.setFirstName("name" + i);
			userRepository.saveUser(detailedUser);
		}
		UserSearchDTO userSearchDTO = new UserSearchDTO();
		userSearchDTO.setName("1");
		System.out.println(userService.searchUser(gson.toJson(userSearchDTO)));
	}
	@Test
	void forDateParse(){
		try {
			String data = "16.10.2022";
			Date date = dateParse.parseStandartFormat(data);
			System.out.println(date);
		} catch (ParseException p){
			p.printStackTrace();
			System.out.println("parse exc");
		}
	}
}
