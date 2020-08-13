package chapter1.multipleoverloaded;

public class Child extends  Parent {

//    @Override
//    public   void b(int i) {
//        System.out.print("int");
//    }

    @Override
    public  void b(long i) {
        System.out.print("long");
    }
//
//    @Override
//    public  void b(Integer i) {
//        System.out.print("Integer");
//    }

    @Override
    public  void b(Number i) {
        System.out.print("Number");
    }

    @Override
    public  void b(int... i) {
        System.out.print("Varargs");
    }

    @Override
    public Integer foo() {
        return 0;
    }
}
