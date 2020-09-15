package sqlquerywithpurejdbc.bgd.sqltask.dao;


import sqlquerywithpurejdbc.bgd.sqltask.model.Printer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by User on 13.09.2020.
 */
public interface PrinterDao {
    Optional<List<Printer>> fetchColoredPrinters() throws SQLException;

    List<String> fetchMakersPcAndColoredPrinters() throws SQLException;
}
