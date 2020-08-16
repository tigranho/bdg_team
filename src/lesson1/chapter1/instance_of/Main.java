package lesson1.chapter1.instance_of;

public class Main {

    public static void main(String[] args) {
        HeavyAnimal hippo = new Hippo();

        boolean b1 = hippo instanceof Hippo; // true
        boolean b2 = hippo instanceof HeavyAnimal; // true
        boolean b3 = hippo instanceof Elephant; // false
        boolean b4 = hippo instanceof Object; // true
        boolean b5 = hippo instanceof Mother; // false

        Hippo nullHippo = null;
        boolean b7 = nullHippo instanceof Object; // false

        Hippo anotherHippo = new Hippo();
       // boolean b6 = anotherHippo instanceof Elephant; // DOES NOT COMPILE
       // boolean b61 = anotherHippo instanceof Elephant; // DOES NOT COMPILE
        boolean b62 = anotherHippo instanceof Mother; //false


        boolean b9 = hippo instanceof Mother; // false;

        Hippo motherHippo = new MotherHippo();

        boolean b8 = motherHippo instanceof Mother; // true;


    }

}
