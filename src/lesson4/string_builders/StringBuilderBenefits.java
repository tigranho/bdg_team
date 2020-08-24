package lesson4.string_builders;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class StringBuilderBenefits {
    public static void main(String[] args) {
        String alpha = "";
        for (char c = 'a'; c <= 'z'; c++) {
            alpha += c;// that is create 26 garbage objects
        }
        out.println(alpha);
        //string builder solution
        StringBuilder alpha2 = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++) {
            alpha2.append(c); // that's no
        }
        out.println(alpha2);

        StringBuilder sb = new StringBuilder("start");
        sb.append("+middle"); // sb = "start+middle"
        StringBuilder same = sb.append("+end");
        StringBuilder a = new StringBuilder("abc");
        StringBuilder b = a.append("de");
        b = b.append("f").append("g");
        out.println("a=" + a);
        out.println("b=" + b);

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder("animal");
        StringBuilder sb3 = new StringBuilder(10);
    }
}
