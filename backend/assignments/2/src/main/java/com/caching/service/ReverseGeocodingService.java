package com.caching.service;

import com.caching.dto.ReverseGeocodingResponse;

public interface ReverseGeocodingService {
    ReverseGeocodingResponse reverseGeocoding(String latitude, String longitude);
}