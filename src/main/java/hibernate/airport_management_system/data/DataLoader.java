package hibernate.airport_management_system.data;

import hibernate.airport_management_system.Service.AddressDaoImpl;
import hibernate.airport_management_system.Service.PassengerDaoImpl;
import hibernate.airport_management_system.dao.AddressDao;
import hibernate.airport_management_system.dao.PassengerDao;
import hibernate.airport_management_system.entity.Address;
import hibernate.airport_management_system.entity.Passenger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DataLoader {
    public void insertIntoPassenger() throws IOException {
        BufferedReader companiesReader = new BufferedReader(new FileReader("C:\\Users\\Samvel\\Desktop\\JAVA_Intermediate\\homework(JDBC)\\homework(JDBC)\\passengers.txt"));
        String str;

        while ((str = companiesReader.readLine()) != null) {
            String[] s = str.split(",");

            Address address = new Address(s[2], s[3]);
            Passenger passenger = new Passenger(s[0], s[1], address);
            PassengerDao passengerDao = new PassengerDaoImpl();

            passengerDao.save(passenger);

        }
    }

    public void insertIntoAddress() throws IOException {
        BufferedReader companiesReader = new BufferedReader(new FileReader("C:\\Users\\Samvel\\Desktop\\passengers.txt"));
        String str;
        List<String> strings = new LinkedList<>();

        while ((str = companiesReader.readLine()) != null) {
            strings.add(str);
        }
        for(String obj: strings) {
            String[] s = obj.split(",");

            Address address = new Address(s[2], s[3]);
            AddressDao addressDao = new AddressDaoImpl();

            addressDao.save(address);
            System.out.println(address.getId());
        }



    }
}
