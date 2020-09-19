package threads;

public class Task1 {
    public static void main(String[] args) throws InterruptedException {
        Result result = new Result();

        Thread thread1 = new Thread(() -> {
            int n = (int)(Math.random()*10) + 1;
            System.out.println("thread1: " + n);
            result.sum += n;
        });

        Thread thread2 = new Thread(() -> {
            int n = (int)(Math.random()*10) + 1;
            System.out.println("thread2: " + n);
            result.sum += n;
        });

        Thread thread3 = new Thread(() -> {
            int n = (int)(Math.random()*10) + 1;
            System.out.println("thread3: " + n);
            result.sum += n;
        });

        Thread thread4 = new Thread(() -> {
            int n = (int)(Math.random()*10) + 1;
            System.out.println("thread4: " + n);
            result.sum += n;
        });

        Thread thread5 = new Thread(() -> {
            int n = (int)(Math.random()*10) + 1;
            System.out.println("thread5: " + n);
            result.sum += n;
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();


        System.out.println(result.sum);
    }
}

class Result {
    int sum = 0;
}
