package packageForConcurency.lockThreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {
    ReentrantLock locker;
    Condition condition;
    int n;
    boolean flag = true;

    public Queue() {
        this.locker = new ReentrantLock();
        this.condition = locker.newCondition();
    }

    void put(int n) {
        try {
            locker.lock();
            try {
                while (flag) {
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.n = n;
            System.out.println("put" + n);
            flag = true;
            condition.signal();
            // flag = true;
        } finally {
            locker.unlock();
        }
    }

    int get() {
        try {
            locker.lock();
            try {
                while (!flag)
                    condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("get" + n);
            flag = false;
            condition.signal();
            return n;
        } finally {
            locker.unlock();
        }
    }
}
