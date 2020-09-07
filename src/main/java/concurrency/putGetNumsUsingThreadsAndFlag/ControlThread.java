package concurrency.putGetNumsUsingThreadsAndFlag;

public class ControlThread {
    int num;
    boolean flag = false;
    synchronized int getNum(){
        while (!flag){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        flag = false;
        notifyAll();
        return num;
    }
    synchronized int putNum(int num){
        while (flag){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.num = num;
        flag = true;
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        notifyAll();
        return num;
    }
}
