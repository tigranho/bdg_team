package ThreadTask;

public class Task2a {

    private static int num = 0;


    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            for(int i = 0; i < 1000000; i++) {
                num++;
            }
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(num);
        }).start();
    }
}

