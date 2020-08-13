package chapter1.enums;

public enum OnlyOne {
    ONCE(true);
    private OnlyOne(boolean b) {
               System. out .println("constructing");
    }
}
