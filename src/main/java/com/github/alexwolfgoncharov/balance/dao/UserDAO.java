package com.github.alexwolfgoncharov.balance.dao;

import com.github.alexwolfgoncharov.balance.security.User;
import com.github.alexwolfgoncharov.balance.security.UserRoles;

import java.util.List;

/**
 * Created by alexwolf on 01.02.16.
 */
public interface UserDAO {
    void add(User user);
    User modify(User user);
    User getByID(int ID);
    User getByLogin(String login);
    List<User> getAll();
    List<User> getAllByRole(UserRoles role);
    void delete (User user);
    void block(User user);
}
