package lesson1.chapter1.enums;

public enum OnlyOne {
    ONCE(true);


     OnlyOne(boolean b) {
        System.out.println("constructing");
    }
}
