package messages;
import java.util.concurrent.*;
public class MessageExector {
    // Create a single message queue to be shared by MessageSender and MessageReceiver
    public static void main(String[] args)
    {
    MessageQueue messageQueue = new MessageQueue();
    ExecutorService senderThreadPool = Executors.newFixedThreadPool(3);
    ExecutorService receiverThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
        MessageSender sender = new MessageSender(messageQueue);
        senderThreadPool.execute(sender);
    }
        for (int i = 0; i < 3; i++) {
        MessageReceiver receiver = new MessageReceiver(messageQueue);
        receiverThreadPool.execute(receiver);
    }
        senderThreadPool.shutdown();
        receiverThreadPool.shutdown();
}
}
