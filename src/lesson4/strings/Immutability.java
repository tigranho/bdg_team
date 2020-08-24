package lesson4.strings;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class Immutability {
    public static void main(String[] args) {
        String s1 = "1";
        String s2 = s1.concat("2");
        s2.concat("3");
        out.println(s2);
    }
}

class Mutable {
    private String s;

    public void setS(String newS) {
        s = newS;
    }

    public String getS() {
        return s;
    }
}

final class Immutable {
    private String s = "name";

    public String getS() {
        return s;
    }
}