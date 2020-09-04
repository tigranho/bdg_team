package com.bdg.HomeWork;

import java.util.Random;

public class Task1Sum implements Runnable {
    int a;

    public Task1Sum(int a) {
        this.a = a;
    }

    public void run() {

        addRandom();
    }

    public void addRandom() {
        Random rand = new Random();
        int n = rand.nextInt(10) + 1;
        System.out.println("number generated: " + n);
        synchronized (this) {
            a += n;
        }
    }


    public static void main(String[] args) {
        int base = 0;

        Task1Sum sum2 = new Task1Sum(base);

        Thread t1 = new Thread(sum2);
        Thread t2 = new Thread(sum2);
        Thread t3 = new Thread(sum2);
        Thread t4 = new Thread(sum2);
        Thread t5 = new Thread(sum2);

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("final result: " + sum2.a);
    }
}

