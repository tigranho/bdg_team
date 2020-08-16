package lesson1.chapter1.enums;

public enum Season2 {
    WINTER("Low") {
        public void printHours() { System. out .println("9am-3pm"); }
    },
    SPRING("Medium") {
        public void printHours() { System. out .println("9am-7pm"); }
    };

    private String expectedVisitors;

    private Season2(String expectedVisitors) {
        this.expectedVisitors = expectedVisitors;
    }

    public void printExpectedVisitors() {
        System.out.println(expectedVisitors);
    }

    public abstract void printHours();

    private void test() {

    }

    int test2(int i) {
        return i++;
    }

   public static int test3(int i) {
        return i++;
    }

}
