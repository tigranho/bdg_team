package sqlquerywithpurejdbc.bgd.sqltask.dao.daoimpl;


import sqlquerywithpurejdbc.bgd.sqltask.dao.ProductDao;
import sqlquerywithpurejdbc.bgd.sqltask.util.DatabaseConnectionFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 13.09.2020.
 */
public class ProductDaoImpl implements ProductDao {

    @Override
    public List<String> fetchAllMakers(String type) throws SQLException {
        String query = "select Distinct maker  from product where type=?;";
        List<String> l = new LinkedList<>();
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement s = conn.prepareStatement(query)) {
            s.setString(1, type);
            try (ResultSet resultSet = s.executeQuery()) {
                while (resultSet.next()) {
                    l.add(resultSet.getString("maker"));
                }
            }
        }
        return l;
    }

    @Override
    public Map<String, Integer> fetchModelCountByMakers() throws SQLException {
        String query = "select maker, count(model) from product group by maker;";
        Map<String, Integer> map = new HashMap<>();
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                map.put(resultSet.getString(1), resultSet.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Integer> fetchMakerByTypeCount() throws SQLException {
        String q = "select maker, count(distinct type) from product group by maker;";
        Map<String, Integer> map = new HashMap<>();
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(q)) {
            while (resultSet.next()) {
                map.put(resultSet.getString(1), resultSet.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public List<String> fetchMakersOfLaptopWichDontMakePrinters() throws SQLException {
        String query = "select distinct maker from laptop join product on laptop.model = product.model where speed > 500 and maker not in(select distinct maker from printer join product on printer.model = product.model);";
        List<String> l = new LinkedList<>();
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement s = conn.prepareStatement(query)) {
            try (ResultSet resultSet = s.executeQuery()) {
                while (resultSet.next()) {
                    l.add(resultSet.getString("maker"));
                }
            }
        }
        return l;
    }
}

