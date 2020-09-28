package tasks.concurrency.randomMumbers;

/**
 * @author Tatevik Mirzoyan
 */
public class SumOfRandomNumbers {
    static int sum;

    public static void main(String[] args) {

        Thread thread1 = new Thread(SumOfRandomNumbers::getRandomNumber);
        Thread thread2 = new Thread(SumOfRandomNumbers::getRandomNumber);
        Thread thread3 = new Thread(SumOfRandomNumbers::getRandomNumber);
        Thread thread4 = new Thread(SumOfRandomNumbers::getRandomNumber);
        Thread thread5 = new Thread(SumOfRandomNumbers::getRandomNumber);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            System.out.println("Throws Interrupted Exception");
        }

        System.out.println("\b\b = " + sum);
        System.out.println("The average of numbers is " + (double)sum / 5);
    }

    static synchronized void getRandomNumber() {
        int num = (int) (Math.random() * 10) + 1;
        System.out.print(num + " + ");
        sum += num;
    }


}
