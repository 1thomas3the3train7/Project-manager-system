package com.example.userservice.Service;

import com.example.userservice.DTO.UserSearchDTO;
import com.example.userservice.Model.Role.ShortRole;
import com.example.userservice.Model.User.DetailedUser;
import com.example.userservice.Model.User.ShortUser;
import com.example.userservice.Repository.RoleRepository;
import com.example.userservice.DTO.UserDTO;

import com.example.userservice.Repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DtoUtils dtoUtils;
    private final RoleRepository roleRepository;
    private final Gson gson = new Gson();

    public UserService(UserRepository userRepository, DtoUtils dtoUtils, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.dtoUtils = dtoUtils;
        this.roleRepository = roleRepository;
    }
    public String getUserByEmail(final String email){
        return gson.toJson(dtoUtils.shortUserToDto(userRepository.getShortUserAndRoleByEmail(email)));
    }
    private String saveUser(final UserDTO userDTO){
        final ShortUser shortUser = userRepository.getShortUserByEmail(userDTO.getEmail());
        if(shortUser != null){
            return "user already exist";
        }
        final DetailedUser detailedUser =
                new DetailedUser(userDTO.getEmail(),userDTO.getPassword(),userDTO.getFirstName());
        userRepository.saveUser(detailedUser);
        final ShortRole shortRole = roleRepository.getShortRoleByName("ROLE_USER");
        roleRepository.appendUserAndRole(detailedUser,shortRole);

        return "user saved";
    }
    public String saveUserFromProto(final String email,final String password,final String firstName){
        return saveUser(new UserDTO(email,password,firstName));
    }
    public String searchUser(final String request){
        final UserSearchDTO userSearchDTO = gson.fromJson(request, UserSearchDTO.class);
        final String name = userSearchDTO.getName();
        int page = userSearchDTO.getPage();
        if(page < 1){page = 1;}
        if(name == null){
            return null;
        }
        return gson.toJson(dtoUtils.ListShortUsersToDto(userRepository.getShortUsersByName(name,page)));
    }
}
