package Tasks.Producer_Consumer;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        Deque<String> warehouse = new ArrayDeque<>();

        Producer producer1 = new Producer(warehouse);
        Producer producer2 = new Producer(warehouse);
        Consumer consumer1 = new Consumer(warehouse);
        Consumer consumer2 = new Consumer(warehouse);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
