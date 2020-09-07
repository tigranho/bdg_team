package concurrency.putGetNumsUsingThreadsAndFlag;

public class putGetNumsByFollowingEachOtherUsingThreads {
    public static void main(String[] args) {
        ControlThread controlThread = new ControlThread();
        GenerateNum put = new GenerateNum(controlThread);
        GetNum getNum = new GetNum(controlThread);
        Thread thread = new Thread(put);
        Thread thread1 = new Thread(getNum);
        thread.start();
        thread1.start();
        try{
            thread.join();
            thread1.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
