package lesson7.cunsumer_supplier.locks;

import java.util.concurrent.atomic.AtomicLong;

public class Producer implements Runnable {
    private final Warehouse warehouse;
    private static final AtomicLong PROD_GENERATOR = new AtomicLong();

    public Producer(Warehouse warehouse) {
        if (warehouse == null) throw new NullPointerException("warehouse not defined");
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            try {
                warehouse.lock.lock();
                if (warehouse.getSize() <= warehouse.getLimit()) {
                    warehouse.addItem("item N%" + PROD_GENERATOR.incrementAndGet());
                    System.out.println("Produced item N%" + PROD_GENERATOR.get());
                    if (PROD_GENERATOR.get() == 50000) System.exit(0);
                }
            } finally {
                warehouse.lock.unlock();
            }
        }
    }
}
