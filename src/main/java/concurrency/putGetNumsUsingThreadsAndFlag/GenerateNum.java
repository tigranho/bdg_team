package concurrency.putGetNumsUsingThreadsAndFlag;

public class GenerateNum implements Runnable{
    ControlThread controlThread;
    GenerateNum(ControlThread controlThread){
        this.controlThread = controlThread;
    }
    @Override
    public void run() {
        synchronized (this){
            int i = 0;
            while (true){
                if(i == 1_000_000){
                    break;
                }
                controlThread.putNum(i);
                System.out.println("put " + i);
                i++;
            }
        }
    }
}
