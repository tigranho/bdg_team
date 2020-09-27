package threads;

public class Task2B {
    public static void main(String[] args) {

        Count count = new Count();
        new Thread(count::set).start();

        new Thread(count::getCount).start();
    }
}
class Count extends Thread {

    private int count = 0;

    Count() {
    }

    public synchronized void getCount() {
        try {
            wait(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }

    public synchronized void set() {
        for (int i = 0; i < 1_000_000; i++) {
//            System.out.println(count++);
            this.count++;
        }
        notifyAll();
    }
}