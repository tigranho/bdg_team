package lesson7.cunsumer_supplier.locks;

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
                warehouse.lock.lock();
                while (warehouse.getSize() == 0) {
                    warehouse.isEmpty.await();
                }
                System.out.println("Consumed " + warehouse.getItem());
                warehouse.isFull.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                warehouse.lock.unlock();
            }
        }
    }
}
