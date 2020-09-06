package lesson7.cunsumer_supplier.blocking_queue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Warehouse {
    private final int limit;
    private static final int WAREHOUSE_SIZE = 35;
    private final BlockingQueue<String> warehouse = new ArrayBlockingQueue<>(30, true);

    public Warehouse(int limit) {
        if (limit < 0 || limit > WAREHOUSE_SIZE)
            throw new IllegalArgumentException("illegal warehouse limit: "
                    + limit + "\n limit must be grater than zero and less or equals warehouse max capacity:" + WAREHOUSE_SIZE);
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public int getSize() {
        return warehouse.size();
    }

    public String getItem() throws InterruptedException {
        return warehouse.take();
    }

    public void addItem(String item) throws InterruptedException {
        warehouse.put(item);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final Warehouse warehouse = new Warehouse(30);
        List<Runnable> workers = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) workers.add(new Producer(warehouse));
            else workers.add(new Consumer(warehouse));
        }
        ExecutorService service = Executors.newFixedThreadPool(6);
        for (Runnable worker : workers) service.submit(worker);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            service.shutdown();
            System.out.printf("executed in %d second", (System.currentTimeMillis() - start) / 1000); // 10 second
        }));
    }
}
