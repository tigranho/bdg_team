package lesson5.chapter2.design_pattern.singleton;

public class StaffRegister {
    private static final StaffRegister INSTANCE;
    static {
        INSTANCE = new StaffRegister();
    }
    private StaffRegister() {}
    public static StaffRegister getInstance() {
        return INSTANCE;
    }
}
