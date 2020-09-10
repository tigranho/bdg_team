package concurrency.putGetNumsUsingThreadsAndFlag;

public class ControlThread {
    int num;
    boolean flag = false;
    int getNum(){
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
    int putNum(int num){
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
