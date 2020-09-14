package ProducerConsumer;

import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {
        WareHouse wareHouse = new WareHouse(new ArrayDeque<String>());
        Thread producer = new Producer(wareHouse);
        Thread producer1 = new Producer(wareHouse);
        Thread producer2 = new Producer(wareHouse);
        Thread consumer = new Consumer(wareHouse);
        producer.start();
        producer1.start();
        producer2.start();
        consumer.start();
    }
}