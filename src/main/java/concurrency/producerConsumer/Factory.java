package concurrency.producerConsumer;

import java.util.ArrayDeque;

public class Factory {
    private ArrayDeque<String> arrayDeque;
    public Factory(ArrayDeque arrayDeque){
        this.arrayDeque = arrayDeque;
    }

    public ArrayDeque<String> getArrayDeque() {
        return arrayDeque;
    }


    void consume(){
        while (arrayDeque.size() == 0){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("consumed " + arrayDeque.poll());
        notifyAll();
    }
    void produce(int i){
        while (arrayDeque.size() == 100){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        arrayDeque.offer(" " + i);
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        notifyAll();
    }
    public static void main(String[] args) {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        Factory factory = new Factory(arrayDeque);
        Thread producer1 = new Thread(new Producer(factory));
        Thread consumer1 = new Thread(new Consumer(factory));

        producer1.start();
        consumer1.start();
        try{
            producer1.join();
            consumer1.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
