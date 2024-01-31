package com.example.jdbc.service;

import com.example.jdbc.DAO.TenantDAO;
import com.example.jdbc.model.Shift;
import com.example.jdbc.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TenantService {
    @Autowired
    TenantDAO tenantDAO;
    public void addTenant(Tenant tenant){
        tenantDAO.saveTenant(tenant);
    }
    public Tenant getTenantById(UUID id){
        return tenantDAO.getTenantById(id);
    }
}
