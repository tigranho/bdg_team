package airportamangmentsystems.io;

import model.Company;
import model.Passenger;
import service.CompanyService;
import service.PassengerService;
import service.implementation.CompanyServiceImplement;
import service.implementation.PassengerServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class CompanyPassenger {
    CompanyPassengerList companyPassengerList;
    CompanyService companyService;
    PassengerService passengerService;

    CompanyPassenger(){
        this.companyPassengerList = new CompanyPassengerList();
        this.companyService = new CompanyServiceImplement();
        this.passengerService = new PassengerServiceImpl();

        }

        public void addCompanies() throws SQLException {
           List<Company> list = companyPassengerList.getCompaniesList();
           for (Company company:list){
               companyService.save(company);
           }
        }

        public void addPassengers() throws SQLException {
        List<Passenger> list = companyPassengerList.getPassengersList();
        for (Passenger passenger:list){
            passengerService.save(passenger);
        }
        }

    public static void main(String[] args) throws SQLException {
        CompanyPassengerList companyPassengerList = new CompanyPassengerList();
        System.out.println(companyPassengerList.getCompaniesList());
        System.out.println(companyPassengerList.getCompaniesList());
        companyPassengerList.getPassengersList();
        CompanyPassenger companyPassenger = new CompanyPassenger();
        //companyPassenger.addCompanies();
        companyPassenger.addPassengers();
    }
    }

