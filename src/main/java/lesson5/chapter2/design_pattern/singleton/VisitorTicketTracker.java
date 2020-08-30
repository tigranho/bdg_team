package lesson5.chapter2.design_pattern.singleton;

public class VisitorTicketTracker {
    private static volatile VisitorTicketTracker instance;

    private VisitorTicketTracker() {}

    public static VisitorTicketTracker getInstance() {
        if (instance == null) {
            synchronized (VisitorTicketTracker.class) {
                if (instance == null) {
                    return new VisitorTicketTracker();
                }
            }
        }
        return instance;
    }
}
