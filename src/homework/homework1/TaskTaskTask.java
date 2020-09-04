package homework.homework1;

import java.util.Random;

public class TaskTaskTask {
    public TaskTaskTask() throws InterruptedException {
       Thread t = new Thread(this::method);
       t.start();
       t.join();
    }
    static int count;
    public static void main(String[] args) throws InterruptedException {

       new TaskTaskTask();
       new TaskTaskTask();
       new TaskTaskTask();
       new TaskTaskTask();
       new TaskTaskTask();

        System.out.println("\n");
        System.out.println(count);
    }
    public void method(){
        int a = new Random().nextInt(10);
        System.out.println(a + " " + Thread.currentThread().getName());
        count+=a;
    }
}
