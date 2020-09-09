package packageForConcurency.lockunlock;

public class Producer {
    Queue queue;

    public Producer(Queue queue) {
        this.queue = queue;
        this.t.start();
    }

    Thread t = new Thread(() -> {
        int i = 0;
        while (i < 100) {
            queue.put(i++);
        }
    });
}

