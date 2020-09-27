package threads.task3.modyfied;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<String> blockingQueue;

    Producer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                String data = LocalDateTime.now().toString();
                blockingQueue.put(data);
                System.out.println("Producer produced data");
            }
        } catch (InterruptedException ignored) {
            System.out.println("Producer thread is interrupted");
        }
    }
}