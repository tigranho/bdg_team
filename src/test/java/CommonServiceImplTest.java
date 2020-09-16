import model.PC;
import model.Printer;
import org.junit.Assert;
import org.junit.Test;
import repository.FirmRepo;
import repository.FirmRepoImpl;
import service.CommonService;
import service.CommonServiceImpl;

import java.sql.SQLException;
import java.util.*;

public class CommonServiceImplTest {




    @Test
  public  void get() throws SQLException {
        FirmRepo firmRepo = new FirmRepoImpl();
        List<String>list = new ArrayList<>();
        list = firmRepo.fetch("PC");
        Assert.assertEquals("B",list.get(0));
    }

    @Test
    public void getByCountTest() throws SQLException {
        FirmRepo firmRepo = new FirmRepoImpl();
       Map<String,Integer>map = new HashMap<>();
       map = firmRepo.fetchByCount();
       Assert.assertEquals(Optional.of(2).get(),map.get("B"));
       Assert.assertNotEquals(Optional.of(3),map.get("A"));
    }


    @Test
    public void getByTypeTest() throws SQLException {
        FirmRepo firmRepo = new FirmRepoImpl();
         Map<String,String>map = new HashMap<>();
         map = firmRepo.fetchByType();
         Assert.assertEquals(Optional.of("3").get(),map.get("A"));
         Assert.assertNotEquals(5,map.get("B"));
         Assert.assertNotEquals(3,map.get("C"));
         Assert.assertNotEquals(2,map.get("D"));
         Assert.assertNotEquals(5,map.get("E"));
         Assert.assertNotEquals(3,map.get("C"));
            Assert.assertEquals("2",map.get("B"));
            Assert.assertEquals("2",map.get("E"));
    }
@Test
  public void getAvgTest() throws SQLException {
        FirmRepo firmRepo = new FirmRepoImpl();
        Optional<Integer> list ;
       list = firmRepo.getAvg();
        Assert.assertEquals(Optional.of(1003),list);
        Assert.assertNotEquals(Optional.of(700),list);
  }

  @Test
  public void markPCTest() throws SQLException {
        FirmRepo firmRepo = new FirmRepoImpl();
        Map<String,List<PC>>map = new HashMap<>();
        List<PC>list = new ArrayList<>();
        PC pc = new PC();
        pc.setModel("1232");
        pc.setPrice(600);
        pc.setSpeed(500);
        pc.setCode(1);
        pc.setMaker("A");
        map = firmRepo.fetchPCAndMaker();
        Assert.assertEquals(list,map.get("A"));
  }

  @Test
  public void getPrinterMakerTest() throws SQLException {
//      printer.setPrice(resultSet.getInt("code"));
//      printer.setPrice(resultSet.getInt("price"));
//      printer.setModel(resultSet.getString("model"));
//      printer.setMaker(resultSet.getString("maker"));

//        FirmRepo firmRepo = new FirmRepoImpl();
//        Map<String, List<Printer>> map = new HashMap<>();
//        List<Printer>list = new ArrayList<>();
//        map = firmRepo.fetchPrinterAndMaker();
//        Printer printer = new Printer();
//      printer =map.get("E").get(2);
//        list.add(printer);
//        Assert.assertEquals("E",printer.getMaker());
//        Assert.assertEquals("1434",printer.getModel());
//        Assert.assertEquals(290,printer.getPrice());
//        Assert.assertEquals(3,printer.getCode());
  }
}
