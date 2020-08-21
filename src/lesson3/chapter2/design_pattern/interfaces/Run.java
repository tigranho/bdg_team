package lesson3.chapter2.design_pattern.interfaces;

public interface Run extends Walk {
    public abstract boolean canHuntWhileRunning();
    abstract double getMaxSpeed();
}
