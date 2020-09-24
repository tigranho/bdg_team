package jdbclesson;

import jdbclesson.implementation.AddressI;
import jdbclesson.implementation.CompanyI;
import jdbclesson.implementation.PassengerI;

import java.io.*;
import java.sql.*;

public class InputToSql {

    public void readFile() throws SQLException, ClassNotFoundException, IOException {

        int id = 0;
        String name;
        String phone;
        String c_name;
        String found_date;
        String country;
        String city;

        File file = new File("passengers.txt");
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String read;
            while ((read = bufferedReader.readLine()) != null){
                String[] data = read.split(",");
                name = data[0];
                phone = data[1];
                country = data[2];
                city = data[3];
//                Address address = new AddressI().save(new Address(id, country, city));
                int a_id = new Address().getId();
//                new PassengerI().save(new Passenger(id, name, phone, a_id));
                insertToP(id, name, phone, a_id);
            }
        }

        File file1 = new File("companies.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file1))){
            String read;
            while ((read = bufferedReader.readLine()) != null){
                String[] data = read.split(",");
                c_name = data[0];
                found_date = data[1];
//                new CompanyI().save(new Company(c_name, found_date));
            }
        }
    }

//    private void insertToA(int id, String country, String city) throws SQLException, ClassNotFoundException {
//        try (Connection connection = Connect.getConnection()){
//            PreparedStatement preparedStatement;
//            preparedStatement = connection.prepareStatement(
//                    "insert into address(id, country, city) values (?, ?, ?)");
//            preparedStatement.setString(1, String.valueOf(id));
//            preparedStatement.setString(2, country);
//            preparedStatement.setString(3, city);
//            preparedStatement.executeUpdate();
//        }
//    }
//
    private void insertToP(int id, String name, String phone, int address) throws SQLException, ClassNotFoundException {
        try (Connection connection = Connect.getConnection()){
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(
                    "insert into passengers(id, name, phone, address_id) values (?, ?, ?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, phone);
            preparedStatement.setInt(4, address);
            preparedStatement.executeUpdate();
        }
    }
//
//    private void insertToC(String c_name, String found_date) throws SQLException, ClassNotFoundException {
//        int id = 0;
//        try (Connection connection = Connect.getConnection()){
//            PreparedStatement preparedStatement;
//            preparedStatement = connection.prepareStatement("insert into companies(id, name, found_date) values (?, ?, ?)");
//            preparedStatement.setString(1, String.valueOf(id));
//            preparedStatement.setString(1, c_name);
//            preparedStatement.setString(2, found_date);
//            preparedStatement.executeUpdate();
//        }
//    }
}