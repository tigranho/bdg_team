package lesson4.strings;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class StringMethods {
    public static void main(String[] args) {
        //length
        String string = "animals";
        out.println(string.length());

        //charAt
        out.println(string.charAt(0));
        out.println(string.charAt(6));
//        out.println(string.charAt(7));

        //indexOf
        out.println(string.indexOf('a'));
        out.println(string.indexOf("al")); // 4
        out.println(string.indexOf('a', 4));
        out.println(string.indexOf("al", 5));

        //substring
        out.println(string.substring(3));
        out.println(string.substring(string.indexOf('m')));
        out.println(string.substring(3, 4));
        out.println(string.substring(3, 7));
        out.println(string.substring(3, 3));
//        out.println(string.substring(3, 2));
//        out.println(string.substring(3, 8));

        //toLowerCase and toUpperCase
        out.println(string.toUpperCase());
        out.println("Abc123".toLowerCase());
        out.println(string.substring(string.indexOf('a'), string.lastIndexOf('a')).toUpperCase());

        //equals and equalsIgnoreCase
        out.println("abc".equals("ABC"));
        out.println("ABC".equals("ABC"));
        out.println("abc".equalsIgnoreCase("ABC"));

        //startsWith and endsWith
        out.println("abc".startsWith("a"));
        out.println("abc".startsWith("A"));
        out.println("abc".endsWith("c"));
        out.println("abc".endsWith("a"));

        //contains
        out.println("abc".contains("b"));
        out.println("abc".contains("B"));

        //replace
        out.println("abcabc".replace('a', 'A'));
        out.println("abcabc".replace("a", "A"));
        out.println("abcabc".replaceFirst("a", "A"));

        //trim()
        out.println("abc".trim());
        System.out.println("\t a b c\n ".trim());

        //method chaining
        String start = "AniMaL ";
        String trimmed = start.trim();
        String lowercase = trimmed.toLowerCase();
        String result = lowercase.replace('a', 'A');
        out.println(result);
        result = "AniMaL ".trim().toLowerCase().replace('a', 'A');
        out.println(result);
        String a = "abc";
        String b = a.toUpperCase();
        b = b.replace("B", "2").replace('C', '3');
        out.println("a=" + a);
        out.println("b=" + b);
    }
}
