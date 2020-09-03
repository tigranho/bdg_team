package lesson7.cunsumer_supplier.wait_notify;

public class Consumer implements Runnable {
    private final Warehouse warehouse;

    public Consumer(Warehouse warehouse) {
        if (warehouse == null) throw new NullPointerException("warehouse not defined");
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (warehouse) {
                    while (warehouse.getSize() == 0) {
                        warehouse.wait();
                    }
                    System.out.println("Consumed " + warehouse.getItem());
                    warehouse.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
