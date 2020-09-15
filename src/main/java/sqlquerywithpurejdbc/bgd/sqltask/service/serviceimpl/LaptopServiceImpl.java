package sqlquerywithpurejdbc.bgd.sqltask.service.serviceimpl;

import am.bgd.sqltask.dao.LapTopDao;
import am.bgd.sqltask.dao.daoimpl.LapTopDaoImpl;
import am.bgd.sqltask.service.LaptopService;

import java.sql.SQLException;

/**
 * Created by User on 13.09.2020.
 */
public class LaptopServiceImpl implements LaptopService{

    private LapTopDao lapTopDao;
    public LaptopServiceImpl(){
        this.lapTopDao = new LapTopDaoImpl();
    }
    @Override
    public Double getAvgPrice() throws SQLException {
        return this.lapTopDao.fetchAvgPrice();
    }
}
