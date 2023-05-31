package com.myproject.BoardManagement.demo.service;

import com.myproject.BoardManagement.demo.model.Action;
import com.myproject.BoardManagement.demo.model.User;

import java.util.Date;
import java.util.List;

public interface UserService {
     User saveUser(User user) ;
    List<User> findAllUsers();
//    User findUserByUsername(String username);
    List<User> getUsersByUsernameContaining(String searchString);

    List<Action> findUserActions(String username);
    List<Action> getActionsByUsernameAndDate(String username, Date date);

}
