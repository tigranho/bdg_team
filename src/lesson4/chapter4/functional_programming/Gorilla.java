package lesson4.chapter4.functional_programming;

public interface Gorilla {
    String move();
}

class GorillaFamily {
    private String walk;
    public void everyonePlay(boolean baby) {
        String approach = "amble";
//        approach = "run";
        play(() -> walk);
        play(() -> baby ? "hitch a ride" : "run");
        play(() -> approach);
        play(() -> Nested.go);
    }

    public void play(Gorilla g) {
        System.out.println(g.move());
    }

    static class Nested{
        private static String go = "go";
    }

    public static void main(String[] args) {
        new GorillaFamily().everyonePlay(true);
    }

}