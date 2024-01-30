package com.example.springsecurityassignment.service;

import com.example.springsecurityassignment.dto.UserDto;
import com.example.springsecurityassignment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StartUpDataAddition implements CommandLineRunner {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userRepo.addUser(new UserDto("Ibrahim", passwordEncoder.encode("test1234"), "ibrahim@kickdrum.com", "ROLE_ADMIN"));
        userRepo.addUser(new UserDto("Bittu", passwordEncoder.encode("test1234"), "Bittu@kickdrum.com", "ROLE_USER"));
        userRepo.addUser(new UserDto("Ravi", passwordEncoder.encode("test1234"), "ravi@kickdrum.com", "ROLE_USER"));
    }
}
