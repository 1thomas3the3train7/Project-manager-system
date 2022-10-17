package com.example.projectservice.Repository;

import com.example.projectservice.Model.Project.BaseProject;
import com.example.projectservice.Model.User.ShortUser;
import com.example.projectservice.Model.User.User;

public interface UserRepository {
    void save(User user);
    void delete(User user);
    void appendProjectAndUser(BaseProject baseProject, User user);
    void appendProjectAndUser(int project_id,int user_id);
    ShortUser getShortUserByEmail(String email);

}
