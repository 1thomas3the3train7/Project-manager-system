package com.example.userservice.Repository;

import com.example.userservice.Model.Role.BaseRole;
import com.example.userservice.Model.Role.ShortRole;
import com.example.userservice.Model.User.BaseUser;

public interface RoleRepository {
    void save(BaseRole baseRole);
    void delete(BaseRole baseRole);
    ShortRole getShortRoleByName(final String name);
    void appendUserAndRole(final int user_id,final int role_id);
    void appendUserAndRole(final BaseUser user, final BaseRole baseRole);
}
