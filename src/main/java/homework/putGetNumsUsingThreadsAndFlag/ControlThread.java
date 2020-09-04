package homework.putGetNumsUsingThreadsAndFlag;

public class ControlThread {
    int num;
    boolean flag = false;
    synchronized int getNum(){
        if(!flag){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        flag = false;
        notify();
        return num;
    }
    synchronized int putNum(int num){
        if(flag){
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
        notify();
        return num;
    }
}
