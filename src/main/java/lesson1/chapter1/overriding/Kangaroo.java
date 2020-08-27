package lesson1.chapter1.overriding;

import java.io.IOException;

/**
 * @author Tigran
 */
public class Kangaroo extends Marsupial {

    public int lenght = 8;

    public  static  boolean isBipedStatic() {
        return  true;
    }

    public  boolean isBiped() {
        return  true;
    }

    public void print(double d) {

    }

    public Integer foo() throws IOException {
        return  1;
    }


    public void getKangarooDescription() {
        System.out.println("Static Kangaroo  " + isBiped());
        System.out.println("Kangaroo  " + isBiped());
    }

    @Override
    public Long jumpingCount() {
        return (Long) super.jumpingCount();
    }

    public static void main(String[] args) {
        Kangaroo kangaroo = new Kangaroo();
        kangaroo.getMarsupialDescription();
        kangaroo.getKangarooDescription();
        kangaroo.print(1);
    }
}
