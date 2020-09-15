package sqlquerywithpurejdbc.bgd.sqltask.service;


import sqlquerywithpurejdbc.bgd.sqltask.model.Printer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by User on 13.09.2020.
 */
public interface PrinterService {
    Optional<List<Printer>> getColoredPrinters() throws SQLException;

    List<String> getMakersPcAndColoredPrinters() throws SQLException;
}
