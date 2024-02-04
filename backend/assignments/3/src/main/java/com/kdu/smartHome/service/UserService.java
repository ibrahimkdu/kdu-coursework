package com.kdu.smartHome.service;

import com.kdu.smartHome.model.User;
import com.kdu.smartHome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        // Check if the user already exists
        // Check if the user already exists by username or emailId


        // Implement user registration logic
        return userRepository.save(user);

        // Implement user registration logic

    }

    public User getUserByUsername(String username) {
        // Implement logic to fetch user by username from the repository
        return userRepository.findByUserName(username);
    }

    // Add more service methods as needed
}
