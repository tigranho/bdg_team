package packageForConcurency.lockExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {
    Queue queue;

    public ProducerConsumer(Queue queue) {
        this.queue = queue;
        this.service = Executors.newSingleThreadExecutor();
    }

    ExecutorService service = null;


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
                try {
                    int i = 0;
                    while (i < 100) {
                        queue.get();
                    }
                } finally {
                    if (service != null) {
                        service.shutdown();
                    }
                }
            });
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}



