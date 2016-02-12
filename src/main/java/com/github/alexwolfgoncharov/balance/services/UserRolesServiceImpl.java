package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.dao.UserRolesDAO;
import com.github.alexwolfgoncharov.balance.dao.impl.UserRolesDAOImpl;
import com.github.alexwolfgoncharov.balance.security.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 01.02.16.
 */
public class UserRolesServiceImpl implements UserRolesService {
    private static final Logger log = Logger.getLogger(UserServiceImpl.class
            .getName());

    @Autowired
    private static UserRolesDAO rolesDAO = new UserRolesDAOImpl();
    @Override
    public void add(UserRoles roles) {
        rolesDAO.add(roles);
    }

    @Override
    public Set<UserRoles> getAll() {
        return rolesDAO.getAll();
    }

    @Override
    public void delete(UserRoles roles) {
        rolesDAO.delete(roles);
    }

    @Override
    public UserRoles getById(int id) {
        return rolesDAO.getById(id);
    }
}
