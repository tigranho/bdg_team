package task2;

import java.util.Random;

public class GenerateRandomNumberTask1 implements Runnable {
    int number;
    Random rand = new Random();

    @Override
    public void run() {
        number = rand.nextInt(10) + 1;
    }

    int getNumber(){
        return number;
    }

    public static void main(String[] args) {
        int total = 0;

        GenerateRandomNumberTask1 r1 = new GenerateRandomNumberTask1();
        GenerateRandomNumberTask1 r2 = new GenerateRandomNumberTask1();
        GenerateRandomNumberTask1 r3 = new GenerateRandomNumberTask1();
        GenerateRandomNumberTask1 r4 = new GenerateRandomNumberTask1();
        GenerateRandomNumberTask1 r5 = new GenerateRandomNumberTask1();

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);
        Thread t5 = new Thread(r5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        total = r1.getNumber() + r2.getNumber() + r3.getNumber() + r4.getNumber() + r5.getNumber();

        System.out.println(total);
    }
}
