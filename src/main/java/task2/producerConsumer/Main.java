package task2.producerConsumer;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Queue<String> warehouse = new ArrayDeque<>();

        Producer p = new Producer(warehouse);
        Consumer c = new Consumer(warehouse);

        p.start();
        c.start();

        p.join();
        c.join();

    }
}
