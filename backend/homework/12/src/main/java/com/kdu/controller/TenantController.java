package com.kdu.controller;

import com.kdu.model.Tenant;
import com.kdu.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/v1")
public class TenantController {
    private final TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping("/tenant")
    public ResponseEntity<String> createNewTenant(@RequestBody Tenant tenant) {
        tenantService.save(tenant);
        return new ResponseEntity<>("saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/AllTenants")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> allTenants = tenantService.getAll();
        return new ResponseEntity<>(allTenants, HttpStatus.OK);
    }
}