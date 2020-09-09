package tasks.concurrencyTasks.incrementingTasks;

/**
 * @author Tatevik Mirzoyan
 */
public class IncrementingTask1 {

    static  int number = 12;
    static void increment(){
        for (int i = 0; i < 1_000_000; i++) {
            number++;
        }
    }
    public static void main(String[] args) {
        Thread thread = new Thread(IncrementingTask1::increment);
        Thread thread1 = new Thread(() -> System.out.println(number));
        thread.start();
        thread1.start();
    }
}
