package chapter7.task;

/**
 * @author Tigran
 */
public class SumOfRandomNumbers {
    private static int sum = 0;
    static int number1, number2, number3, number4, number5;

    static int randomNumber() {
        int from = 1;
        int to = 10;
        return (int) (from + Math.random() * to);
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Thread1());
        Thread t2 = new Thread(new Thread2());
        Thread t3 = new Thread(new Thread3());
        Thread t4 = new Thread(new Thread4());
        Thread t5 = new Thread(new Thread5());

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        for (Thread t : new Thread[]{t1, t2, t3, t4, t5}) {
            t.join();
        }

        sum = number1 + number2 + number3 + number4 + number5;
        System.out.println(sum);
    }
}

class Thread1 implements Runnable {
    public void run() {
        SumOfRandomNumbers.number1 = SumOfRandomNumbers.randomNumber();
        System.out.println("number1 = " + SumOfRandomNumbers.number1);
    }
}

class Thread2 implements Runnable {
    public void run() {
        SumOfRandomNumbers.number2 = SumOfRandomNumbers.randomNumber();
        System.out.println("number2 = " + SumOfRandomNumbers.number2);
    }
}

class Thread3 implements Runnable {
    public void run() {
        SumOfRandomNumbers.number3 = SumOfRandomNumbers.randomNumber();
        System.out.println("number3 = " + SumOfRandomNumbers.number3);
    }
}

class Thread4 implements Runnable {
    public void run() {
        SumOfRandomNumbers.number4 = SumOfRandomNumbers.randomNumber();
        System.out.println("number4 = " + SumOfRandomNumbers.number4);
    }
}

class Thread5 implements Runnable {
    public void run() {
        SumOfRandomNumbers.number5 = SumOfRandomNumbers.randomNumber();
        System.out.println("number5 = " + SumOfRandomNumbers.number5);
    }
}