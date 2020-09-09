package packageForConcurency.lockExecutors;

public class Main {
    public static void main(String[] args) {
        Queue queue = new Queue();
        new ProducerConsumer(queue).producer();
        new ProducerConsumer(queue).consumer();
    }
}
