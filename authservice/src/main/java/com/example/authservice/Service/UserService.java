package com.example.authservice.Service;

import com.example.authservice.DTO.JwtDTO;
import com.example.authservice.DTO.RoleDTO;
import com.example.authservice.DTO.UserDTO;
import com.example.authservice.Grpc.GrpcClient;
import com.example.authservice.Repository.RedisRepository;
import com.example.authservice.Validate.EmailValidator;
import com.google.gson.Gson;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    private final GrpcClient grpcClient;
    private final DtoUtils dtoUtils;
    private final JwtUtils jwtUtils;
    private final RedisRepository redisRepository;
    private final Gson gson = new Gson();
    private final EmailValidator emailValidator;
    private final PasswordEncoder encoder;

    public UserService(GrpcClient grpcClient, DtoUtils dtoUtils, JwtUtils jwtUtils,
                       RedisRepository redisRepository, EmailValidator emailValidator, PasswordEncoder encoder) {
        this.grpcClient = grpcClient;
        this.dtoUtils = dtoUtils;
        this.jwtUtils = jwtUtils;
        this.redisRepository = redisRepository;
        this.emailValidator = emailValidator;
        this.encoder = encoder;
    }
    public String getUserByEmail(final String email){
        return grpcClient.getUserByEmail(email);
    }
    public JwtDTO loginUser(final String req){
        final UserDTO userDTO = dtoUtils.toUserDTO(req);
        final String response = grpcClient.getUserByEmail(userDTO.getEmail());
        if(response == null || response.equals("null")){
            System.out.println("user not found");
            return null;
        }
        final UserDTO userDTOfromBD = gson.fromJson(response, UserDTO.class);
        if(!encoder.matches(userDTO.getPassword(),userDTOfromBD.getPassword())){
            System.out.println("password encoded not same");
            return null;
        }
        RoleDTO[] roleDTOS = new RoleDTO[1];
        roleDTOS[0] = new RoleDTO("ROLE_ADMIN");
        userDTO.setRoles(roleDTOS);
        final String refreshToken = jwtUtils.generateRefreshToken(userDTO);
        redisRepository.save(userDTO.getEmail(),String.valueOf(refreshToken.hashCode())).subscribe(System.out::println);
        System.out.println("was save");
        return new  JwtDTO(jwtUtils.generateAccessToken(userDTO),
                refreshToken);
    }
    private JwtDTO generateAccessAndRefreshToken(final String refresh){
        if(refresh == null){
            return null;
        }
        final UserDTO userDTO = jwtUtils.parseRefreshToken(refresh);
        final CompletableFuture<String> getToken =
                redisRepository.get(userDTO.getEmail()).toFuture();
        final String hashToken = String.valueOf(refresh.hashCode());
        final String tokenRedis = getToken.join();

        if(jwtUtils.validateRefreshToken(refresh) && hashToken.equals(tokenRedis)){

            final String newRefreshToken = jwtUtils.generateRefreshToken(userDTO);
            final CompletableFuture<Boolean> saveToken = redisRepository
                    .save(userDTO.getEmail(),String.valueOf(newRefreshToken.hashCode())).toFuture();
            final String newAccessToken = jwtUtils.generateAccessToken(userDTO);
            System.out.println(saveToken.join());
            return new JwtDTO(newAccessToken,newRefreshToken);
        }
        return null;
    }
    public JwtDTO getAccessAndRefreshToken(final String request){
        final JwtDTO jwtDTO = gson.fromJson(request, JwtDTO.class);
        if(jwtDTO.getRefreshToken() == null){
            return null;
        }
        return generateAccessAndRefreshToken(jwtDTO.getRefreshToken());
    }
    public String registerAndValid(final String request){
        try {
            final UserDTO userDTO = gson.fromJson(request,UserDTO.class);
            if(emailValidator.validateEmail(userDTO.getEmail()) && userDTO.getPassword().length() > 5){
                return registerUser(userDTO);
            }
            return "Non correct email or password";
        } catch (NullPointerException n){
            return "Non correct email or password";
        }
    }
    private String registerUser(final UserDTO userDTO){
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        return grpcClient.userSave(userDTO);
    }
}
