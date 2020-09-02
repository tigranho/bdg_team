package chapter1.overriding;

/**
 * @author Tigran
 */
public class Marsupial {

    public  int lenght = 4;


    public  static  boolean isBipedStatic() {
        return  false;
    }

    public boolean isBiped() {
        return  false;
    }

    public Number foo() throws Exception {
        return  1;
    }


    public void getMarsupialDescription() {
        System.out.println("Static Marsupial  " + isBiped());
        System.out.println("Marsupial  " + isBiped());
    }
}
