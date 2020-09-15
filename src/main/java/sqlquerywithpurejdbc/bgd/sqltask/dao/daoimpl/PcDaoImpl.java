package sqlquerywithpurejdbc.bgd.sqltask.dao.daoimpl;


import sqlquerywithpurejdbc.bgd.sqltask.dao.PcDao;
import sqlquerywithpurejdbc.bgd.sqltask.model.Pc;
import sqlquerywithpurejdbc.bgd.sqltask.util.DatabaseConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by User on 13.09.2020.
 */
public class PcDaoImpl implements PcDao {
    @Override
    public Optional<List<Pc>> fetchAll() throws SQLException {
        String q = "select code, pc.model,  speed, price, maker from pc join product on pc.model = product.model;";
        List<Pc> list = new LinkedList<>();
        Pc pc;
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(q)) {
            while (resultSet.next()) {
                pc = new Pc();
                pc.setCode(resultSet.getInt(1));
                pc.setModel(resultSet.getString(2));
                pc.setSpeed(resultSet.getDouble(3));
                pc.setPrice(resultSet.getFloat(4));
                pc.setMaker(resultSet.getString(5));
                list.add(pc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(list);
    }

    @Override
    public Map<String, List<Pc>> getMakersPc() throws SQLException {
//        String qu = "select code, pc.model, speed,price, maker  from product left join pc on product.model = pc.model where type='pc' order by maker Asc;";
        String query = "select code, pc.model, speed,price, maker  from product join pc on product.model = pc.model where type='pc' order by maker Asc;";
        Map<String, List<Pc>> map = new HashMap<>();
        List<Pc> list;
        Pc pc;
        String maker;
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                list = new LinkedList<>();
                pc = new Pc();
                maker = resultSet.getString(5);
                pc.setCode(resultSet.getInt(1));
                pc.setModel(resultSet.getString(2));
                pc.setSpeed(resultSet.getDouble(3));
                pc.setPrice(resultSet.getFloat(4));
                pc.setMaker(maker);
                if (map.containsKey(maker)) {
                    list = map.get(maker);
                    list.add(pc);
                    map.put(maker, list);
                } else {
                    list.add(pc);
                    map.put(maker, list);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Double> fetchAvgPriceEachMaker() throws SQLException {
        String q = "select maker, avg(price)  from product join pc on product.model = pc.model group by maker;";
        Map<String, Double> map = new HashMap<>();
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(q)) {
            while (resultSet.next()) {
                map.put(resultSet.getString(1), resultSet.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
