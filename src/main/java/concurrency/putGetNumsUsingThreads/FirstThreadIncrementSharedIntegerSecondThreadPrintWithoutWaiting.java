package concurrency.putGetNumsUsingThreads;

public class FirstThreadIncrementSharedIntegerSecondThreadPrintWithoutWaiting {
    public static void main(String[] args) {
        WorkingThread w = new WorkingThread();
        GenerateNum put = new GenerateNum(w);
        GetNum get = new GetNum(w);
        Thread thread = new Thread(put);
        Thread thread1 = new Thread(get);
        thread.start();
        thread1.start();
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
