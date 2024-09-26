package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class CryptoMarket {
    private static final Logger logger = LoggerFactory.getLogger(CryptoMarket.class);

    List<Coins> coinList;
    List<Transaction> transactionList;
    List<Trader> traderList;
    String traderNotFound="Trader Not Found";
    String coinNotFOund="Coin Not Found";
    public CryptoMarket(String coinCsvPath, String traderCsvPath, String transactionJsonPath) {
        this.coinList = Coins.loadCoinsFromCSV(coinCsvPath);
        this.traderList = Trader.loadTradersFromCSV(traderCsvPath);
        this.transactionList = Transaction.loadTransaction(transactionJsonPath);
    }

    public Coins getCoinDetails(String name) {
        Optional<Coins> result = coinList.stream()
                .filter(coin -> name.equals(coin.getCoinName()))
                .findFirst();

        return result.orElse(null);
    }

    public List<Coins> getTopCoins(int n) {
        return coinList.stream()
                .sorted(Comparator.comparingDouble(Coins::getPrice).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getTraderPortfolio(String traderId) {
        Trader trader = traderList.stream()
                .filter(t -> traderId.equals(t.getWalletAddress()))
                .findFirst()
                .orElse(null);

        if (trader == null) {
            logger.error(traderNotFound);
            return Collections.emptyMap();
        }

        Map<String, Integer> portfolio = new HashMap<>();
        trader.getWallet().forEach((coin, quantity) -> portfolio.put(coin, quantity.intValue()));

        return portfolio;
    }

    public double getTraderProfitLoss(String traderId) {
        Trader trader = traderList.stream()
                .filter(t -> traderId.equals(t.getWalletAddress()))
                .findFirst()
                .orElse(null);

        if (trader == null) {
            logger.error(traderNotFound);
            return 0.0;
        }

        double initialWalletValue = trader.getWallet().entrySet().stream()
                .mapToDouble(entry -> getCoinValue(entry.getKey(), entry.getValue()))
                .sum();

        double currentWalletValue = trader.getWallet().entrySet().stream()
                .mapToDouble(entry -> getCoinValue(entry.getKey(), entry.getValue()))
                .sum();

        return currentWalletValue - initialWalletValue;
    }

    public List<Trader> getTopBottomTraders(int n) {
        List<Trader> sortedTraders = traderList.stream()
                .sorted(Comparator.comparingDouble(Trader::getProfitLoss).reversed())
                .collect(Collectors.toList());

        List<Trader> result = new ArrayList<>();
        result.addAll(sortedTraders.subList(0, Math.min(n, sortedTraders.size())));
        result.addAll(sortedTraders.subList(Math.max(0, sortedTraders.size() - n), sortedTraders.size()));

        return result;
    }

    public synchronized void buy(Map<String, Object> data) {
        String coin = (String) data.get("coin");
        long quantity = (int) data.get("quantity");
        String walletAddress = (String) data.get("wallet_address");
        logger.info("coin: {}, quantity: {}", coin, quantity);

        Coins coinDetails = getCoinDetails(coin);
        if (coinDetails == null) {
            logger.error(coinNotFOund);
            return;
        }

        if (coinDetails.getCirculatingSupply() < quantity) {
            logger.error("Insufficient supply");
            return;
        }

        Trader trader = traderList.stream()
                .filter(t -> walletAddress.equals(t.getWalletAddress()))
                .findFirst()
                .orElse(null);

        if (trader == null) {
            logger.error(traderNotFound);
            return;
        }

        long coinBalance = trader.getWallet().getOrDefault(coin, 0L);
        trader.getWallet().put(coin, coinBalance + quantity);
        coinDetails.setCirculatingSupply(coinDetails.getCirculatingSupply() - quantity);
    }

    public synchronized void sell(Map<String, Object> data) {
        String coin = (String) data.get("coin");
        long quantity = (long) data.get("quantity");
        String walletAddress = (String) data.get("wallet_address");
        logger.info("coin: {}, quantity: {}", coin, quantity);

        Coins coinDetails = getCoinDetails(coin);
        if (coinDetails == null) {
            logger.error(coinNotFOund);
            return;
        }

        Trader trader = traderList.stream()
                .filter(t -> walletAddress.equals(t.getWalletAddress()))
                .findFirst()
                .orElse(null);

        if (trader == null) {
            logger.error(traderNotFound);
            return;
        }

        long coinBalance = trader.getWallet().getOrDefault(coin, 0L);
        if (coinBalance < quantity) {
            logger.error("Insufficient balance");
            return;
        }
        trader.getWallet().put(coin, coinBalance - quantity);
    }

    public synchronized void updatePrice(Map<String, Object> data) {
        String coin = (String) data.get("coin");
        double price = (double) data.get("price");
        logger.info("coin: {}, price: {}", coin, price);

        Coins coinDetails = getCoinDetails(coin);
        if (coinDetails == null) {
            logger.error(coinNotFOund);
            return;
        }

        coinDetails.setPrice(price);
    }

    public synchronized void addVolume(Map<String, Object> data) {
        String coin = (String) data.get("coin");
        long volume = (int) data.get("volume");
        logger.info("coin: {}, volume: {}", coin, volume);

        Coins coinDetails = getCoinDetails(coin);
        if (coinDetails == null) {
            logger.error("Coin not found");
            return;
        }

        coinDetails.setCirculatingSupply(coinDetails.getCirculatingSupply() + volume);
    }

    private double getCoinValue(String coin, long quantity) {
        Coins coinDetails = getCoinDetails(coin);
        return coinDetails != null ? coinDetails.getPrice() * quantity : 0.0;
    }
}
