package com.bdg.Tasks.Threads;

public class Task2 {
    public static void main(String[] args) {
        RandomN count = new RandomN();

        Thread t = new Thread(() -> {
            for (int i = 0; i < 1000000; i++){
                count.setN();
            }
        });

        t.notify();
        t.start();

        Thread t1 = new Thread(() ->{

            System.out.println(count.getN());

        });


        t1.start();
    }
}
class RandomN {

    private int n = 0;

    public void setN() {
        n++;
    }

    public int getN() {
        return n;
    }
}