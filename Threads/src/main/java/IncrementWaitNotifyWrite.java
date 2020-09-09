public class IncrementWaitNotifyWrite {
    private static Integer sharedInt = 0;

    private static final Object o = new Object();

    public static void main(String[] args) {

        Thread a = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                sharedInt++;
            }
            synchronized (o) {
                o.notify();
            }
        });

        Thread b = new Thread(() -> {
            synchronized (o) {
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(sharedInt);
        });

        a.start();
        b.start();
    }
}