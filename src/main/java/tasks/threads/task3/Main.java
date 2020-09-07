package tasks.threads.task3;

public class Main {
    public static void main(String[] args) {

        Producer producer = new Producer();
        Consumer consumer = new Consumer(producer);
        producer.start();
        consumer.start();
        producer.setName("Producer - 1");
        consumer.setName("Consumer - 1");
    }
}
