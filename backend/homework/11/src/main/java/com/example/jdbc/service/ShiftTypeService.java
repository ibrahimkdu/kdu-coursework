package com.example.jdbc.service;
import com.example.jdbc.dao.ShiftTypeDAO;
import com.example.jdbc.model.ShiftType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftTypeService {

    ShiftTypeDAO shiftTypeDAO;
    @Autowired
    public ShiftTypeService(ShiftTypeDAO shiftTypeDAO)
    {
        this.shiftTypeDAO=shiftTypeDAO;
    }

    public void addShiftType(ShiftType shiftType){
        shiftTypeDAO.saveShiftType(shiftType);
    }
    public ShiftType getShiftTypeById(UUID id){
        return shiftTypeDAO.getShiftTypeById(id);
    }

}
