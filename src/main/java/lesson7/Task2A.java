package lesson7;

public class Task2A {

    private static int counter;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 1000000; i++) counter++;
        }).start();
        new Thread(() -> {
            System.out.println(counter);
        }).start();
    }
}
