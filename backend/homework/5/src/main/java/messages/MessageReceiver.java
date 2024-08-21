package messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageReceiver implements Runnable {
    private static final Logger slf4jLogger = LoggerFactory.getLogger(MessageReceiver.class);

    private MessageQueue messageQueue;

    public MessageReceiver(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                String message = messageQueue.getMessage();
                slf4jLogger.info("Received: {}", message);
                Thread.sleep(1000); // Simulate some processing time
            }
        } catch (InterruptedException e) {
            // Either re-interrupt this method or rethrow the InterruptedException
            slf4jLogger.error("Receiver thread interrupted", e);
            Thread.currentThread().interrupt(); // Re-interrupt the thread
        } finally {
            // Add an end condition to the loop, or perform cleanup here
            slf4jLogger.info("Receiver thread finished.");
        }
    }
}
