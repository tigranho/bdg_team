package lesson7.cunsumer_supplier.blocking_queue;

public class Consumer implements Runnable {
    private final Warehouse warehouse;

    public Consumer(Warehouse warehouse) {
        if (warehouse == null) throw new IllegalArgumentException("warehouse not defined");
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumed " + warehouse.getItem());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
