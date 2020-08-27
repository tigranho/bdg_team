package lesson3.chapter2.design_pattern.interfaces;

/**
 * @author Hrach
 */

public interface Run extends Walk {
    public abstract boolean canHuntWhileRunning();
    abstract double getMaxSpeed();
}
