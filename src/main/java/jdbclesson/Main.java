package jdbclesson;

import java.io.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        new InputToSql().insertToA();
        new InputToSql().insertToP();
        new InputToSql().insertToC();
    }
}