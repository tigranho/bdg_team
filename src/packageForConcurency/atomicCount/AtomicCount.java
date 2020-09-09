package packageForConcurency.atomicCount;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCount {
   static int sum = 0;

    public static void main(String[] args) {
        AtomicCount atomicCount = new AtomicCount();
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(()->{
            for (int i = 0;i < 5;i++){
                atomicCount.method();
            }
            System.out.println(sum + "sum");
        });
    }

    void method(){
        int i = new Random().nextInt(10);
        AtomicInteger atomicInteger = new AtomicInteger(i);
        System.out.println(atomicInteger + "atomicInteger");
        sum+=atomicInteger.get();
    }
}
