package homework.putGetNumsUsingThreads;

public class GetNum implements Runnable{
    public WorkingThread thread;
    public GetNum(WorkingThread thread){
        this.thread = thread;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1_000_000; i++){
            int g = thread.getNum();
            System.out.println("Get " + g);
//            thread.getNum();
        }
    }
}
