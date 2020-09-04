package com.bdg.Tasks.Threads;

import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    public static void main(String[] args) {

        RndNnumber rndNnumber = new RndNnumber();

        new Thread(new RndNnumber()).start();
        new Thread(new RndNnumber()).start();
        new Thread(new RndNnumber()).start();
        new Thread(new RndNnumber()).start();
        new Thread(new RndNnumber()).start();

        int n = rndNnumber.n;

        Future
        System.out.println(n);
    }
}
class RndNnumber implements Runnable{

    private Random random = new Random();
    int n = 0;

    @Override
    public void run() {
        n = random.nextInt(10);
    }
}