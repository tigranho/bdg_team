package lesson7.cunsumer_supplier.wait_notify;

public class Producer implements Runnable {
    private final Warehouse warehouse;
    private static int PROD_GENERATOR = 0;

    public Producer(Warehouse warehouse) {
        if (warehouse == null) throw new NullPointerException("warehouse not defined");
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (warehouse) {
                    while (warehouse.getSize() >= warehouse.getLimit()) {
                        warehouse.wait();
                    }
                    warehouse.addItem("item N%" + (++PROD_GENERATOR));
                    System.out.println("Produced item N%" + PROD_GENERATOR);
                    if (PROD_GENERATOR == 50000) System.exit(0);
                    warehouse.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
