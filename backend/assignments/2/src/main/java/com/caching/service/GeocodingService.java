package com.caching.service;

import com.caching.dto.GeocodingResponse;

public interface GeocodingService {
    GeocodingResponse geocoding(String address);
}