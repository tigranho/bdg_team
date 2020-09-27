package threads.task3.standart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Producer extends Thread {

    private static final int SIZE = 3;
    private final List<String> wharehouse = new ArrayList<>();

    @Override
    public void run() {

        try {
            while (true) {
                produce();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void produce() throws InterruptedException {

        while (wharehouse.size() == SIZE) {
            System.out.println("Queue limit reached. Waiting for consumer");
            wait();
            System.out.println("Producer got notification from consumer");
        }

        String data = LocalDateTime.now().toString();
        wharehouse.add(data);
        System.out.println("Producer produced data");
        notifyAll();
    }

    public synchronized String consumer() throws InterruptedException {
        notifyAll();
        while (wharehouse.isEmpty()) {
            wait();
        }
        String data = wharehouse.get(0);
        wharehouse.remove(data);
        return data;
    }
}