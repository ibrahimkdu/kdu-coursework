package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trader {
    private String firstName;
    private String lastName;

    private String mobile;
    private String walletAddress;

    private Map<String, Long> wallet;

    private double profitLoss;

    public Trader(String firstName, String lastName, String mobile, String walletAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.walletAddress = walletAddress;
        this.wallet = new HashMap<>();
    }

    @Override
    public String toString() {
        return "org.example.Trader{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", walletAddress='" + walletAddress + '\'' +
                '}';
    }

    public static List<Trader> loadTradersFromCSV(String file) {
        List<Trader> traderList = new ArrayList<>();
        try {
            CSVUtils.parseCSV(file, true).forEach(trader -> {
                Trader obj = new Trader(trader[1], trader[2], trader[3], trader[4]);
                traderList.add(obj);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return traderList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public Map<String, Long> getWallet() {
        return wallet;
    }

    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
    }

    public double getProfitLoss() {
        return profitLoss;
    }
}
