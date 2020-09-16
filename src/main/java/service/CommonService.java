package service;
import java.sql.SQLException;





public interface CommonService {
    void get() throws SQLException;

    void getByCount()throws SQLException;

    void getByType() throws SQLException;

    void getPriceAvg() throws SQLException;

    void getMakerPc() throws SQLException;

    void getMakerPrinter() throws SQLException;

    void getMakerCodeSpeedModelPrice() throws SQLException;

    void getAvgPrice() throws SQLException;

    void getMakerPCPrinter() throws SQLException;

    void getMakerLaptopNotInPrinter();

}
