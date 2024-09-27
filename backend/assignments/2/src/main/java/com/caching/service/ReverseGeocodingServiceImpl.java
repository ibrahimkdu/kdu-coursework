package com.caching.service;

import com.caching.dto.ReverseGeocodingResponse;
import com.caching.exception.InvalidRequestException;
import com.caching.exception.ReverseGeoCodingApiException;
import com.fasterxml.jackson.databind.JsonNode;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ReverseGeocodingServiceImpl implements ReverseGeocodingService {

    @Value("${api-key}")
    String apiKey;
    /**
     * Retrieves reverse geocoding information (address label) for the given latitude and longitude using the Position Stack API.
     *
     * @param latitude1  The latitude for which reverse geocoding information is requested.
     * @param longitude1 The longitude for which reverse geocoding information is requested.
     * @return ReverseGeocodingResponse containing the address label.
     * @throws InvalidRequestException        If latitude or longitude is blank.
     * @throws ReverseGeoCodingApiException   If null data is fetched from the API.
     * @throws InvalidRequestException        If the coordinate format is invalid.
     */
    @Override
    public ReverseGeocodingResponse reverseGeocoding(String latitude1, String longitude1) {
        log.info("Fetching reverse API from position stack");
        validateRequest(latitude1, longitude1);
        Double latitude = parseCoordinate(latitude1);
        Double longitude = parseCoordinate(longitude1);
        String url = "http://api.positionstack.com/v1/reverse?access_key=" + apiKey + "&query=" + latitude + "," + longitude;
        RestTemplate restTemplate = new RestTemplate();
        JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class);
        String label = jsonNode.get("data").get(0).get("label").asText();
        ReverseGeocodingResponse reverseGeocodingResponse = new ReverseGeocodingResponse(label);
        log.info("Address: " + label);
        ReverseGeocodingResponse response = reverseGeocodingResponse;
        validateApiResponse(response);
        return response;
    }

    private Double parseCoordinate(String coordinate) {
        try {
            return Double.parseDouble(coordinate);
        } catch (NumberFormatException e) {
            throw new InvalidRequestException("Invalid coordinate format");
        }
    }

    private void validateRequest(String latitude, String longitude) {
        if (StringUtils.isBlank(latitude) || StringUtils.isBlank(longitude)) {
            throw new InvalidRequestException("Latitude or longitude is blank");
        }
    }

    private void validateApiResponse(ReverseGeocodingResponse response) {
        if (response == null) {
            throw new ReverseGeoCodingApiException("Null data fetched");
        }
    }
}