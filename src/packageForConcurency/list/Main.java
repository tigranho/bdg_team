package packageForConcurency.list;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(() -> {
            try {
                producerConsumer.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (service != null) {
                    service.shutdown();
                }
            }
        });


        service.submit(() -> {
            try {
                producerConsumer.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (service != null) {
                    service.shutdown();
                }
            }
        });
    }


    static class ProducerConsumer {
        ReentrantLock locker;
        Condition condition;

        public ProducerConsumer() {
            this.locker = new ReentrantLock();
            this.condition = locker.newCondition();

        }

        boolean flag = false;
        LinkedList<Integer> list = new LinkedList<>();

        public void producer() throws InterruptedException {
            try {
                locker.lock();
                int value = 0;
                for (int i = 0; i < 100; i++) {
                    while (flag) {
                        condition.await();
                    }
                    list.add(++value);
                    System.out.println("producer" + value);
                    condition.signal();
                    flag = true;
                    Thread.sleep(100);
                }
            } finally {
                locker.unlock();
            }
        }

        public void consumer() throws InterruptedException {
            try {
                locker.lock();
                for (int i = 0; i < 100; i++) {
                    while (!flag) {
                        condition.await();
                    }
                    int val = list.get(i);
                    System.out.println("consumer" + val);
                    flag = false;
                    condition.signal();
                    Thread.sleep(100);
                }
            } finally {
                locker.unlock();
            }
        }
    }
}
