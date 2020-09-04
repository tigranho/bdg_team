package homework.producerConaumer.byCollections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueue2 {
    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        new Thread(()->{
            try {
               producerConsumer.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
               producerConsumer.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    static class ProducerConsumer{
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
        int n = 0;
        void put() throws InterruptedException {
    for (int i = 0;i < 100;i++){
                blockingQueue.put(++n);
               System.out.println("put" + n);
                Thread.sleep(100);
            }
        }

        void get() throws InterruptedException {

           for (int i = 0;i < 100;i++){
                System.out.println("get" +blockingQueue.take());
                Thread.sleep(100);
            }
        }
    }
}
