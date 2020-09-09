package packageForConcurency.lockThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducConsum {
    Queue queue;
    ExecutorService service = null;

    public ProducConsum(Queue queue) {
        this.queue = queue;
        this.service = Executors.newSingleThreadExecutor();
    }


    void producer() {
        try {
            service.submit(() -> {
                int i = 0;
                while (i < 100) {
                    queue.put(i++);
                }
            });
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    void consumer() {
        try {
            service.submit(() -> {
                int i = 0;
                while (i < 100) {
                    queue.get();
                }
            });
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

}

