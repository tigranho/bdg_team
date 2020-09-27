package threads.task3.standart;

public class Consumer extends Thread {

    private final Producer producer;

    public Consumer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String data = producer.consumer();
                System.out.println("Consumed by: " + Thread.currentThread().getName() + " data: " + data);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}