package chapter7.create_thread;

public class ReadInventoryThread extends Thread {

    public void run() {
        System.out.println("Printing zoo inventory");
    }

    public static void main(String[] args) {
        (new ReadInventoryThread()).start();
    }

}
