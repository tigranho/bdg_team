package homework.sumThreadsGeneratingRandomNumbers;

public class SumThreadsGeneratingRandomNumbers {
    private static int  sum = 0;
    synchronized void generateAndSumRandomNumber(){
        int r = (int) (Math.random()*10);
        //System.out.println(r);
        sum += r;
        //System.out.println(sum);
    }
    public static void main(String[] args) {
        SumThreadsGeneratingRandomNumbers s = new SumThreadsGeneratingRandomNumbers();
        Thread thread1 = new Thread(()->{s.generateAndSumRandomNumber();});
        Thread thread2 = new Thread(()->{s.generateAndSumRandomNumber();});
        Thread thread3 = new Thread(()->{s.generateAndSumRandomNumber();});
        Thread thread4 = new Thread(()->{s.generateAndSumRandomNumber();});
        Thread thread5 = new Thread(()->{s.generateAndSumRandomNumber();});
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        try{
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(sum);
    }
}
