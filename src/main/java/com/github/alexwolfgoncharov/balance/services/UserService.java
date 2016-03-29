package com.github.alexwolfgoncharov.balance.services;


import com.github.alexwolfgoncharov.balance.security.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
	 
    User getUser(String login);
    
    void addUser(User user);
    User update(User user);
    List<User> getAll();
    void deleteUser(User user);
    void block(User user);
 
}
