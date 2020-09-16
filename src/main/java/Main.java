import repository.FirmRepoImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        FirmRepoImpl firmRepo = new FirmRepoImpl();
//        System.out.println(firmRepo.fetch("PC"));
//        System.out.println(firmRepo.fetchByCount());
//        System.out.println(firmRepo.fetchByType());
//        System.out.println(firmRepo.getAvg());
//        System.out.println(firmRepo.fetchPCAndMaker());
        System.out.println(firmRepo.fetchPrinterAndMaker());
//        System.out.println(firmRepo.fetchCodeSpeedModelPriceByMaker());
//        System.out.println(firmRepo.fetchGetAvgPrice());
//        System.out.println(firmRepo.fetchLaptopAndNotInPrinterByMaker().get());
    }
}
