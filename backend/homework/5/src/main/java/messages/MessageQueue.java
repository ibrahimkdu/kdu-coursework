package messages;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private Queue<String> messages = new LinkedList<>();

    public synchronized void addMessage(String message) {
        messages.add(message);
        notifyAll();
    }

    public synchronized String getMessage() throws InterruptedException {
        while (messages.isEmpty()) {
            wait();
        }
        return messages.poll();
    }
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();


        Thread[] senders = new Thread[3];
        Thread[] receivers = new Thread[3];

        for (int i = 0; i < 3; i++) {
            senders[i] = new Thread(new MessageSender(messageQueue));
            receivers[i] = new Thread(new MessageReceiver(messageQueue));
        }

        for (Thread sender : senders) {
            sender.start();
        }
        for (Thread receiver : receivers) {
            receiver.start();
        }
    }
}
