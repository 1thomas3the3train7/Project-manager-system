package com.example.authservice;

import com.example.authservice.Repository.RedisRepository;
import com.example.authservice.Service.ProjectService;
import com.example.authservice.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthServiceApplicationTests {
	@Autowired
	private RedisRepository redisRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;

	@Test
	void contextLoads() {
	}
	@Test
	void forRedis1(){
		redisRepository.save("testredis","redis");
		String res = redisRepository.getValue("testredis");
		if(!res.equals("redis")){
			throw new RuntimeException("testfailed");
		}
	}

}
