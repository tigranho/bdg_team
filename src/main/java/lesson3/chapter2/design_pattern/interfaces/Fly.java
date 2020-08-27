package lesson3.chapter2.design_pattern.interfaces;

/**
 * @author Hrach
 */

public interface Fly {
    int getWingSpan() throws Exception;

    int MAX_SPEED = 100;

    default void land() {
        System.out.println("Animal is landing");
    }

    public static double calculateSpeed(float distance, double time) {
        return distance / time;
    }
}
