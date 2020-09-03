package lesson7.cunsumer_supplier.wait_notify;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Warehouse {
    private final int limit;
    private static final int WAREHOUSE_SIZE = 35;
    private final Queue<String> warehouse;

    public Warehouse(int limit, Queue<String> warehouse) {
        if (warehouse == null) throw new NullPointerException("warehouse not defined");
        if (limit < 0 || limit > WAREHOUSE_SIZE)
            throw new IllegalArgumentException("illegal warehouse limit: "
                    + limit + "\n limit must be grater than zero and less or equals warehouse max capacity:" + WAREHOUSE_SIZE);

        this.warehouse = warehouse;
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public int getSize() {
        return warehouse.size();
    }

    public String getItem() {
        return warehouse.poll();
    }

    public void addItem(String item) {
        warehouse.add(item);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final Queue<String> mainWarehouse = new ConcurrentLinkedQueue<>();
        final Warehouse warehouse = new Warehouse(30, mainWarehouse);
        List<Runnable> workers = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            if (i < 3) workers.add(new Producer(warehouse));
            else workers.add(new Consumer(warehouse));
        }
        ExecutorService service = Executors.newFixedThreadPool(6);
        for(Runnable worker: workers) service.submit(worker);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            service.shutdown();
            System.out.printf("executed in %d second",  (System.currentTimeMillis() - start) / 1000); // 8 second
        }));
    }
}
