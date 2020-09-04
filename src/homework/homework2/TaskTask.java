package homework.homework2;

public class TaskTask {
    public static void main(String[] args) throws InterruptedException {
        TaskTask task = new TaskTask();

        service(()->task.put());
        service(()->{ task.get();});


    }
    int n;


    static void service(Runnable r) throws InterruptedException {
      Thread t =  new Thread(r);
      t.start();
      t.join();
    }

   synchronized void put(){
        for (int i = 0;i < 100;i++){
            n+=i;
            while (n==100){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   synchronized void get() {
            while (n==0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(n);
        }
}
