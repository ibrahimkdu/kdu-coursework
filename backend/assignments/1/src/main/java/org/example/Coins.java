package org.example;

import java.util.ArrayList;
import java.util.List;

public class Coins {
    private int rank;
    private String name;
    private String symbol;

    private double price;

    private long circulatingSupply;

    public Coins(int rank, String name, String symbol, double price, long circulatingSupply) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulatingSupply = circulatingSupply;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "rank=" + rank +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                ", circulatingSupply=" + circulatingSupply +
                '}';
    }

    public static List<Coins> loadCoinsFromCSV(String file) {
        List<Coins> coinlist = new ArrayList<>();
        try {
            CSVUtils.parseCSV(file, true).forEach(coin -> {
                Coins obj = new Coins(Integer.parseInt(coin[1]), coin[2], coin[3], Double.parseDouble(coin[4]), Long.parseLong(coin[5]));
                coinlist.add(obj);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coinlist;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCoinName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoinSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(long circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }
}
