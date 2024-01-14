package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    private JsonUtils()
    {

    }
    public static JsonNode parseJSON(String file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        return mapper.readTree(fileReader);

    }
}
