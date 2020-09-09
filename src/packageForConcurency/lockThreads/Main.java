package packageForConcurency.lockThreads;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue();
        new ProducConsum(queue).producer();
        new ProducConsum(queue).consumer();
    }
}
