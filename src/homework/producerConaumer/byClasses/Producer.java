package homework.producerConaumer.byClasses;



class Producer implements Runnable{
Queue queue = new Queue();
public Producer(Queue queue){
    this.queue = queue;
    new Thread(this).start();
}
    @Override
    public void run() {
        int i = 0;
        while (i < 100){
          queue.put(i++);
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (queue){

        }
    }
}


