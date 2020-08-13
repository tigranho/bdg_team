package chapter1.multipleoverloaded;

public class Parent {

    public void b(int i) {
        System.out.print("int");
    }

    public void b(long i) {
        System.out.print("long");
    }

    public void b(Integer i) {
        System.out.print("Integer");
    }

    public void b(Number i) {
        System.out.print("Number");
    }

    public void b(int... i) {
        System.out.print("Varargs");
    }

    public Number  foo() {
        return 0;
    }
}
