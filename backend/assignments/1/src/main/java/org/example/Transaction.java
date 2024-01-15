package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


enum TransactionType {
    BUY, SELL, UPDATE_PRICE, ADD_VOLUME
}

public class Transaction {
    TransactionType type;
    Map<String, Object> data;

    @Override
    public String toString() {
        return "org.example.Transaction{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    private static final Random rnd = new Random();
    private String getBlockHash() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();

        /**
         * Introducing delay mimicking complex
         * calculation being performed.
         */
        for (double i = 0; i < 199999999; i++) {
            i = i;
        }

        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            transactionHash.append(SALTCHARS.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return hashCode;
    }

    public static List<Transaction> loadTransaction(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.treeToValue(JsonUtils.parseJSON(filePath), new TypeReference<List<Transaction>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Transaction> loadTransaction(JsonNode transactionArray) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.treeToValue(transactionArray, new TypeReference<List<Transaction>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
