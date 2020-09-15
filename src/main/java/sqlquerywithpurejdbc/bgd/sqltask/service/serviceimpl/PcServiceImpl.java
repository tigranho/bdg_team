package sqlquerywithpurejdbc.bgd.sqltask.service.serviceimpl;

import am.bgd.sqltask.dao.PcDao;
import am.bgd.sqltask.dao.daoimpl.PcDaoImpl;
import am.bgd.sqltask.model.Pc;
import am.bgd.sqltask.service.PcService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by User on 13.09.2020.
 */
public class PcServiceImpl implements PcService {

    private PcDao pcDao;

    public PcServiceImpl(){
        this.pcDao = new PcDaoImpl();
    }

//    @Override
//    public Pc add(Pc pc) throws SQLException {
//        return null;
//    }
//
//    @Override
//    public Optional<Pc> get(int id) throws SQLException {
//        return null;
//    }


    @Override
    public Map<String, Double> getAvgPriceEachMaker() throws SQLException {
        return pcDao.fetchAvgPriceEachMaker();
    }

    @Override
    public Optional<List<Pc>> getAll() throws SQLException {
        return this.pcDao.fetchAll();
    }

    @Override
    public Map<String, List<Pc>> getMakersPc() throws SQLException {
        return this.pcDao.getMakersPc();
    }
}
