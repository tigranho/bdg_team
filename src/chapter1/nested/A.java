package chapter1.nested;

public class A extends D {
    private int x = 10;

    public Integer test()  {
        C c = new C() {
            @Override
            void test1() {

            }
        };
      return 1;
    }

    public int foo() {
        try {
            throw new Exception();
        } catch (Exception e ) {
            return 0;
        }finally {
            return 1;
        }
    }

    class B implements Comparable  {
        private int x = 20;

        public void foo() {

        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }

        class C  {
            private int x = 30;

            public void allTheX() {
                System.out.println(x); // 30
                System.out.println(this.x); // 30
                System.out.println(B.this.x); // 20
                System.out.println(A.this.x); // 10
            }
            public void foo() {
                System.out.println("Class C");
            }
        }
    }

    public static void main(String[] args) {

//        List<B> bList = new ArrayList<>();
////        TreeMap< String, B> t = new TreeMap();
//        Collections.sort(bList);
//
    A a = new A();
//        A.B b = a.new B();
//        A.B.C c = b.new C();
//        c.allTheX();
//
//        System.out.println("");
//        a.foo();
//        b.foo();
//        c.foo();


        System.out.println(a.foo());

    }
}
