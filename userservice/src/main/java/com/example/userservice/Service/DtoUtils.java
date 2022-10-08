package com.example.userservice.Service;

import com.example.userservice.DTO.RoleDTO;
import com.example.userservice.DTO.UserDTO;
import com.example.userservice.Model.Role.ShortRole;
import com.example.userservice.Model.User.ShortUser;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DtoUtils {
    public UserDTO shortUserToDto(final ShortUser userWithRoles){
        try {
            final int size = userWithRoles.getRoles().size();
            final RoleDTO[] roles = new RoleDTO[size];
            final List<ShortRole> roles1 = userWithRoles.getRoles().stream().toList();
            for(int i = 0;i < size - 1;i++){
                roles[i] = new RoleDTO(roles1.get(i).getName());
            }
            return new UserDTO(userWithRoles.getId(),userWithRoles.getEmail(), userWithRoles.getPassword(),
                    userWithRoles.getFirstName(), userWithRoles.getLastName(), userWithRoles.isEnabled(),
                    userWithRoles.isBanned(),roles);
        } catch (NullPointerException n){
            System.out.println("null exc");
            n.printStackTrace();
            return null;
        }
    }
}
