package com.example.projectservice.Service;

import com.example.projectservice.DTO.UserDTO;
import com.example.projectservice.Model.User.DetailedUser;
import com.example.projectservice.RepositoryImpl.UserRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Gson gson = new Gson();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public String saveNewUserAndValid(final String request){
        if(request == null){
            return "not valid request";
        }
        final UserDTO userDTO = gson.fromJson(request, UserDTO.class);
        if(userDTO.getEmail() == null || userDTO.getUser_id() < 1){
            return "not valid request";
        }
        return saveNewUser(userDTO);
    }
    private String saveNewUser(final UserDTO userDTO){
        final DetailedUser detailedUser = new DetailedUser(userDTO.getEmail(), userDTO.getUser_id());
        userRepository.save(detailedUser);
        return "save";
    }

    public String addUserToProject(final int user_id,final int project_id){
        userRepository.appendProjectAndUser(project_id,user_id);
        return "add";
    }
    public String addUserToProject(final String email,final int project_id){

        return "add";
    }
}
