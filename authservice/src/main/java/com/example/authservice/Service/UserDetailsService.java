package com.example.authservice.Service;

import com.example.authservice.DTO.RoleDTO;
import com.example.authservice.DTO.UserDTO;
import com.example.authservice.Grpc.GrpcClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsService implements ReactiveUserDetailsService {
    private final GrpcClient grpcClient;
    private final DtoUtils dtoUtils;

    public UserDetailsService(GrpcClient grpcClient, DtoUtils dtoUtils) {
        this.grpcClient = grpcClient;
        this.dtoUtils = dtoUtils;
    }

    private Collection<? extends GrantedAuthority> toGrantedAuthority(final RoleDTO[] roles){
        return Arrays.stream(roles).map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    @Override
    public Mono<UserDetails> findByUsername(String email) {
        final UserDTO userDTO = dtoUtils.toUserDTO(grpcClient.getUserByEmail(email));
        if(userDTO != null){
            return Mono.just(new User(userDTO.getEmail(),userDTO.getPassword(),toGrantedAuthority(userDTO.getRoles())));
        } throw new UsernameNotFoundException(String.format("User not found"));
    }
}
