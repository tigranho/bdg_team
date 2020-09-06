package lesson7.cunsumer_supplier.locks;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse {
    private final int limit;
    private static final int WAREHOUSE_SIZE = 35;
    private final Queue<String> warehouse = new ConcurrentLinkedQueue<>();
    public final Lock lock = new ReentrantLock();
    public final Condition isFull = lock.newCondition();
    public final Condition isEmpty = lock.newCondition();

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

    public String getItem() {
        return warehouse.poll();
    }

    public void addItem(String item) {
        warehouse.add(item);
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
            System.out.printf("executed in %d second", (System.currentTimeMillis() - start) / 1000); //11 second
        }));
    }
}
