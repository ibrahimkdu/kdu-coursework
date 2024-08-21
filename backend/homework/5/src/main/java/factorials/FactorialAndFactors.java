package factorials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactorialAndFactors {
    public static final Logger slf4jLogger = LoggerFactory.getLogger(FactorialAndFactors.class);

    public static void main(String[] args) {
        // two threads used here, one for finding factorial and one for finding factors
        Thread factorialThread = new Thread(new FactorialCalculator(6));
        Thread factorsThread = new Thread(new FactorsCalculator(6));
        factorialThread.start();
        factorsThread.start();
        try {
            factorialThread.join();
            factorsThread.join();
        } catch (InterruptedException e) {
            slf4jLogger.error("Main thread interrupted", e);
            Thread.currentThread().interrupt();
        }
        slf4jLogger.info("Main thread finished.");
    }

    private static class FactorialCalculator implements Runnable {
        private int number;

        public FactorialCalculator(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            long factorialResult = calculateFactorial(number);
            slf4jLogger.info("Factorial of {}: {}", number, factorialResult);
        }

        private long calculateFactorial(int n) {
            long factorial = 1;
            for (int i = 1; i <= n; i++) {
                factorial *= i;
            }
            return factorial;
        }
    }

    private static class FactorsCalculator implements Runnable {
        private int number;

        public FactorsCalculator(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            if (!Thread.interrupted()) {
                try {
                    calculateFactors(number);
                } catch (InterruptedException e) {
                    slf4jLogger.error("FactorsCalculator thread interrupted", e);
                    Thread.currentThread().interrupt();
                }
            }
        }

        private void calculateFactors(int n) throws InterruptedException {
            StringBuilder factorsLog = new StringBuilder("Factors of " + n + ": ");
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    factorsLog.append(i).append(" ");
                }
            }
            slf4jLogger.info(factorsLog.toString());
            Thread.sleep(1000); // Simulate some processing time
        }
    }
}
