package messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageSender implements Runnable {
    private static final Logger slf4jLogger = LoggerFactory.getLogger(MessageSender.class);

    private MessageQueue messageQueue;

    public MessageSender(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                String message = "Message from sender " + Thread.currentThread().getId() + ": " +
                        "is sent at " + System.currentTimeMillis();
                messageQueue.addMessage(message);
                slf4jLogger.info("Sent: {}", message);
                Thread.sleep(1000); // Simulate some processing time
            }
        } catch (InterruptedException e) {

            slf4jLogger.error("Sender thread interrupted", e);
            Thread.currentThread().interrupt();
        } finally {

            slf4jLogger.info("Sender thread finished.");
        }
    }
}
