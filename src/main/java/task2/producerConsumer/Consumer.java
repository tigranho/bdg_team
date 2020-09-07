package task2.producerConsumer;

import java.util.Queue;

public class Consumer extends Thread {
    Queue<String> warehouse;

    public Consumer(Queue<String> warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (warehouse.size() == 0) {
                    wait();
                }
                String elem = warehouse.poll();

                System.out.println("Consumer consumed " + elem);
                notify();
                Thread.sleep(1000);
            }
        }
    }
}
