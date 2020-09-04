package com.bdg.HomeWork;

public class Task2 implements Runnable {
    public static int i = 0;

    public synchronized void run (){
        for (i = 0; i<1000000;){
            i++;
        }
        if (i == 1000000){
            notify();
        }
    }
    static class Display implements Runnable{
        public synchronized void run(){
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Task2 inc = new Task2();
        Display d = new Display();
        Thread t = new Thread(inc);
        Thread r = new Thread(d);
        t.start();
        r.start();
        synchronized (r){
            r.wait();
        }


    }

}
