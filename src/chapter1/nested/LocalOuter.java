package chapter1.nested;

public class LocalOuter {
    private int length = 5;

    private Outer.Inner inner  = new Outer().new Inner();

    private Enclosing.Nested nested = new Enclosing.Nested();

    public void calculate() {
        nested.get();
        inner.go();
        final int width = 20;

        class Inner {
            public void multiply() {
                System.out.println(length * width);
            }
        }

        Inner inner = new Inner();
        inner.multiply();
    }

    public static void main(String[] args) {
        LocalOuter localOuter = new LocalOuter();
        localOuter.calculate();
    }
}
