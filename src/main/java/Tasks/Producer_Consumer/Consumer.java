package Tasks.Producer_Consumer;

import java.util.Queue;

public class Consumer extends Thread {
    Queue<String> warehouse;

    public Consumer(Queue<String> warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void consume() throws InterruptedException {
        while (warehouse.size() == 0) {
                wait();
        }
        System.out.println(warehouse.remove() + " is consumed");
        notify();
    }
}
