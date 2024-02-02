package com.kdu.Assesment2.service;

import com.kdu.Assesment2.model.Users;
import com.kdu.Assesment2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository userRepository) {
        this.usersRepository = userRepository;
    }

    public void saveUser(Users users) {
        usersRepository.save(users);
    }

    public Users getUserById(Integer usersId) {
        return usersRepository.findById(usersId).orElse(null);
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }


}
