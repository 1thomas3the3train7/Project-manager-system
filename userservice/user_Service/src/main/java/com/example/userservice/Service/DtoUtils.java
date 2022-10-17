package com.example.userservice.Service;

import com.example.userservice.Model.Role.ShortRole;
import com.example.userservice.Model.User.ShortUser;
import com.example.userservice.DTO.RoleDTO;
import com.example.userservice.DTO.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DtoUtils {
    public UserDTO shortUserToDto(final ShortUser userWithRoles){
        try {
            final int size = userWithRoles.getRoles().size();
            if(size != 0){
                final RoleDTO[] roles = new RoleDTO[size];
                final List<ShortRole> roles1 = userWithRoles.getRoles().stream().toList();
                for(int i = 0;i < size - 1;i++){
                    roles[i] = new RoleDTO(roles1.get(i).getName());
                }
                return new UserDTO(userWithRoles.getId(),userWithRoles.getEmail(), userWithRoles.getPassword(),
                        userWithRoles.getFirstName(), userWithRoles.getLastName(), userWithRoles.isEnabled(),
                        userWithRoles.isBanned(),roles);
            }
            return new UserDTO(userWithRoles.getEmail(), userWithRoles.getPassword(), userWithRoles.getFirstName());
        } catch (NullPointerException n){
            System.out.println("null exc");
            n.printStackTrace();
            return null;
        }
    }
    public UserDTO[] ListShortUsersToDto(final List<ShortUser> users){
        final int size = users.size();
        if(size == 0){
            return null;
        }
        final UserDTO[] usersD = new UserDTO[size];
        for(int i = 0;i < size;i++){
            final ShortUser shortUser = users.get(i);
            usersD[i] = new UserDTO(shortUser.getId(),shortUser.getEmail(),
                    shortUser.getPassword(),shortUser.getFirstName());
        }
        return usersD;
    }
}
