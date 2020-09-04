package main.java.chapter4;

/**
 * @author Tigran
 */

public class GorillaFamily {
    String walk = "walk";

    void everyonePlay(boolean baby) {
        String approach = "amble";
        //approach = "run";

        play(() -> walk);
        play(() -> baby ? "hitch a ride" : "run");
        play(() -> approach);
    }

    void play(main.java.chapter4.Gorilla g) {
        System.out.println(g.move());
    }

}
