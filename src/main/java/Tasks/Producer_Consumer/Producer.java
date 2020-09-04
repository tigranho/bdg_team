package Tasks.Producer_Consumer;

import java.util.Queue;

public class Producer extends Thread {
    Queue<String> warehouse;

    public Producer(Queue<String> warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void produce() throws InterruptedException {
        while (warehouse.size() == 100) {
            wait();
        }

        warehouse.offer("An item");
        System.out.println("An item is produced");
        notify();
    }
}
