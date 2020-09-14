public class IncrementWaitNotifyWrite {
    private static Integer sharedInt = 0;
    private volatile static boolean checked = false;

    private static final Object o = new Object();

    public static void main(String[] args) {

        Thread a = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                sharedInt++;
            }
            checked = true;
            synchronized (o) {
                o.notify();
            }
        });

        Thread b = new Thread(() -> {
            while (!checked) {
                synchronized (o) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        System.out.println("InterruptedException thrown");
                    }
                }
            }
            System.out.println(sharedInt);
        });

        a.start();
        b.start();
    }
}