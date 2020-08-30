package lesson5.chapter2.design_pattern.singleton;

 class VisitorTicketTracker {
    private static volatile VisitorTicketTracker instance;

    private VisitorTicketTracker() {
    }

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

interface A {
    void a();

    default void b() {
        System.out.println("default method 1");
    }

    default void c() {
        System.out.println("default method 2");
    }
}

public class E {
    public static void main(String[] args) {
        A a = new A() {
            @Override
            public void a() {
                new A() {
                    @Override
                    public void a() {
                        System.out.println(1);
                    }
                }.b();
            }
        };
        A a1 = () -> {
            ((A) () -> {
                System.out.println(1);
            }).b();
        };
        System.out.println(a.getClass()); // E$1     an anonymous class
        System.out.println(a1.getClass()); // E$$Lambda$15/0x0000000840064c40     an Proxy-lambda class
        a.b(); a1.b(); // default method 1
        a.c(); a1.c(); // default method 2
        a.a(); a1.a(); // default method 1;
    }
}