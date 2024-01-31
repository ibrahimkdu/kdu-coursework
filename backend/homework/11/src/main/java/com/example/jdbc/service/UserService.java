package com.example.jdbc.service;


import com.example.jdbc.DAO.UserDAO;
import com.example.jdbc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;
    public void addUser(User user){
        userDAO.saveUser(user);
    }

    public User getUserById(UUID id){
        return userDAO.getUserById(id);
    }

    public List<User> getAllUser(){
        return userDAO.getUsers();
    }

    public List<User> getUserByName(String name){
        return userDAO.getUserByName(name);
    }

    public int updateUser(UUID id, String name){
        return userDAO.updateNameForId(id,name);
    }


}
