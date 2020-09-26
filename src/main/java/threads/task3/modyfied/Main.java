package threads.task3.modyfied;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        final BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        new Thread(new Producer(blockingQueue)).start();
        new Thread(new Consumer(blockingQueue)).start();
    }
}