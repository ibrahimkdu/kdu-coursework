package com.kdu.service;

import com.kdu.model.ShiftType;
import com.kdu.repository.ShiftTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShiftTypeService {
    private final ShiftTypeRepository shiftTypeRepository;

    @Autowired
    public ShiftTypeService(ShiftTypeRepository shiftTypeRepository) {
        this.shiftTypeRepository = shiftTypeRepository;
    }

    public void save(ShiftType shiftType) {
        shiftTypeRepository.save(shiftType);
    }

    public Optional<ShiftType> get(UUID shiftTypeId) {
        return shiftTypeRepository.findById(shiftTypeId);
    }

    public List<ShiftType> getAll() {
        return shiftTypeRepository.findAll();
    }
}