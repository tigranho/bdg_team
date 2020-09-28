package tasks.concurrency.producerConsumer;

import java.util.List;

/**
 * @author Tatevik Mirzoyan
 */
public class Producer extends Thread {
    List<String> warehouse;
    int size;

    Producer(List<String> warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        add();
    }

    private synchronized void add() {
        for (int i = 0; i < warehouse.size(); i++) {
            while (size < warehouse.size()) {
                warehouse.add("Produce item " + i);
                size++;
                warehouse.notifyAll();
            }
        }
            while (size == warehouse.size()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("Throws Interrupted Exception");
                }
            }
        }
    }
