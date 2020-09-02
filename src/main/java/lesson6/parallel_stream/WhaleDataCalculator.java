package lesson6.parallel_stream;

import java.util.ArrayList;
import java.util.List;

public class WhaleDataCalculator {
    public int processRecord(int input) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        return input + 1;
    }

    public void processAllData(List<Integer> data) {
        data.stream().map(this::processRecord).forEach(i -> {}); //Tasks completed in: 43.165 seconds
//        data.stream().map(this::processRecord).count(); //Tasks completed in: 0.005 seconds
    }

    public static void main(String[] args) {
        WhaleDataCalculator calculator = new WhaleDataCalculator();
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 4000; i++) data.add(i);
        long start = System.currentTimeMillis();
        calculator.processAllData(data);
        double time = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("\nTasks completed in: " + time + " seconds");
    }
}
