package Tasks;

public class Task2_a {
    private static int num = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                ++num;
            }
        });
        Thread thread2 = new Thread(() -> {
            System.out.println(num);
        });

        thread1.start();
        thread2.start();
    }
}
