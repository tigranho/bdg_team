package lesson2.chapter3.generics;

import java.util.ArrayList;
import java.util.List;

public class Doggies {
    private List<String> names;

    public Doggies() {
        names = new ArrayList<>();
    }

    public List<String> copyNames() {
        ArrayList<String> copyOfNames;
        copyOfNames = new ArrayList<>(names);
        return copyOfNames;
    }
}
