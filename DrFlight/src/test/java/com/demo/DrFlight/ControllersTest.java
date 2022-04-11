package com.demo.DrFlight;

import com.demo.DrFlight.DAO.FlightDao;
import com.demo.DrFlight.Poco.Flight;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class ControllersTest {

    @Test
    public void contextLoads() {
    }

    @Test
    public void getFlightByIdFromAnonymousControllerTest() {
        String url = "http://localhost:8080/anonymous/flight/3";
        var expected = HttpTestManager.getResponse(url,Flight.class);
        FlightDao flightDao= new FlightDao();
        var current = flightDao.get(3);
        Assert.assertEquals(current.toString(), expected.toString());
    }

    @Test
    public void getFlightsFromAnonymousControllerTest() {
        String url = "http://localhost:8080/anonymous/flight/";
        var expected = HttpTestManager.getResponse(url,Flight[].class);
        FlightDao flightDao= new FlightDao();
        var current = flightDao.get(3);
        Assert.assertEquals(current.toString(), expected[2].toString());
    }

    @Test
    public void getFlightByParametersFromAnonymousControllerTest() {
        String url ="http://localhost:8080/anonymous/flight/1/7/2022-03-21%2017:51:53";
        var expected = HttpTestManager.getResponse(url,Flight[].class);
        FlightDao flightDao= new FlightDao();
        var current = flightDao.getFlightsByParameters(1,7,Timestamp.valueOf("2022-03-21 17:51:53"),null);
        Assert.assertEquals(current.get(0).toString(), expected[0].toString());
    }
}
