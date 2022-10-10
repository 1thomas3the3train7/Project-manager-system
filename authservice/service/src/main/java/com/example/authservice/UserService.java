package com.example.authservice;

import com.example.authservice.dto.JwtDTO;
import com.example.authservice.dto.RoleDTO;
import com.example.authservice.dto.UserDTO;
import com.example.authservice.grpc.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final GrpcClient grpcClient;
    private final DtoUtils dtoUtils;
    private final JwtUtils jwtUtils;

    public UserService(GrpcClient grpcClient, DtoUtils dtoUtils, JwtUtils jwtUtils) {
        this.grpcClient = grpcClient;
        this.dtoUtils = dtoUtils;
        this.jwtUtils = jwtUtils;
    }
    public String getUserByEmail(final String email){
        return grpcClient.getUserByEmail(email);
    }
    public JwtDTO loginUser(final String req){
        final UserDTO userDTO = dtoUtils.toUserDTO(req);
        RoleDTO[] roleDTOS = new RoleDTO[1];
        roleDTOS[0] = new RoleDTO("ROLE_ADMIN");
        userDTO.setRoles(roleDTOS);
        return new  JwtDTO(jwtUtils.generateAccessToken(userDTO),
                jwtUtils.generateRefreshToken(userDTO));
    }
}
