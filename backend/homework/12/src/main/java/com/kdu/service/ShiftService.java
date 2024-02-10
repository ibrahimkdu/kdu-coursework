package com.kdu.service;

import com.kdu.model.Shift;
import com.kdu.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public void save(Shift shift) {
        shiftRepository.save(shift);
    }

    public Optional<Shift> get(UUID shiftId) {
        return shiftRepository.findById(shiftId);
    }
    public List<Shift> getAll() {
        return shiftRepository.findAll();
    }

}