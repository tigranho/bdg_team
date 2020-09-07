package concurrency.putGetNumsUsingThreads;

public class GenerateNum implements Runnable{
    public WorkingThread thread;
    public GenerateNum(WorkingThread workinThread){
        this.thread = workinThread;
    }

    @Override
    public void run() {
        synchronized (this){
            for(int i = 0; i < 1_000_000; i++){
                System.out.println("Put " + i);
                thread.putNum(i);
            }
        }
    }
}
