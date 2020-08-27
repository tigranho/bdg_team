package lesson4.chapter4.functional_programming;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Supplier;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class SupplierImpl {
    public static void main(String[] args) {
        Supplier<LocalDate> s1 = LocalDate::now;
        Supplier<LocalDate> s2 = () -> LocalDate.now();
        LocalDate d1 = s1.get();
        LocalDate d2 = s2.get();
        out.println(d1);
        out.println(d2);

        Supplier<StringBuilder> s3 = StringBuilder::new;
        Supplier<StringBuilder> s4 = () -> new StringBuilder();
        out.println(s3.get());
        out.println(s4.get());

        Supplier<ArrayList<String>> s5 = ArrayList<String>::new;
        ArrayList<String> a1 = s5.get();
        out.println(a1);
        out.println(s5);
    }
}
