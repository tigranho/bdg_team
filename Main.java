package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Number number = new Number();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                number.num1 = random.nextInt(9) + 1;

            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                number.num2 = random.nextInt(9) + 1;

            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                number.num3 = random.nextInt(9) + 1;

            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                number.num4 = random.nextInt(9) + 1;

            }
        });
        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                number.num5 = random.nextInt(9) + 1;

            }
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
        int sum;
        sum = number.num1 + number.num2 + number.num3 + number.num4 + number.num5;
        System.out.println(sum);
    }
}
