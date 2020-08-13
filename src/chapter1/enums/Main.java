package chapter1.enums;

public class Main {

    public static void main(String[] args) {

        Season s = Season.SUMMER;
        System.out.println(Season.SUMMER); // SUMMER
        System.out.println(s == Season.SUMMER); // true

        for (Season season : Season.values()) {
            System.out.println(season.name() + " " + season.ordinal());
        }

        Season s1 = Season.valueOf("SUMMER"); // SUMMER
       // Season s2 = Season.valueOf("summer"); // exception

        Season summer = Season.SUMMER;
        switch (summer) {
//            case Season.WINTER:          // Not compile
//                System.out.println("Get out the sled!");
//                break;
            case SUMMER:
                System.out.println("Time for the pool!");
                break;
            default:
                System.out.println("Is it summer yet?");
        }
    }

    int season2 = Season2.test3(8);


    OnlyOne firstCall = OnlyOne.ONCE; // prints constructing
    OnlyOne secondCall = OnlyOne.ONCE; // doesn't print anything

}



