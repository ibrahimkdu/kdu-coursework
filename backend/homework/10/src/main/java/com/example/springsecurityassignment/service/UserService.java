package com.example.springsecurityassignment.service;

import com.example.springsecurityassignment.dto.UserDto;
import com.example.springsecurityassignment.exception.ResourceNOtFoundException;
import com.example.springsecurityassignment.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserDto> getAllUsers() throws RuntimeException {
        try {
            List<UserDto> users = userRepo.getAllUsers();
            if (users.isEmpty()) {
                throw new ResourceNOtFoundException("No users entry found in the  database");
            }
            return users;
        } catch (ResourceNOtFoundException e) {
            throw new ResourceNOtFoundException(e.getMessage());
        }
    }

    public UserDto getUserByName(String name) throws RuntimeException {
        try {
            UserDto user = userRepo.getUserByName(name);
            if (user != null) {
                return user;
            }
            throw new ResourceNOtFoundException(" user not  found ");
        } catch (ResourceNOtFoundException e) {
            throw new ResourceNOtFoundException(e.getMessage());
        }
    }

    public UserDto addUser(UserDto userDto) {
        try {
            userRepo.addUser(userDto);
            return userDto;
        } catch (Exception e) {
            throw new RuntimeException("Failed to add the user Retry ");
        }
    }
}
