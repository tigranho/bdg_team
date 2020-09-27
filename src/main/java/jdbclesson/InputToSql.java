package jdbclesson;

import jdbclesson.dao.AddressDAO;
import jdbclesson.implementation.AddressImpl;
import jdbclesson.implementation.CompanyImpl;
import jdbclesson.model.Address;
import jdbclesson.model.Company;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InputToSql {

    public void insertToA() throws IOException {

        int id = 0;
        String country;
        String city;
        File file = new File("src/main/resources/passengers.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                String[] data = read.split(",");
                country = data[2];
                city = data[3];

                new AddressImpl().save(new Address(id, country, city));
            }
        }
    }

    public void insertToP() throws IOException, SQLException, ClassNotFoundException {
        int id = 0;
        int aid = 0;
        String name;
        String phone;
        AddressDAO address = new AddressImpl();
        File file = new File("src/main/resources/passengers.txt");

        try (Connection connection = Connect.getConnection();
             BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                String[] data = read.split(",");
                name = data[0];
                phone = data[1];
                aid++;
                Address a_id = address.getById(aid);
                int id1 = a_id.getId();

                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into passengers(id, name, phone, address_id) VALUES (?, ?, ?, ?)");
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, phone);
                preparedStatement.setInt(4, id1);
            }

        }
    }

    public void insertToC() throws IOException {
        int id = 0;
        String c_name;
        String found_date;
        File file = new File("src/main/resources/companies.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                String[] data = read.split(",");
                c_name = data[0];
                found_date = data[1];

                new CompanyImpl().save(new Company(id, c_name, found_date));
            }
        }
    }
}