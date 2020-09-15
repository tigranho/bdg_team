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
        notify();
    }

    synchronized void print() {
        while (number < 100) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Throws Interrupted Exception");
            }
        }
        System.out.println(number);
    }

    public static void main(String[] args) {
        IncrementingTask2 object = new IncrementingTask2();
        Thread thread1 = new Thread(object::increment);
        Thread thread2 = new Thread(object::print);
        thread1.start();
        thread2.start();
    }
}
