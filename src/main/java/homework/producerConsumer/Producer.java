package homework.producerConsumer;

import java.util.ArrayDeque;

public class Producer implements Runnable{
    private volatile ArrayDeque<String> arrayDeque;
    private Factory factory;
    public Producer(Factory factory){
        this.arrayDeque = factory.getArrayDeque();
        this.factory = factory;
    }
    @Override
    public void run() {
        synchronized (this) {
            int i = 0;
            while (true){
                factory.produce(++i);
                System.out.println("Produced "  + i);
            }
        }
    }
}
