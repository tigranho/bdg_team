package tasks;

import tasks.airportManagementSystem.JPA.daoImpl.CompanyImpl;
import tasks.airportManagementSystem.JPA.daoImpl.PassengerImpl;
import tasks.airportManagementSystem.JPA.daoImpl.TripImpl;
import tasks.airportManagementSystem.JPA.model.Address;
import tasks.airportManagementSystem.JPA.model.Company;
import tasks.airportManagementSystem.JPA.model.Passenger;
import tasks.airportManagementSystem.JPA.model.Trip;

import java.sql.*;
import java.util.List;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 18-Sep-20
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        List<Trip> passengers = new TripImpl().getTripsTo("Moscow");
        for (Trip p : passengers) {
            System.out.println(p);
        }


    }
}
