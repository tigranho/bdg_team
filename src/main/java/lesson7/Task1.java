package lesson7;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task1 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        Random random = new Random();
        try {
            Future<Integer> num1 = service.submit(() -> random.nextInt(10));
            Future<Integer> num2 = service.submit(() -> random.nextInt(10));
            Future<Integer> num3 = service.submit(() -> random.nextInt(10));
            Future<Integer> num4 = service.submit(() -> random.nextInt(10));
            Future<Integer> num5 = service.submit(() -> random.nextInt(10));
            System.out.printf("%d %d %d %d %d %n", num1.get(), num2.get(), num3.get(), num4.get(), num5.get());
            int sum = num1.get() + num2.get() + num3.get() + num4.get() + num5.get();
            System.out.println("Sum: " + sum);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
        } finally {
            service.shutdown();
        }
    }
}
