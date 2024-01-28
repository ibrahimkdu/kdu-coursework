package com.caching.service;

import com.caching.dto.GeocodingResponse;
import com.caching.exception.GeoCodingApiException;
import com.caching.exception.InvalidRequestException;
import com.fasterxml.jackson.databind.JsonNode;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class GeocodingServiceImpl implements GeocodingService {
    @Value("${api-key}")
    String apiKey;

    @Override
    public GeocodingResponse geocoding(String address) {
        validateRequest(address);
        log.info("Fetching forward API from position stack");
        String url = "http://api.positionstack.com/v1/forward?access_key=" + apiKey + "&query=" + address;
        RestTemplate restTemplate = new RestTemplate();
        JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class);
        assert jsonNode != null;
        double lat = jsonNode.get("data").get(0).get("latitude").asDouble();
        double lon = jsonNode.get("data").get(0).get("longitude").asDouble();
        GeocodingResponse geocodingResponse = new GeocodingResponse(lat, lon);
        log.info("latitude: " + lat + " longitude: " + lon);
        GeocodingResponse response = geocodingResponse;
        validateApiResponse(response);
        return response;
    }

    private void validateRequest(String address) {
        if (StringUtils.isBlank(address)) {
            throw new InvalidRequestException("Address is blank");
        }
    }

    private void validateApiResponse(GeocodingResponse response) {
        if (response == null) {
            throw new GeoCodingApiException("Null data fetched");
        }
    }
}
