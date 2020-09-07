package task2.producerConsumer;

import java.util.Queue;

public class Producer extends Thread {

    Queue<String> warehouse;

    public Producer(Queue<String> warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void produce() throws InterruptedException {
        String elem = "Product produced";
        while (true) {
            synchronized (this) {
                while (warehouse.size() == 100) {
                    wait();
                }
                warehouse.offer(elem);
                System.out.println(elem);
                notify();
                Thread.sleep(1000);
            }
        }
    }
}
