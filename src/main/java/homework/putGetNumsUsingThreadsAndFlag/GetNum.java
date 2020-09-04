package homework.putGetNumsUsingThreadsAndFlag;

public class GetNum implements Runnable{
    ControlThread controlThread;
    GetNum(ControlThread controlThread){
        this.controlThread = controlThread;
    }
    @Override
    public void run() {
        synchronized (this){
            int i;
            while (true){
                i = controlThread.getNum();
                System.out.println("get " + i);
                if(i == 1_000_000){
                    break;
                }
            }
        }
    }
}
