package homework.producerConaumer.byClasses;


class Main{
    public static void main(String[] args) {
        Queue queue = new Queue();
        new Producer(queue);
        new Consumer(queue);
    }
}
