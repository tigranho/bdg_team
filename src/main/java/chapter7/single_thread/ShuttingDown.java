package chapter7.single_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tigran
 */
public class ShuttingDown {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        System.out.println("ShutDown is " +service.isShutdown());
        System.out.println("Terminated is " +service.isTerminated());

        service.execute(() -> System.out.println("Runnable state"));

        service.shutdown();

        System.out.println("ShutDown is " +service.isShutdown());
        System.out.println("Terminated is " +service.isTerminated());




    }
}
