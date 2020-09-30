package jdbctaskusingjpaproviderhibernate2.companypassengerlist;


import jdbctaskusingjpaproviderhibernate2.entity.Trip;
import jdbctaskusingjpaproviderhibernate2.exceptions.DatabaseException;
import jdbctaskusingjpaproviderhibernate2.service.TripService;
import jdbctaskusingjpaproviderhibernate2.service.serviceimpl.TripServiceImpl;

import java.sql.SQLException;

/**
 * Created by User on 22.09.2020.
 */
public class TripController {
    private TripService tripService;
    public TripController(){
        this.tripService = new TripServiceImpl();
    }

    public static void main(String[] args) throws DatabaseException, SQLException {
//        Trip trip = new Trip();
//        trip.setTimeIn("12:12:21");
//        trip.setTimeOut("15:05:41");
//        trip.setTownTo("G");
//        trip.setTownFrom("GG");
        TripController tripController = new TripController();
        //System.out.println(tripController.tripService.save(trip));
       Trip trip =  tripController.tripService.getById(6L);

        System.out.println(trip);
    }
}
