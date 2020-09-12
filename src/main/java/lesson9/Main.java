package lesson9;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        boolean b = Arrays.stream(new String[]{""}).allMatch(el -> el.equals("x") || el.equals("o"));

    }
}
