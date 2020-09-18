package jdbctask.companypassengerlist;


import jdbctask.model.Company;
import jdbctask.model.Passenger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 17.09.2020.
 */
public class CompanyPassengerList {
    private static List<String> getCompanyOrPassengerList(String fileName) {
        List<String> list = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            int readCount;
            while ((readCount = in.read()) != -1) {
                list.add((char) readCount + in.readLine());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Company> getCompaniesList() {
        List<Company> list = new LinkedList<>();
        List<String> companyList = getCompanyOrPassengerList("companies.txt");
        List<String[]> currentList = new LinkedList<>();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for (int i = 1; i < companyList.size(); i++) {
            currentList.add(companyList.get(i).split(","));
        }
        for (String[] el : currentList) {
            Company company = new Company();
            company.setName(el[0]);
//            company.setFoundDate(LocalDate.parse(el[1], formatter));
            company.setFoundDate(el[1]);
            list.add(company);
        }
        return list;
    }

    public List<Passenger> getPassengersList() {
        List<Passenger> list = new LinkedList<>();
        List<String> passengersList = getCompanyOrPassengerList("passengers.txt");
        List<String[]> currentList = new LinkedList<>();
        for (int i = 1; i < passengersList.size(); i++) {
            currentList.add(passengersList.get(i).split(","));
        }
        for (String[] el : currentList) {
            Passenger passenger = new Passenger();
            passenger.setName(el[0]);
            passenger.setPhone(el[1]);
            passenger.setCountry(el[2]);
            passenger.setCity(el[3]);
            list.add(passenger);
        }
        return list;
    }

    public static void main(String[] args) {
        CompanyPassengerList c = new CompanyPassengerList();
        System.out.println(c.getPassengersList());
//        System.out.println(c.getCompaniesList());
    }
}
