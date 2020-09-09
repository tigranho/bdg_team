package ProducerConsumer;

import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {
        WareHouse wareHouse = new WareHouse(new ArrayDeque<String>());
        Thread producer = new Thread(wareHouse::produce);
        Thread producer1 = new Thread(wareHouse::produce);
        Thread producer2 = new Thread(wareHouse::produce);
        Thread consumer = new Thread(wareHouse::consume);
        producer.start();
        producer1.start();
        producer2.start();
        consumer.start();
    }
}