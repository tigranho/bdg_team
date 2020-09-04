package homework.putGetNumsUsingThreads;

public class WorkingThread {
    int num;
    public synchronized int getNum(){
        return num;
    }
    public synchronized int putNum(int num){
        this.num = num;
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return num;
    }
}
