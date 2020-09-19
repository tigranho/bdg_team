package threads;

public class Task2b {

    private static int num = 0;
    private static volatile boolean transfer = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            for(int i = 0; i < 1000000; i++) {
                num++;
            }
            transfer = true;
        }).start();

        new Thread(() -> {
            while(!transfer) {
                Thread.onSpinWait();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println(num);
        }).start();


    }
}
