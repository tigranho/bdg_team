package lesson2.chapter3.generics.classes;

import java.util.ArrayList;
import java.util.List;

/**
* @author Hrach
*/

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

    public void changeNames(List list) {
        names.addAll(list);
    }

    static void printNames(List list) {
        for (int i = 0; i < list.size(); i++) {
            String name = (String) list.get(i);
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        List names = new ArrayList();
        names.add(new StringBuffer("Webby"));
//        printNames(names);
        Doggies doggies = new Doggies();
        doggies.changeNames(names);
        printNames(doggies.names);
    }
}
