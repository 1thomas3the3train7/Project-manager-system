package com.example.authservice;

import com.example.authservice.DTO.RoleDTO;
import com.example.authservice.Repository.RedisRepository;
import com.example.authservice.Service.JwtUtils;
import com.google.gson.Gson;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AuthserviceApplicationTests {

	@Autowired
	private RedisRepository redisRepository;
	@Autowired
	private JwtUtils jwtUtils;

	private final Gson gson = new Gson();
	@Test
	void contextLoads() {
		System.out.println("start");
		redisRepository.save("user","jwt").subscribe(System.out::println);
		redisRepository.get("user").subscribe(System.out::println);
		System.out.println("end");
		List<RoleDTO> roles = new ArrayList<>();
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setName("role1");
		RoleDTO roleDTO1 = new RoleDTO();
		roleDTO1.setName("role2");
		roles.add(roleDTO);roles.add(roleDTO1);
		gson.toJson(roles);
	}

}
