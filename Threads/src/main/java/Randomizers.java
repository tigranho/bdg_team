import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Randomizers {
    private static AtomicInteger sum = new AtomicInteger(0);

    private static void randomNumberAccumulator() {
        IntSupplier a = () -> new Random().nextInt(10);
        int i = a.getAsInt();
//        System.out.println("Random Number: " + i);
        sum.addAndGet(i);
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

        System.out.println("Sum: " + sum.get());
    }
}
