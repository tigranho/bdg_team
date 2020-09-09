import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class IncrementAndWrite {
    private static int sharedInt;

    public static void main(String[] args) {
        ExecutorService service = null;
        ExecutorService service1 = null;
        try {
            service = Executors.newSingleThreadExecutor();
            service1 = Executors.newSingleThreadExecutor();
            for (int i = 0; i < 1000000; i++) {
                service.submit(() -> sharedInt++);
            }
            service1.submit(() -> System.out.println(sharedInt));
        } finally {
            if (service != null) service.shutdown();
            if (service1 != null) service1.shutdown();
        }
    }
}
