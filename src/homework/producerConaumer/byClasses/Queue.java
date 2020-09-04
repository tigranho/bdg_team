package homework.producerConaumer.byClasses;



class Queue{
    boolean  isSizeValue = true;
    int n;

    synchronized int get(){
        while (!isSizeValue){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("get" + n);
        isSizeValue = false;
        notify();
        return n;
    }

  synchronized void put(int n){
        while (isSizeValue){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
          this.n = n;
        System.out.println("put" + n);
        isSizeValue = true;
        notify();

    }
}

