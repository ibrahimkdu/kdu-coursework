package com.kdu.service;

import com.kdu.model.Tenant;
import com.kdu.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    @Autowired
    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public void save(Tenant tenant) {
        tenantRepository.save(tenant);
    }

    public Optional<Tenant> get(UUID tenantid)
    {
        return tenantRepository.findById(tenantid);
    }
    public List<Tenant> getAll() {
        return tenantRepository.findAll();
    }
}