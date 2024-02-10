package com.kdu.service;

import com.kdu.model.ShiftUser;
import com.kdu.repository.ShiftUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShiftUserService {
    private final ShiftUserRepository shiftUserRepository;

    @Autowired
    public ShiftUserService(ShiftUserRepository shiftUserRepository) {
        this.shiftUserRepository = shiftUserRepository;
    }

    public void save(ShiftUser shiftUser) {
        shiftUserRepository.save(shiftUser);
    }

    public Optional<ShiftUser> get(UUID shiftUserId) {
        return shiftUserRepository.findById(shiftUserId);
    }

    public List<ShiftUser> getAll() {
        return shiftUserRepository.findAll();
    }

}