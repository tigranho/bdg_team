package lesson2.chapter3.generics.with_legacy_code;

import java.util.ArrayList;
import java.util.List;

public class LegacyDragons {
    public static void main(String[] args) {
        List unicorns = new ArrayList();
        unicorns.add(new Unicorn());
        printDragons(unicorns);
    }
    private static void printDragons(List<Dragon> dragons) {
        for(Dragon dragon: dragons) {
            System.out.println(dragon);
        }
    }
}

class Unicorn{}
class Dragon{}
