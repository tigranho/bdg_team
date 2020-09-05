package chapter7.task.a;

public class IncrementInteger {
    public static int count;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                count++;
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            while (t1.isAlive()){
                System.out.println(count);
            }
        });
        t2.start();


        t1.join();
        t2.join();
    }
}
