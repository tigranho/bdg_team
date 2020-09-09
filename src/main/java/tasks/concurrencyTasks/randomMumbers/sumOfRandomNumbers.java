package tasks.concurrencyTasks.randomMumbers;

/**
 * @author Tatevik Mirzoyan
 */
public class sumOfRandomNumbers {
    static int sum;

    public static void main(String[] args) {

        Thread thread1 = new Thread(sumOfRandomNumbers::getRandomNumber);
        Thread thread2 = new Thread(sumOfRandomNumbers::getRandomNumber);
        Thread thread3 = new Thread(sumOfRandomNumbers::getRandomNumber);
        Thread thread4 = new Thread(sumOfRandomNumbers::getRandomNumber);
        Thread thread5 = new Thread(sumOfRandomNumbers::getRandomNumber);

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
            e.printStackTrace();
        }

        System.out.println(sum);
    }

    static synchronized void getRandomNumber() {
        sum += (int) (Math.random() * 10) + 1;
    }


}
