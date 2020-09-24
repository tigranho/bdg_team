package jdbclesson;

import java.io.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        new InputToSql().readFile();
    }
}