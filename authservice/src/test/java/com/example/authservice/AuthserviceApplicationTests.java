package com.example.authservice;

import com.example.authservice.DTO.RoleDTO;
import com.example.authservice.DTO.UserDTO;
import com.example.authservice.Grpc.GrpcClient;
import com.example.authservice.Repository.RedisRepository;
import com.example.authservice.Service.DtoUtils;
import com.example.authservice.Service.JwtUtils;
import com.example.authservice.Service.UserService;
import com.example.authservice.Validate.EmailValidator;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthserviceApplicationTests {

	@Autowired
	private RedisRepository redisRepository;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private GrpcClient grpcClient;
	@Autowired
	private DtoUtils dtoUtils;
	@Autowired
	private UserService userService;
	@Autowired
	private EmailValidator emailValidator;

	private final Gson gson = new Gson();
	@Test
	void contextLoads() {
	}
	@Test
	void DtoTest() {
		/*UserDTO userDTO = dtoUtils.toUserDTO(grpcClient.getUserByEmail("email"));
		System.out.println(userDTO.getEmail() + userDTO.getPassword());*/
		UserDTO userDTO1 = new UserDTO();
		RoleDTO[] roles = new RoleDTO[1];
		RoleDTO roleDTO = new RoleDTO("ROLE_ADMIN");
		roles[0] = roleDTO;
		userDTO1.setRoles(roles);
		String token = jwtUtils.generateAccessToken(userDTO1);
		Claims claims = jwtUtils.getAccessClaims(token);
		System.out.println(claims.get("roles"));
		System.out.println(claims.get("roles").getClass());

		RoleDTO[] roles1 = gson.fromJson((String) claims.get("roles"), RoleDTO[].class);
		for (RoleDTO r : roles1) {
			System.out.println(r.getId());
			System.out.println(r.getName());
		}
	}
	@Test
	void forRedis(){
		redisRepository.save("key1","value1").subscribe(System.out::println);
		redisRepository.save("key1","value2").subscribe(System.out::println);
		redisRepository.get("key1").subscribe(System.out::println);
		final String jwt;
		UserDTO userDTO = new UserDTO();
		redisRepository.get("key1").subscribe(userDTO::setEmail);
	}
	@Test
	void forJwtUtils(){
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail("email");
		RoleDTO[] roles = new RoleDTO[1];
		RoleDTO roleDTO = new RoleDTO("ROLE_ADMIN");
		roles[0] = roleDTO;
		userDTO.setRoles(roles);
		System.out.println(userDTO);
		String ref = jwtUtils.generateRefreshToken(userDTO);
		UserDTO userDTO1 = jwtUtils.parseRefreshToken(ref);
		String ref1 = jwtUtils.generateRefreshToken(userDTO1);
		UserDTO userDTO2 = jwtUtils.parseRefreshToken(ref1);
		System.out.println(userDTO);
		System.out.println(userDTO1);
		System.out.println(userDTO2);
		System.out.println(ref);
		System.out.println(ref1);
	}
	@Test
	void forReactive(){

	}
	@Test
	void forValidate(){
		System.out.println(emailValidator.validateEmail("mercyfirsov@gmail.com"));
		System.out.println(emailValidator.validateEmail("mercyfirsov@gmaiasdadl.com"));
		System.out.println(emailValidator.validateEmail("merdwdwdwdwcyfirsov@gmail.com"));
		System.out.println(emailValidator.validateEmail("mercyfirsov@gmailcom"));
		System.out.println(emailValidator.validateEmail("mercyfirsovgmail.com"));
		System.out.println(emailValidator.validateEmail("mercyfirsov@gmail.wdwdwd"));
	}
	@Test
	void forGrpc(){
		for(int i = 0;i < 100;i++){
			String response = grpcClient.getUserByEmail("emaissdadsl");
			System.out.println(response);
		}

	}


}
