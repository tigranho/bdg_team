package sqlquerywithpurejdbc.bgd.sqltask.dao.daoimpl;


import sqlquerywithpurejdbc.bgd.sqltask.dao.PrinterDao;
import sqlquerywithpurejdbc.bgd.sqltask.model.Printer;
import sqlquerywithpurejdbc.bgd.sqltask.util.DatabaseConnectionFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by User on 13.09.2020.
 */
public class PrinterDaoImpl implements PrinterDao {
    @Override
    public Optional<List<Printer>> fetchColoredPrinters() throws SQLException {
        String q = "select code, printer.model, price, maker from printer " +
                "join product on printer.model = product.model where color = 'y' order by price Desc;";
        List<Printer> list = new LinkedList<>();
        Printer printer;
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(q)) {
            while (resultSet.next()) {
                printer = new Printer();
                printer.setCode(resultSet.getInt(1));
                printer.setModel(resultSet.getString(2));
                printer.setPrice(resultSet.getFloat(3));
                printer.setMaker(resultSet.getString(4));
                list.add(printer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(list);
    }

    @Override
    public List<String> fetchMakersPcAndColoredPrinters() throws SQLException {
        String query = "select Distinct maker from pc join product on pc.model = product.model where type = 'pc' and maker IN (select Distinct maker from product join printer on product.model = printer.model where color='y' and type='printer');";
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
