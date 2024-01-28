package com.caching.controller;

import com.caching.dto.ReverseGeocodingResponse;
import com.caching.service.ReverseGeocodingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/reverse-geocoding")
public class ReverseGeocodingController {
    private final ReverseGeocodingService reverseGeocodingService;

    @Autowired
    public ReverseGeocodingController(ReverseGeocodingService reverseGeocodingService) {
        this.reverseGeocodingService = reverseGeocodingService;
    }

    @GetMapping
    @Cacheable(value = "reverse-geocoding", key = "{#latitude,#longitude}")
    public String reverseGeocoding(@Valid @ModelAttribute @RequestParam("latitude") String latitude,
                                   @RequestParam("longitude") String longitude) {
        log.info("Fetching reverse API from position stack");
        ReverseGeocodingResponse response = reverseGeocodingService.reverseGeocoding(latitude, longitude);
        return response.getLabel();
    }
}