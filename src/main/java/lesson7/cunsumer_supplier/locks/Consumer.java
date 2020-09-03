package lesson7.cunsumer_supplier.locks;

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
                warehouse.lock.lock();
                if (warehouse.getSize() != 0)
                    System.out.println("Consumed " + warehouse.getItem());
            } finally {
                warehouse.lock.unlock();
            }
        }
    }
}
