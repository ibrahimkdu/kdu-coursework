package com.kdu.smarthome.service;

import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.dto.request.RequestUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void save(RequestUserDTO user) {
        try {
            userRepository.save(user);
        } catch (Exception exc) {
            throw exc;
        }
    }
    public void addUser(RequestUserDTO person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        save(person);
    }
    public RequestUserDTO getUserByUsername(String username) throws Exception {
        try {
            RequestUserDTO user = userRepository.findByUsername(username.trim());
            if (user == null) {
                throw new UsernameNotFoundException("Username not found");
            }
            return user;
        } catch (UsernameNotFoundException e) {
            throw e;
        }
    }
}
