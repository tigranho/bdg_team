package threads.task3.modyfied;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue<String> blockingQueue;

    Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            //noinspection InfiniteLoopStatement
            while (true) {

                String data = blockingQueue.take();
                System.out.println("Consumed by: " + Thread.currentThread().getName() + " data: " + data);
                blockingQueue.remove(data);
            }
        } catch (InterruptedException ignored) {
            System.out.println("Consumer thread is interrupted");
        }
    }
}