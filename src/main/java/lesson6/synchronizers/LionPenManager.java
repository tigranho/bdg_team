package lesson6.synchronizers;

import java.util.concurrent.*;

public class LionPenManager {
    private void removeAnimals() {
        System.out.println("Removing animals");
    }

    private void cleanPen() {
        System.out.println("Cleaning the pen");
    }

    private void addAnimals() {
        System.out.println("Adding animals");
    }

    public void performTask(CyclicBarrier c1, CyclicBarrier c2) {
        try {
            removeAnimals();
            c1.await();
            cleanPen();
            c2.await();
            addAnimals();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            CyclicBarrier c1 = new CyclicBarrier(4);
            CyclicBarrier c2 = new CyclicBarrier(4,
                    () -> System.out.println("*** Pen Cleaned!"));
            LionPenManager manager = new LionPenManager();
            for (int i = 0; i < 4; i++) {
                service.submit(() -> manager.performTask(c1, c2));
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }
}

class LionPenManager2 {
    private void removeAnimals() throws InterruptedException {
        System.out.println("Removing animals");
        TimeUnit.MILLISECONDS.sleep(10);
    }

    private void cleanPen() throws InterruptedException {
        System.out.println("Cleaning the pen");
        TimeUnit.MILLISECONDS.sleep(10);
    }

    private void addAnimals() throws InterruptedException {
        System.out.println("Adding animals");
        TimeUnit.MILLISECONDS.sleep(10);
    }

    public void performTask(Phaser phaser) {
       try {
           removeAnimals();
           phaser.arriveAndAwaitAdvance();
           cleanPen();
           phaser.arriveAndAwaitAdvance();
           addAnimals();
           phaser.arriveAndDeregister();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            Phaser phaser = new Phaser(4);
            LionPenManager2 manager = new LionPenManager2();
            for (int i = 0; i < 4; i++) {
                TimeUnit.SECONDS.sleep(2);
                service.submit(() -> manager.performTask(phaser));
            }
            phaser.forceTermination();
        } finally {
            if (service != null) service.shutdown();
        }
    }
}