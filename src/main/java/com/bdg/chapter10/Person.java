package com.bdg.chapter10;
import java.sql.*;

public class Person {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/Bdg";

        try (Connection conn = DriverManager.getConnection(url,"Karlen","Anuliks2013")){
            System.out.println("Connected");
    }
}
}
