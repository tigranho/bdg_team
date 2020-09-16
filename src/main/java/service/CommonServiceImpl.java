package service;

import repository.FirmRepo;
import repository.FirmRepoImpl;
import java.sql.SQLException;


public class CommonServiceImpl implements CommonService{
    FirmRepo firmRepo;
   public CommonServiceImpl(){
        this.firmRepo = new FirmRepoImpl();
    }
    @Override
    public void get() throws SQLException {
        firmRepo.fetch("PC");
    }

    @Override
    public void getByCount() throws SQLException {
        firmRepo.fetchByCount();
    }

    @Override
    public void getByType() throws SQLException {
      firmRepo.fetchByType();
    }

    @Override
    public void getPriceAvg() {
        firmRepo.fetchGetAvgPrice();
    }

    @Override
    public void getMakerPc() throws SQLException {
     firmRepo.fetchPCAndMaker();
    }

    @Override
    public void getMakerPrinter() throws SQLException {
        firmRepo.fetchPrinterAndMaker();
    }

    @Override
    public void getMakerCodeSpeedModelPrice() {
      firmRepo.fetchCodeSpeedModelPriceByMaker();
    }

    @Override
    public void getAvgPrice() throws SQLException {
      firmRepo.getAvg();
    }

    @Override
    public void getMakerPCPrinter() {
      firmRepo.fetchPCAndPrinterByMaker();
    }

    @Override
    public void getMakerLaptopNotInPrinter() {
        firmRepo.fetchLaptopAndNotInPrinterByMaker();
    }


}
