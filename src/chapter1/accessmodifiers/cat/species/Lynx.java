package chapter1.accessmodifiers.cat.species;


import chapter1.accessmodifiers.cat.BigCat;

public class Lynx extends BigCat {

    public static void main(String[] args) {
        BigCat cat = new BigCat();
        System.out.println(cat.name);
//        System. out .println(cat.hasFur);
//        System. out .println(cat.hasPaws);
//        System. out .println(cat.id);
    }
}
