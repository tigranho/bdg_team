package lesson4.strings;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class Concatenation {
    public static void main(String[] args) {
        out.println(1 + 2);
        out.println("a" + "b");
        out.println("a" + "b" + 3);
        out.println(1 + 2 + "c");
        int three = 3;
        String four = "4";
        out.println(1 + 2 + three + four);
        String s = "1";
        s += "2";
        s += 3;
        out.println(s);
    }
}
