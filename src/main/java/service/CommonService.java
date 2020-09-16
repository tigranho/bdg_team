package service;

import model.PC;
import model.Printer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
