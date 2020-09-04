package homework.producerConsumer;

import java.util.ArrayDeque;

public class Consumer implements Runnable{
    private volatile ArrayDeque<String> arrayDeque;
    private Factory factory;
    public Consumer(Factory factory){
        this.arrayDeque = factory.getArrayDeque();
        this.factory = factory;
    }
    @Override
    public void run() {
        synchronized (this) {
            while (true){
                factory.consume();
            }
        }
    }
}
