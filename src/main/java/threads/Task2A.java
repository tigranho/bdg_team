package threads;

public class Task2A {
    public static void main(String[] args) {

        Counter counter = new Counter();

        new Thread(() -> {
            System.out.println(counter.getCount());
        }).start();

    }
}
class Counter extends Thread {
    private int count = 0;

    Counter() {
        this.start();
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {

        for (int i = 0; i < 1_000_000; i++) {
            count++;
        }
    }
}