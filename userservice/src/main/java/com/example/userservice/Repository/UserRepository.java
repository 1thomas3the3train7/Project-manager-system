package com.example.userservice.Repository;

import com.example.userservice.Model.User.BaseUser;
import com.example.userservice.Model.User.ShortUser;

public interface UserRepository {
    void saveUser(BaseUser user);
    ShortUser getShortUserAndRoleByEmail(final String email);
    ShortUser getShortUserByEmail(final String email);
}
