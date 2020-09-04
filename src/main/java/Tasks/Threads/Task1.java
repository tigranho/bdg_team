package Tasks.Threads;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    public static void main(String[] args) {
//
//        Summary summary = new Summary();
//
//        for (int i = 0; i < 5; i++)
//            new Thread(Summary::new);
//        System.out.println(summary.getSum());

//        Random random = new Random();
//        var ref = new Object() {
//            int n = 0;
//        };
//        int sum = 0;

//        for (int i = 0; i < 5; i++) {
//            new Thread(() -> {
//                n.set(random.nextInt(10));
//                System.out.println(n);
//            }).start();
//        }

//        new Thread(() -> {
//            ref.n = (random.nextInt(10));
//            System.out.println(ref.n);
//        }).start();
//
//        new Thread(() -> {
//            ref.n = (random.nextInt(10));
//            System.out.println(ref.n);
//        }).start();
//
//        new Thread(() -> {
//            ref.n = (random.nextInt(10));
//            System.out.println(ref.n);
//        }).start();
//
//        new Thread(() -> {
//            ref.n = (random.nextInt(10));
//            System.out.println(ref.n);
//        }).start();
//
//        new Thread(() -> {
//            ref.n = (random.nextInt(10));
//            System.out.println(ref.n);
//        }).start();

//        sum += (ref.n);
//        System.out.println(sum);

        Summary summary = new Summary();

        new Thread(Summary::new).start();
        new Thread(Summary::new).start();
        new Thread(Summary::new).start();
        new Thread(Summary::new).start();
        new Thread(Summary::new).start();

        System.out.println(summary.getSum());
    }
}
class Summary extends Thread{

    private int sum = 0;
    private int n = 0;
    private Random random = new Random();

    public Summary() {
        setSum();
    }

    @Override
    public void run() {
        n = random.nextInt(10);
        notifyAll();
    }

    public int getSum() {
        return sum;
    }

    public synchronized void setSum() {

        for (int i = 0; i < 5; i++){
            sum += n;
        }
    }
}