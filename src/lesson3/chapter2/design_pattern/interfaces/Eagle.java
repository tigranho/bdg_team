package lesson3.chapter2.design_pattern.interfaces;

/**
 * @author Hrach
 */

public class Eagle implements Fly {
    @Override
    public int getWingSpan() {
        return 15;
    }
    public void land() {
        System.out.println("Eagle is diving fast");
    }
}
