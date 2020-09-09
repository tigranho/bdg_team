import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class Randomizers {
    private static int sum;

    private static void randomNumberAccumulator() {
        Supplier<Integer> a = () -> new Random().nextInt(10);
        sum += a.get();
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 5; i++) {
                service.submit(Randomizers::randomNumberAccumulator);
            }
        } finally {
            if (service != null) service.shutdown();
        }

        System.out.println(sum);
    }
}
