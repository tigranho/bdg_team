package repository;

import commondbproperties.DBConnection;
import model.PC;
import model.Printer;


import java.sql.*;
import java.util.*;

public class FirmRepoImpl implements FirmRepo {


    @Override
    public List<String> fetch(String type) throws SQLException {
        List<String> list = new ArrayList<>();
        String query = "select distinct maker from product where type = ?;";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, type);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    list.add(resultSet.getString(1));

                }
            }

        }
        return list;
    }

    @Override
    public Map<String, Integer> fetchByCount() throws SQLException {
        String query = "select maker, count(model) from product group by maker;";
        Map<String, Integer> map = new TreeMap<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                map.put(resultSet.getString(1), resultSet.getInt(2));
            }
        }

        return map;
    }


    @Override
    public Map<String, String> fetchByType() throws SQLException {
        String query = "select maker, count(distinct type) from product group by maker ;";
        Map<String, String> map = new TreeMap<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                map.put(resultSet.getString(1), resultSet.getString(2));
            }
        }
        return map;
    }

    @Override
    public Optional<Integer> getAvg() throws SQLException {
        String query = "select  avg(price) from laptop;";
        Integer avg = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                avg = resultSet.getInt(1);
            }
        }
        return avg == null ? Optional.empty() : Optional.of(avg);
    }

    @Override
    public Map<String, List<PC>> fetchPCAndMaker() {
        String query = "select code,pc.model,speed,price,maker from pc join product on pc.model = product.model;";
        Map<String, List<PC>> map = new HashMap<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            PC pc;
            List<PC> list;
            while (resultSet.next()) {
                pc = new PC();
                list = new ArrayList<>();
                pc.setCode(resultSet.getInt("code"));
                pc.setModel(resultSet.getString("model"));
                pc.setSpeed(resultSet.getInt("speed"));
                pc.setPrice(resultSet.getInt("price"));
                pc.setMaker(resultSet.getString("maker"));
                list.add(pc);
                map.put(resultSet.getString(1), list);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, List<Printer>> fetchPrinterAndMaker() {
        String query = "select code,printer.model,price,maker from printer" +
                " join product on printer.model = product.model where color = 'y' order by code desc;";
        Map<String, List<Printer>> map = new HashMap<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            List<Printer> list;
            Printer printer;
            while (resultSet.next()) {
                printer = new Printer();
                list = new ArrayList<>();
                printer.setPrice(resultSet.getInt("code"));
                printer.setPrice(resultSet.getInt("price"));
                printer.setModel(resultSet.getString("model"));
                printer.setMaker(resultSet.getString("maker"));
                list.add(printer);
                map.put(resultSet.getString(1), list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, List<PC>> fetchCodeSpeedModelPriceByMaker() {
        String query = "select code, pc.model,speed,price,maker from pc right join product on pc.model=product.model;";
        Map<String, List<PC>> listMap = new HashMap<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            List<PC> list;
            PC pc;
            while (resultSet.next()) {
                pc = new PC();
                list = new ArrayList<>();
                pc.setCode(resultSet.getInt("code"));
                pc.setModel(resultSet.getString("model"));
                pc.setSpeed(resultSet.getInt("speed"));
                pc.setPrice(resultSet.getInt("price"));
                pc.setMaker(resultSet.getString("maker"));
                list.add(pc);
                listMap.put(resultSet.getString(1), list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listMap;
    }

    @Override
    public Map<String, Double> fetchGetAvgPrice() {
        String query = "select maker,avg(COALESCE(price, 0)) from  " +
                "product left join pc on product.model= pc.model group by maker; ";
        Map<String, Double> map = new HashMap<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                map.put(resultSet.getString(1), resultSet.getDouble(2));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Optional<List<String>> fetchPCAndPrinterByMaker() {
        String query = "select maker from pc join product on pc.model = product.model" +
                " where maker in (select maker from printer join product" +
                " on printer.model = product.model where color = 'y');";
        List<String> list = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list == null ? Optional.empty() : Optional.of(list);
    }

    @Override
    public Optional<Set<String>> fetchLaptopAndNotInPrinterByMaker() {
        String query = "select maker from product join laptop on " +
                "product.model = laptop.model where " +
                "laptop.speed > 500 and maker  not in (select maker from " +
                "product join printer " +
                "on product.model = printer.model);";
        //  List<String>list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                //list.add(resultSet.getString(1));
                set.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return list==null?Optional.empty():Optional.of(list);
        return set == null ? Optional.empty() : Optional.of(set);
    }
}
