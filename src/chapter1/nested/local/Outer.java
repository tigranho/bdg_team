package chapter1.nested.local;

// They do not have an access specifier.
        // They cannot be declared static and cannot declare s tatic fields or methods.
        // They have access to all fields and methods of the enclosing class.
        // They do not have access to local variables of a method unless those variables are final
                                        ///or effectively final. More on this shortly.

public class Outer {

    private int length = 5;

    public void calculate() {
        int width = 20;
        class Inner {
            public void multiply() {
                System.out.println(length * width);
            }
        }
        Inner inner = new Inner();
        inner.multiply();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.calculate();
    }
}