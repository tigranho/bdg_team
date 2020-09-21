package tasks;

import tasks.airportManagementSystem.daoImpl.CompanyImpl;
import tasks.airportManagementSystem.model.Company;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import static tasks.airportManagementSystem.daoImpl.CompanyImpl.getConnection;

/**
 * @author Tatevik Mirzoyan
 * Created on 18-Sep-20
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        Set<Company> companies = new CompanyImpl().get(3, 3, "name");
        /*for (Company c : companies) {
            System.out.println(c);
        }*/

    }
}
