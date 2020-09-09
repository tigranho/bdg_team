package packageForConcurency.lockunlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {
    ReentrantLock locker;
    Condition condition;

    public Queue() {
        this.locker = new ReentrantLock();
        this.condition = locker.newCondition();
    }

    int n;
    boolean flag = true;

    public void put(int n) {
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
            System.out.println(n + "put");
            flag = true;
            condition.signal();
        } finally {
            locker.unlock();
        }
    }

    public int get() {
        try {
            locker.lock();
            try {
                while (!flag) {
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(n + "get");
            this.flag = false;
            condition.signal();
            return n;
        } finally {
            locker.unlock();
        }
    }
}
