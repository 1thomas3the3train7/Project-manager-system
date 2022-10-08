package com.example.projectservice.Repository;

import com.example.projectservice.Model.Project.BaseProject;
import com.example.projectservice.Model.User.Users;

public interface UserRepository {
    void save(Users users);
    void delete(Users users);
    void appendProjectAndUser(BaseProject baseProject,Users users);
    void appendProjectAndUser(int project_id,int user_id);
}
