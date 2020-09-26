package jdbclesson;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new InputToSql().insertToA();
        new InputToSql().insertToP();
        new InputToSql().insertToC();
    }
}