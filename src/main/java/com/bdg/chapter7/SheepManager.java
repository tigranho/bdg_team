package com.bdg.chapter7;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SheepManager {
    // private int sheepCount = 0;
    private AtomicInteger sheepCount = new AtomicInteger(0);

    private void incrementAndReport() {
        synchronized (this) {
            System.out.print(sheepCount.incrementAndGet() + " ");
        }

    }

    public static synchronized void printDaysWork() {
        System.out.print("Finished work");
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(20);
            SheepManager manager = new SheepManager();
            for (int i = 0; i < 10; i++)
                //synchronized (manager) {
                service.submit(() -> manager.incrementAndReport());
            // }
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
