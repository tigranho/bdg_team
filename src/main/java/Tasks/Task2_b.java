package Tasks;

public class Task2_b {
    private static Integer num = 0;

    private static final Object lock = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                ++num;
            }
            synchronized (lock) {
                lock.notify();
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(num);
        });

        thread2.start();
        thread1.start();
    }
}
