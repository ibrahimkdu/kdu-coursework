package com.example.jdbc.service;
import com.example.jdbc.DAO.ShiftTypeDAO;
import com.example.jdbc.model.ShiftType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftTypeService {
    @Autowired
    ShiftTypeDAO shiftTypeDAO;

    public void addShiftType(ShiftType shiftType){
        shiftTypeDAO.saveShiftType(shiftType);
    }
    public ShiftType getShiftTypeById(UUID id){
        return shiftTypeDAO.getShiftTypeById(id);
    }

}
