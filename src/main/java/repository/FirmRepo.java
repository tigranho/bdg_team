package repository;

import model.Laptop;
import model.PC;
import model.Printer;
import model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface FirmRepo {
    List<String> fetch(String maker) throws SQLException;

    Map<String, Integer> fetchByCount() throws SQLException;

    Map<String, String> fetchByType() throws SQLException;

    Optional<Integer> getAvg() throws SQLException;

    Map<String, List<PC>> fetchPCAndMaker() throws SQLException;

    Map<String, List<Printer>> fetchPrinterAndMaker() throws SQLException;

    Map<String, List<PC>> fetchCodeSpeedModelPriceByMaker();

    Map<String, Double> fetchGetAvgPrice();

    Optional<List<String>> fetchPCAndPrinterByMaker();

   Optional<Set<String>> fetchLaptopAndNotInPrinterByMaker();

}
