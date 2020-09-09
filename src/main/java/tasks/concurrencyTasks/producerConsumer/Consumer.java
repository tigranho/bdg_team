package tasks.concurrencyTasks.producerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Tatevik Mirzoyan
 */
public class Consumer extends Thread {
    List<String> warehouse;

    Consumer(List<String> warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        remove();
    }

    private synchronized void remove() {
        while (warehouse.size() > 0) {
            String removedItem = warehouse.remove(0);
            System.out.println("Consume" + removedItem);
            warehouse.notifyAll();
        }
        while (warehouse.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
