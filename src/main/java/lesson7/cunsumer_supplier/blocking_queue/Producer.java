package lesson7.cunsumer_supplier.blocking_queue;

import java.util.concurrent.atomic.AtomicLong;

public class Producer implements Runnable {
    private final Warehouse warehouse;
    private static final AtomicLong PROD_GENERATOR = new AtomicLong();

    public Producer(Warehouse warehouse) {
        if (warehouse == null) throw new IllegalArgumentException("warehouse not defined");
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            try {
                warehouse.addItem("item N%" + PROD_GENERATOR.incrementAndGet());
                System.out.println("Produced item N%" + PROD_GENERATOR.get());
                if (PROD_GENERATOR.get() == 50000) System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
