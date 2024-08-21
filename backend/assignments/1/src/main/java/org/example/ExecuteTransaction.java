package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class ExecuteTransaction implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteTransaction.class);

    private Transaction transaction;
    private CryptoMarket cryptoMarket;
    private CountDownLatch latch;

    public ExecuteTransaction() {
    }

    public ExecuteTransaction(CryptoMarket cryptoMarket, Transaction transaction, CountDownLatch latch) {
        this.transaction = transaction;
        this.cryptoMarket = cryptoMarket;
        this.latch = latch;
    }

    @Override
    public void run() {
        logger.info("Executing transaction: {}", transaction);
        switch (transaction.getType()) {
            case BUY:
                cryptoMarket.buy(transaction.getData());
                break;
            case SELL:
                cryptoMarket.sell(transaction.getData());
                break;
            case UPDATE_PRICE:
                cryptoMarket.updatePrice(transaction.getData());
                break;
            case ADD_VOLUME:
                cryptoMarket.addVolume(transaction.getData());
                break;
        }
        latch.countDown();
        logger.info("Countdown latch: {}", latch.getCount());
    }
}
