package lesson6.create_threads;

public class ReadInventoryThread extends Thread {
    @Override
    public void run() {
        System.out.println("Printing zoo inventory");
    }

    public static void main(String[] args) {
        new ReadInventoryThread().start();
    }
}
