package com.example.jdbc.service;

import com.example.jdbc.dao.ShiftDAO;
import com.example.jdbc.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftService {


    ShiftDAO shiftDAO;
    @Autowired
    public ShiftService(ShiftDAO shiftDAO)
    {
        this.shiftDAO=shiftDAO;
    }
    public void addShift(Shift shift){
        shiftDAO.saveShift(shift);
    }
    public Shift getShiftById(UUID id){
        return shiftDAO.getShiftByid(id);
    }
}
