package chapter7.task.producer_consumer_pattern;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author Tigran
 */
public class Main {
    public static void main(String[] args) {
        //size must be 100;
        Queue<String> warehouse = new ArrayDeque<>();

    }

}

class Producer extends Thread {

    private Queue<String> warehouse;

    public Producer(Queue<String> warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (warehouse.size() < 100) {
            warehouse.add("Produced  e1");
        }
    }
}

class Consumer extends Thread {
    private Queue<String> warehouse;

    public Consumer(Queue<String> warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (warehouse.size() > 0) {
            System.out.println("Consumed e1 " + warehouse.poll());
        }
    }
}



