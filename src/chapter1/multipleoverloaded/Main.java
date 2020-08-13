package chapter1.multipleoverloaded;

public class Main  {

    public static void main(String[] args) {
        // 1.
        a(7);
        System.out.println("  ");
        // 2.
        Child child = new Child();
        child.b( new Integer(7));



    }

    private static void a(int i) {
        System.out.print("int");
    }

    private static void a(long i) {
        System.out.print("long");
    }

    private static void a(Integer i) {
        System.out.print("Integer");
    }

    private static void a(Number i) {
        System.out.print("Number");
    }

    private static void a(int... i) {
        System.out.print("Varargs");
    }
}
