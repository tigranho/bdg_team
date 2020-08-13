package chapter1.nested;

public class Enclosing {

    public int outer = 5;

     public static class Nested {
        private int price = 89;


        public  static int get() {
            return 0;
        }
    }

    public static void main(String[] args) {
        Enclosing enclosing = new Enclosing();
        Nested nested = new Nested();
    }
}

