package Tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task1 {
    private static int number1, number2, number3, number4, number5;

    private static int getRandomNumber() {
        int number;
        while (true) {
            number = new Random().nextInt(11);
            if (number != 0) {
                break;
            }
        }
        return number;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            number1 = getRandomNumber();
        });
        Thread thread2 = new Thread(() -> {
            number2 = getRandomNumber();
        });
        Thread thread3 = new Thread(() -> {
            number3 = getRandomNumber();
        });
        Thread thread4 = new Thread(() -> {
            number4 = getRandomNumber();
        });
        Thread thread5 = new Thread(() -> {
            number5 = getRandomNumber();
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();

        int sum = number1 + number2 + number3 + number4 + number5;
        System.out.println(sum);
    }
}
