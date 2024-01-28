package com.caching.controller;

import com.caching.dto.GeocodingResponse;
import com.caching.exception.ApiNullResponse;
import com.caching.service.GeocodingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/geocoding")
public class GeocodingController {
    private final GeocodingService geocodingService;

    @Autowired
    public GeocodingController(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    @GetMapping
    @Cacheable(value = "geocoding", key = "#address", condition = "#address != 'goa'")
    public ResponseEntity<GeocodingResponse> geocoding(@RequestParam("address") String address) {
        log.info("fetching forward api from position stack");
        try {
            if (address == null || address.isBlank()) {
                throw new ApiNullResponse("address is blank");
            }
            GeocodingResponse response = geocodingService.geocoding(address);
            if (response == null) {
                throw new ApiNullResponse("null data fetched");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while processing geocoding request: " + e.getMessage());
            throw new ApiNullResponse("error found");
        }
    }
}