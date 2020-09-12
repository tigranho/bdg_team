package lesson8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        new Main().checkPalindrome("abacaba");
    }

    public static int num(int num) {
        return String.valueOf(num).chars()
                .map(c -> Integer.parseInt(Character.toString(c)))
                .sum();
    }

    boolean checkPalindrome(String inputString) {
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == inputString.charAt(inputString.length() - 1)) continue;
            else return false;
        }
        return true;
    }
}

class Bank {
    static int cookies = 0;

    public synchronized void deposit(int amount) {
        cookies += amount;
    }

    public static synchronized void withdrawal(int amount) {
        cookies -= amount;
    }

    public static void main(String[] amount) throws Exception {
        ExecutorService teller =
                Executors.newScheduledThreadPool(50);
        Bank bank = new Bank();
        for (int i = 0; i < 25; i++) {
            teller.submit(() -> bank.deposit(5));
            teller.submit(() -> bank.withdrawal(5));
        }
        teller.shutdown();
        teller.awaitTermination(10, TimeUnit.SECONDS);
        System.out.print(bank.cookies);
    }
}

///*
//Consider integer numbers from 0 to n - 1 written down along the circle
//in such a way that the distance between any two neighboring numbers is
//equal (note that 0 and n - 1 are neighboring, too).
//Given n and firstNumber, find the number which is written
//in the radially opposite position to firstNumber.
// */
//public int circleOfNumbers(int n, int firstNumber) {
//    //some logic
//}
//
////For n = 10 and firstNumber = 2, method must return 7