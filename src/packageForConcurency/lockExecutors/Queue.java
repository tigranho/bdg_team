package packageForConcurency.lockExecutors;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {
    int n;
    boolean flag = true;
    ReentrantLock locker;
    Condition condition;

    public Queue() {
        this.locker = new ReentrantLock();
        this.condition = this.locker.newCondition();
    }

    int get() {
        try {
            locker.lock();
            try {
                while (!flag) {
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get" + n);
            flag = false;
            condition.signal();
            return n;

        } finally {

        }
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
        } finally {
            locker.unlock();
        }
    }
}
