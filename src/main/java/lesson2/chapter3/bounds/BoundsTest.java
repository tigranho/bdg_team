package lesson2.chapter3.bounds;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.lang.System.out;

/**
 * @author Hrach
 */

public class BoundsTest {
    public static void main(String[] args) {
        List<?> list1 = new ArrayList<A>();
        List<? extends A> list2 = new ArrayList<A>();
        List<? super A> list3 = new ArrayList<A>();
//        List<? extends B> list4 = new ArrayList<A>();
        List<? super B> list5 = new ArrayList<A>(new ArrayList<C>());
//        List<?> list6 = new ArrayList<? extends A>();
        list3.add(new D());
        list3.add(new B());
        list3.add(new A());
//        B b = (B) list3.get(0);//throws ClassCastException

        BoundsTest boundsTest = new BoundsTest();
        out.println(boundsTest.<Long>method1(Arrays.asList(25L)));

        out.println(boundsTest.<A>method3(Arrays.asList(new B())));

        out.println(boundsTest.method4(Arrays.asList(new C())));
    }

    <T> T method1(List<? extends T> list) {
        return list.get(0);
    }

//    public <T> <? extends T> method2(List<? extends T> list) {
//        return list.get(0);
//    }

     <B extends A> B method3(List<B> list) {
        return list.get(0); //Compile successfully
    }

    Object method4(List<? super B> list) {
        return list.get(0);
    }

//    <X> void method5(List<X super B> list) {}
}


class A{}
class B extends A {}
class C extends B {}
class D extends A {}