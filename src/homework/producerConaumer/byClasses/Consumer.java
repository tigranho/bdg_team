package homework.producerConaumer.byClasses;



class Consumer implements Runnable{
Queue queue = new Queue();
public Consumer(Queue queue){
    this.queue = queue;
    new Thread(this).start();
}
    @Override
    public void run() {
        int i = 0;
        while (i < 100){
         queue.get();
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
