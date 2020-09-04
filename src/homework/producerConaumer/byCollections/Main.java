package homework.producerConaumer.byCollections;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        Thread producerThread = new Thread(()->{
            try {
                producerConsumer.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread consumerThread = new Thread(()->{
            try {
                producerConsumer.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

    }




    static class ProducerConsumer{
        boolean flag = false;
        LinkedList<Integer>list = new LinkedList<>();

        public synchronized void producer() throws InterruptedException {
            int value = 0;
            for (int i = 0;i < 100;i++){
                while (flag){
                    wait();
                }
                list.add(++value);
                System.out.println("producer" + value);
                notify();
                flag = true;
              //  notify();
                Thread.sleep(100);
            }
        }

        public synchronized void consumer() throws InterruptedException {
          for (int i = 0;i < 100;i++){
                while (!flag){
                    wait();
                }
              // int val = list.removeFirst();
               int val = list.get(i);
                System.out.println("consumer" + val);
                flag  = false;
                notify();
                Thread.sleep(100);
            }
        }
    }
}
