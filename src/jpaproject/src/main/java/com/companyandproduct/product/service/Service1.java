package com.companyandproduct.product.service;

import com.companyandproduct.product.entity.PC;
import com.companyandproduct.product.entity.Printer;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.*;

public class Service1 {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA_Product");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

  public List<String> fetch(){
        entityManager.getTransaction().begin();
        List<String>list = new ArrayList<>();
       TypedQuery<String>query =  entityManager.createQuery("select distinct maker from " +
                "Product where type ='PC'",String.class);
        List<String>list1 = query.getResultList();
          for (String l:list1){
              System.out.println(l);
          }
          entityManager.getTransaction().commit();
          return list1;
    }

   public Map<String,Long> fetchByCount(){
     entityManager.getTransaction().begin();
     Map<String,Long>map = new HashMap<>();
  Query query = entityManager.createQuery("select  p.maker, count(p.model) " +
          "from Product as p group by p.maker");
List<Object[]>resultList = query.getResultList();
       for (Object[] result : resultList) {
           map.put((String) result[0], ((Long) result[1]));
       }
       System.out.println(map);
   entityManager.getTransaction().commit();
   return map;
   }

   public Map<String,Long>fetchByType() {
     entityManager.getTransaction().begin();
     Query query = entityManager.createQuery("select maker, count(distinct type) from Product" +
             " group by maker ");
     Map<String,Long>map = new HashMap<>();
     List<Object[]>resultSet = query.getResultList();
     for (Object[]result:resultSet){
         map.put( (String) result[0] , ((Long)result[1]));
     }
       System.out.println(map);
     entityManager.getTransaction().commit();

     return map;
   }
//
public Optional<Double> getAvg() throws SQLException{
      entityManager.getTransaction().begin();
   Double d;
       Query query = entityManager.createQuery("select  avg(price) from Laptop");
   d = (Double) query.getSingleResult();

    System.out.println(d);
    entityManager.getTransaction().commit();
    return d==null?Optional.empty():Optional.of(d);
}
//
   public   Map<String, List<PC>> fetchPCAndMaker() throws SQLException{
      entityManager.getTransaction().begin();
      Map<String,List<PC>>map = new HashMap<>();
       List<Object[]>objects1 = entityManager.createQuery("select distinct p.code,p.model, " +
               "p.speed,p.price,pp.maker  from PC as p join Product as pp on p.model = pp.model").getResultList();
     for (Object[] objects2:objects1){
         List<PC>list = new ArrayList<>();
         PC pc = new PC();
         pc.setCode((Integer) objects2[0]);
         pc.setModel((String)objects2[1]);
         pc.setSpeed((Integer) objects2[2]);
         pc.setPrice((Float)objects2[3]);
         if (map.containsKey((String) objects2[4])) {
             List<PC> list1 = map.get((String) objects2[4]);
             list1.add(pc);
             map.put((String) objects2[4], list1);

         }else {
             list.add(pc);
             map.put((String) objects2[4],list);
         }
     }
       System.out.println(map);
      entityManager.getTransaction().commit();
      return map;
     }
//
  public Map<String, List<Printer>> fetchPrinterAndMaker() throws SQLException{
      entityManager.getTransaction().begin();
      Map<String,List<Printer>>map = new HashMap<>();
     Query query = entityManager.createQuery("select p.code,p.price,p.model," +
             "t.maker from Printer p join Product t on p.model = t.model where color = 'y' order by code desc");
     List<Object[]>objects = query.getResultList();
     for (Object[]objects1:objects){
       List<Printer>list = new ArrayList<>();
        Printer printer = new Printer();
        printer.setCode((Integer) objects1[0]);
        printer.setPrice((Float) objects1[1]);
        printer.setModel((String) objects1[2]);
        printer.setMaker((String) objects1[3]);

        if (map.containsKey(objects1[3])){
            List<Printer>list1 = map.get(objects1[3]);
            list1.add(printer);
            map.put((String) objects1[3],list1);
        }else {
            list.add(printer);
            map.put((String)objects1[3],list);
        }
     }
        System.out.println(map);
     entityManager.getTransaction().commit();
     return map;
    }
//

public Map<String, List<PC>> fetchCodeSpeedModelPriceByMaker(){
      entityManager.getTransaction().begin();
      Map<String,List<PC>>map = new HashMap<>();
      Query query = entityManager.createQuery("select c.code, c.model," +
              "c.speed,c.price,p.maker from PC c  join   Product p on c.model=p.model");
      List<Object[]>objects = query.getResultList();
      for (Object[]objects1:objects){
          List<PC>list = new ArrayList<>();
          PC pc = new PC();
          pc.setCode((Integer) objects1[0]);
          pc.setModel((String) objects1[1]);
          pc.setSpeed((Integer) objects1[2]);
          pc.setPrice((Float)objects1[3]);
          if (map.containsKey(objects1[4])){
              List<PC>list1 = map.get(objects1[4]);
              list1.add(pc);
              map.put((String) objects1[4],list1);
          }else {
              list.add(pc);
              map.put((String) objects1[4],list);
          }
      }

     System.out.println(map);
      entityManager.getTransaction().commit();
      return map;
 }
//
   public Map<String, Double> fetchGetAvgPrice(){
      entityManager.getTransaction().begin();
      Map<String,Double>map = new HashMap<>();
      Query query = entityManager.createQuery("select p.maker," +
              "coalesce(avg(c.price),0 ) from  Product p left join PC c on p.model = c.model group by p.maker");
      List<Object[]>objects = query.getResultList();
     for (Object[]objects1:objects){
         map.put((String) objects1[0], (Double) objects1[1]);
     }
        System.out.println(map);
     entityManager.getTransaction().commit();
     return map;
    }
//
    public   Optional<List<String>> fetchPCAndPrinterByMaker  (){
      entityManager.getTransaction().begin();
      List<String>list = new ArrayList<>();
      Query query = entityManager.createQuery("select p.maker" +
              "from PC c join Product p on c.model = p.model " +
              "where p.maker in (select r.maker from " +
              "Printer  r join Product p on r.model = p.model where color = 'y')");
      list = query.getResultList();
        System.out.println(list);
        entityManager.getTransaction().commit();
        return list==null?Optional.empty():Optional.of(list);
    }
//
   public Optional<List<String>> fetchLaptopAndNotInPrinterByMaker(){
     entityManager.getTransaction().begin();
     Query query = entityManager.createQuery("select p.maker from " +
             "Product p join Laptop l on" +
             " p.model = l.model where l.speed > 500 and p.maker  not in (select " +
             "p.maker from Product p join Printer r on p.model =r.model)");

     List<String>list = new ArrayList<>();
     list = query.getResultList();
       System.out.println(list);
       entityManager.getTransaction().commit();
       return list==null?Optional.empty():Optional.of(list);
    }
}
