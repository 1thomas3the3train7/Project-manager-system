package com.example.userservice.Repository;

import com.example.userservice.Model.Role.BaseRole;
import com.example.userservice.Model.Role.ShortRole;
import com.example.userservice.Model.User.ShortUser;

public interface RoleRepository {
    void save(BaseRole baseRole);
    void delete(BaseRole baseRole);
    ShortRole getShortRoleByName(final String name);
    void appendUserAndRole(final Integer user_id,final Integer role_id);
    void appendUserAndRole(final ShortUser shortUser,final ShortRole shortRole);
}
