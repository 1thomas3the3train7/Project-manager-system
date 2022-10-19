package com.example.authservice.Service;

import com.example.authservice.DTO.JwtDTO;
import com.example.authservice.DTO.UserDTO;
import com.example.authservice.DTO.UserSearchDTO;
import com.example.authservice.Exception.NotValidRequestException;
import com.example.authservice.Exception.UserNotFoundException;
import com.example.authservice.Grpc.GrpcClient;
import com.example.authservice.Repository.RedisRepository;
import com.example.authservice.Utils.DtoUtils;
import com.example.authservice.Utils.JwtUtils;
import com.example.authservice.Validate.EmailValidator;
import com.google.gson.Gson;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final GrpcClient grpcClient;
    private final DtoUtils dtoUtils;
    private final JwtUtils jwtUtils;
    private final Gson gson = new Gson();
    private final EmailValidator emailValidator;
    private final PasswordEncoder encoder;
    private final RedisRepository redisRepository;

    public UserService(GrpcClient grpcClient, DtoUtils dtoUtils, JwtUtils jwtUtils,
                       EmailValidator emailValidator, PasswordEncoder encoder, RedisRepository redisRepository) {
        this.grpcClient = grpcClient;
        this.dtoUtils = dtoUtils;
        this.jwtUtils = jwtUtils;
        this.emailValidator = emailValidator;
        this.encoder = encoder;
        this.redisRepository = redisRepository;
    }
    public String getUserByEmail(final String email){
        return grpcClient.getUserByEmail(email);
    }
    public JwtDTO loginUser(final String req){
        final UserDTO userDTO = dtoUtils.toUserDTO(req);
        final String response = grpcClient.getUserByEmail(userDTO.getEmail());
        if(response == null || response.equals("null")){
            throw new UserNotFoundException("Not correct email or password");
        }
        final UserDTO userDTOfromBD = gson.fromJson(response, UserDTO.class);
        if(!encoder.matches(userDTO.getPassword(),userDTOfromBD.getPassword())){
            throw new UserNotFoundException("Not corrcet email or password");
        }
        final String refreshToken = jwtUtils.generateRefreshToken(userDTO);
        redisRepository.save(userDTO.getEmail(),String.valueOf(refreshToken.hashCode()));
        System.out.println("was save");
        return new  JwtDTO(jwtUtils.generateAccessToken(userDTO),
                refreshToken);
    }
    private JwtDTO generateAccessAndRefreshToken(final String refresh){
        if(refresh == null){
            throw new NotValidRequestException("Not valid request");
        }
        final UserDTO userDTO = jwtUtils.parseRefreshToken(refresh);
        final String hashToken = String.valueOf(refresh.hashCode());
        final String tokenRedis = redisRepository.getValue(userDTO.getEmail());

        if(jwtUtils.validateRefreshToken(refresh) && hashToken.equals(tokenRedis)){
            final String newRefreshToken = jwtUtils.generateRefreshToken(userDTO);
            redisRepository.save(userDTO.getEmail(),String.valueOf(newRefreshToken.hashCode()));
            final String newAccessToken = jwtUtils.generateAccessToken(userDTO);
            return new JwtDTO(newAccessToken,newRefreshToken);
        }
        throw new NotValidRequestException("Not valid request or incorrect token");
    }
    public JwtDTO getAccessAndRefreshToken(final String request){
        final JwtDTO jwtDTO = gson.fromJson(request, JwtDTO.class);
        if(jwtDTO.getRefreshToken() == null){
            throw new NotValidRequestException("Not valid request");
        }
        return generateAccessAndRefreshToken(jwtDTO.getRefreshToken());
    }
    public String registerAndValid(final String request){
        try {
            final UserDTO userDTO = gson.fromJson(request,UserDTO.class);
            System.out.println(userDTO.getEmail());
            System.out.println(userDTO.getPassword());
            if(emailValidator.validateEmail(userDTO.getEmail()) && userDTO.getPassword().length() > 5){
                System.out.println("if");
                return registerUser(userDTO);
            }
            return null;
        } catch (NullPointerException n){
            n.printStackTrace();
            throw new NotValidRequestException("Not valid request (null register)");
        }
    }
    private String registerUser(final UserDTO userDTO){
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        return grpcClient.userSave(userDTO);
    }
    public String searchUserAndValid(final String request){
        if(request == null){
            throw new NotValidRequestException("Not valid request");
        }
        final UserSearchDTO userSearchDTO = gson.fromJson(request, UserSearchDTO.class);
        if(userSearchDTO.getName() == null){
            throw new NotValidRequestException("Not valid request");
        }
        return grpcClient.searchUser(userSearchDTO);
    }
}
