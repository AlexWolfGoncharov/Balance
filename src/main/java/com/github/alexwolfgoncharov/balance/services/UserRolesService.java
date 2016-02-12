package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.security.UserRoles;

import java.util.Set;

/**
 * Created by alexwolf on 01.02.16.
 */
public interface UserRolesService {

    void add(UserRoles roles);
    Set<UserRoles> getAll();
    void delete (UserRoles roles);
    UserRoles getById(int id);
}
