package org.example;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static String coinCSVPath = "src/main/resources/coins.csv";
    static String tradersCSVPath = "src/main/resources/traders.csv";
    static String transactionJSONPath = "src/main/resources/medium_transaction.json";
    private static CountDownLatch countDownLatch;
    private static final CryptoMarket cryptoMarket = new CryptoMarket(coinCSVPath, tradersCSVPath, transactionJSONPath);

    public static void main(String[] args) {
//no need of main class

    }

    public static ArrayList<String[]> parseCSV(Path file) throws IOException {
        return CSVUtils.parseCSV(file.toAbsolutePath().toString(), true);
    }

    public static JsonNode parseJsonFile(String file) throws IOException {
        return JsonUtils.parseJSON(file);
    }

    public static void executeTransactions(JsonNode transactionArray, CountDownLatch latch) {
        countDownLatch = latch;
        List<Transaction> transactions = Transaction.loadTransaction(transactionArray);
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(transactions.size(), 10));
        for (Transaction transaction : transactions) {
            executor.execute(new ExecuteTransaction(cryptoMarket, transaction, countDownLatch));
        }
        executor.shutdown();
    }
}




