package chapter7;

import java.util.concurrent.atomic.AtomicInteger;


public class Main{
    public static void main(String[] args) {

        Counter count = new Counter();
        Class<Counter> counterClass = Counter.class;

        int integer = new AtomicInteger().incrementAndGet();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++){
                count.increment();
            }
        });

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++){
                count.increment();
            }
        });

        thread.start();
        thread1.start();
    }
}
class Counter{
    int count = 0;

    synchronized void increment(){
        count++;
    }
}