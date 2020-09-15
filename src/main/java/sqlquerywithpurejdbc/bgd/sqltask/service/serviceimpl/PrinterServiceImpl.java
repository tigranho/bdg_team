package sqlquerywithpurejdbc.bgd.sqltask.service.serviceimpl;

import am.bgd.sqltask.dao.PrinterDao;
import am.bgd.sqltask.dao.daoimpl.PrinterDaoImpl;
import am.bgd.sqltask.model.Printer;
import am.bgd.sqltask.service.PrinterService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by User on 13.09.2020.
 */
public class PrinterServiceImpl implements PrinterService {

    private PrinterDao printerDao;
    public PrinterServiceImpl(){
        this.printerDao = new PrinterDaoImpl();
    }
    @Override
    public Optional<List<Printer>> getColoredPrinters() throws SQLException {
        return printerDao.fetchColoredPrinters();
    }

    @Override
    public List<String> getMakersPcAndColoredPrinters() throws SQLException {
        return printerDao.fetchMakersPcAndColoredPrinters();
    }
}
