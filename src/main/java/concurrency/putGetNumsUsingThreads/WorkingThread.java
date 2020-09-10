package concurrency.putGetNumsUsingThreads;

public class WorkingThread {
    int num;
    public int getNum(){
        return num;
    }
    public int putNum(int num){
        this.num = num;
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return num;
    }
}
