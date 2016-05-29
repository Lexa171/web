package com.epam.spring.dao;

import java.util.List;

import com.epam.spring.model.User;

public interface UserDao {	   
    User getUserById(Integer userId);
    User getUserByName(String userName);
    List<User> getAllUsers();
    void deleteUser(User user);
    void saveOrUpdateUser(User user);
}
