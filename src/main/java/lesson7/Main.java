package lesson7;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
         Arrays.asList(1).parallelStream();
        IntStream.of(1).parallel();
    }
}
