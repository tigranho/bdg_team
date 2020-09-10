package chapter7.task.b;

public class IncrementInteger {
    private static int counter;
    static boolean isIncremented;

    private synchronized void incrementCounter(){
        if(!isIncremented){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter++;
        isIncremented = false;
        notify();
    }

    public synchronized void printCounter(){
        if(isIncremented){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(counter);
        isIncremented = true;
        notify();
    }

    public static void main(String[] args) {
        IncrementInteger incInteger = new IncrementInteger();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                incInteger.incrementCounter();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                incInteger.printCounter();
            }
        });
        t2.start();

    }
}


