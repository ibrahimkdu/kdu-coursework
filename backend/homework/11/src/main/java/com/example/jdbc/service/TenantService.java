package com.example.jdbc.service;

import com.example.jdbc.dao.TenantDAO;
import com.example.jdbc.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TenantService {

    TenantDAO tenantDAO;
    @Autowired
    public TenantService(TenantDAO tenantDAO)
    {
        this.tenantDAO=tenantDAO;
    }
    public void addTenant(Tenant tenant){
        tenantDAO.saveTenant(tenant);
    }
    public Tenant getTenantById(UUID id){
        return tenantDAO.getTenantById(id);
    }
}
