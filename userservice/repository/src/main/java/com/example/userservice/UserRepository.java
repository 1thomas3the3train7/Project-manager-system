package com.example.userservice;

import com.example.userservice.Model.User.BaseUser;
import com.example.userservice.Model.User.ShortUser;

public interface UserRepository {
    void saveUser(BaseUser user);

    ShortUser getShortUserAndRoleByEmail(final String email);
}
