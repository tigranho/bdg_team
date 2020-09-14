package ProducerConsumer;

import java.util.Queue;

public class WareHouse {
    Queue<String> wareHouse;

    public WareHouse(Queue<String> wareHouse) {
        this.wareHouse = wareHouse;
    }

    public synchronized void produce() {
        while (wareHouse.size() == 100) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException thrown");
            }
        }
        wareHouse.offer("Produced element");
        notify();
    }

    public synchronized void consume() {
        while (wareHouse.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException thrown");
            }
        }
        System.out.println("Consumed the " + wareHouse.poll());
        notify();
    }
}
