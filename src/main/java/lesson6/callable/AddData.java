package lesson6.callable;

import java.util.concurrent.*;

public class AddData {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            Future<Integer> result = service.submit(() -> 30 + 11);
            System.out.println(result.get());
        } finally {
            if (service != null) service.shutdown();
        }
        service.awaitTermination(1, TimeUnit.MILLISECONDS);
        if (service.isTerminated())
            System.out.println("All tasks finished");
        else
            System.out.println("At least one task is still running");
    }
}
