package com.demo.DrFlight;

import com.demo.DrFlight.DAO.*;
import com.demo.DrFlight.Facade.CustomerFacade;
import com.demo.DrFlight.Poco.Country;
import com.demo.DrFlight.Poco.Customer;
import com.demo.DrFlight.Poco.Flight;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class DrFlightApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(DrFlightApplication.class, args);

        FlightDao flightDao = context.getBean(FlightDao.class);
        UserDao userDao = context.getBean(UserDao.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);
        CustomerDao customerDao = context.getBean(CustomerDao.class);
        CountryDao countryDao = context.getBean(CountryDao.class);
        AirlineCompanyDao airlineCompanyDao = context.getBean(AirlineCompanyDao.class);
        AdministratorDao administratorDao = context.getBean(AdministratorDao.class);

        CustomerFacade customerFacade = context.getBean(CustomerFacade.class);

        //flightDao.getAll().forEach(System.out::println);
        //flightDao.getAll().forEach(System.out::println);
        //userDao.getAll().forEach(System.out::println);
        //ticketDao.getAll().forEach(System.out::println);
        //countryDao.getAll().forEach(System.out::println);
        //airlineCompanyDao.getAll().forEach(System.out::println);
        //administratorDao.getAll().forEach(System.out::println);

        //customerDao.getAll().forEach(System.out::println);

    }
}
                  