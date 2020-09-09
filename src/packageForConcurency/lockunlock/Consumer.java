package packageForConcurency.lockunlock;

public class Consumer {
    Queue queue;

    public Consumer(Queue queue) {
        this.queue = queue;
        this.t.start();
    }

    Thread t = new Thread(() -> {
        int i = 0;
        while (i < 100) {
            queue.get();
        }
    });
}
