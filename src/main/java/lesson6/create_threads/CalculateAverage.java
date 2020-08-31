package lesson6.create_threads;

import java.util.stream.DoubleStream;

public class CalculateAverage implements Runnable {

    private double[] scores;

    public CalculateAverage(double[] scores) {
        this.scores = scores;
    }

    @Override
    public void run() {
        System.out.println(DoubleStream.of(scores)
                .average().orElse(0.0));
    }
}
