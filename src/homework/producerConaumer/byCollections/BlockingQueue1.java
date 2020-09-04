package homework.producerConaumer.byCollections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueue1 {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new LinkedBlockingQueue();
        new Thread(new Producer1(blockingQueue)).start();
        new Thread(new Consumer1(blockingQueue)).start();


    }


}


class Producer1 implements Runnable{
BlockingQueue blockingQueue;
     boolean flag;
     int n = 0;
    public Producer1(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    @Override
    public void run() {
        int i = 0;
     for (;i < 100;i++){

            try {
                blockingQueue.put(++n);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("put" + n);
              //  flag = true;
              //  notify();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class Consumer1 implements Runnable {
    BlockingQueue blockingQueue;
    boolean flag;

    public Consumer1(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
       // int i = 0;
    for (int i = 0;i < 100;i++){

            try {
                System.out.println("take" + blockingQueue.take());
              //  flag = false;
                Thread.sleep(100);
               // notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
