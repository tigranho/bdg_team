package tasks.concurrencyTasks.producerConsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tatevik Mirzoyan
 */
public class Warehouse {
    public static void main(String[] args) {
        List<String> warehouse = new ArrayList<>(5);

        Thread threadProducer1 = new Thread(new Producer(warehouse));
        Thread threadProducer2 = new Thread(new Producer(warehouse));
        Thread threadProducer3 = new Thread(new Producer(warehouse));

        Thread threadConsumer1 = new Thread(new Consumer(warehouse));
        Thread threadConsumer2 = new Thread(new Consumer(warehouse));

        threadProducer1.start();
        threadProducer2.start();
        threadProducer3.start();
        threadConsumer1.start();
        threadConsumer2.start();

        try {
            threadProducer1.join();
            threadProducer2.join();
            threadProducer3.join();
            threadConsumer1.join();
            threadConsumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(warehouse);
    }

}
