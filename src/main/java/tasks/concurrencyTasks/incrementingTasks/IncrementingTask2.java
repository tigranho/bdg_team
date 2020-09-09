package tasks.concurrencyTasks.incrementingTasks;

/**
 * @author Tatevik Mirzoyan
 */
public class IncrementingTask2 {
    static int number = 0;

    synchronized void increment() {
        for (int i = 0; i < 100; i++) {
            number++;
        }
            if (number == 100) {
                notify();
        }
    }

    synchronized void print() {
        if (number == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(number);
    }

    public static void main(String[] args) {
        IncrementingTask2 object = new IncrementingTask2();
        Thread thread = new Thread(object::increment);
        Thread thread1 = new Thread(object::print);
        thread.start();
        thread1.start();
    }
}
