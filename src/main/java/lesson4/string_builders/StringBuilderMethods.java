package lesson4.string_builders;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class StringBuilderMethods {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("animals");
        String sub = sb.substring(sb.indexOf("a"), sb.indexOf("al"));
        int len = sb.length();
        char ch = sb.charAt(6);
        out.println(sub + " " + len + " " + ch);

        //append
        sb = new StringBuilder().append(1).append('c');
        sb.append("-").append(true);
        out.println(sb);

        //insert
        sb = new StringBuilder("animals");
        sb.insert(7, "-");
        sb.insert(0, "-");
        sb.insert(4, "-");
        out.println(sb);

        //delete and deleteCharAt
        sb = new StringBuilder("abcdef");
        sb.delete(1, 3);
//        sb.deleteCharAt(5);
        out.println(sb);

        //reverse
        sb = new StringBuilder("ABC");
        sb.reverse();
        out.println(sb);

        //toString
        String result = sb.toString();

    }
}
