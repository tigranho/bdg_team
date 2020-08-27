package main.java.chapter1.overriding;

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


    public Integer foo() throws IOException {
        return  1;
    }


    public void getKangarooDescription() {
        System.out.println("Static Kangaroo  " + isBiped());
        System.out.println("Kangaroo  " + isBiped());
    }

    public static void main(String[] args) {
        Kangaroo kangaroo = new Kangaroo();
        kangaroo.getMarsupialDescription();
        kangaroo.getKangarooDescription();
    }
}
