package com.demo.DrFlight.Controller;

import com.demo.DrFlight.Facade.AnonymousFacade;
import com.demo.DrFlight.Facade.FacadeBase;
import com.demo.DrFlight.Poco.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/anonymous")
public class AnonymousController {

    @Autowired
    AnonymousFacade anonymousFacade;

    @GetMapping("/flight")
    public List<Flight> getAll(){
        return anonymousFacade.get_all_flights();
    }

    @GetMapping("/flight/{id}")
    public Flight getFlight(@PathVariable int id){
        return anonymousFacade.get_flight_by_id(id);
    }

    @GetMapping("/flight/{country1}/{country2}/{date}")
    public  List<Flight> getFlightByParameters(@PathVariable int country1,
                                               @PathVariable int country2,
                                               @PathVariable Timestamp date) {
        return anonymousFacade.get_flight_by_parameters(country1, country2, date, null);
    }

    @GetMapping("/airline")
    public List<AirlineCompany> getAllAirlines(){
        return anonymousFacade.get_all_airlines();
    }

    @GetMapping("/airline/{id}")
    public AirlineCompany getAirline(@PathVariable int id){
        return anonymousFacade.get_airline_by_id(id);
    }

    @GetMapping("/airline/{name}/{country}")
    public List<AirlineCompany> getAirlineByParameters(@PathVariable String name,
                                                       @PathVariable int country){
        return anonymousFacade.get_airline_by_parameters(name,country);
    }

    @GetMapping("/country")
    public List<Country> getAllCountries(){
        return anonymousFacade.get_all_countries();
    }

    @GetMapping("/country/{id}")
    public Country getCountry(@PathVariable int id){
        return anonymousFacade.get_country_by_id(id);
    }

    @GetMapping("/login/{username}/{password}")
    public FacadeBase login(@PathVariable String username,
                            @PathVariable String password){
        return anonymousFacade.Login(username,password);
    }

    @RequestMapping(method= RequestMethod.POST,value = "")
    public boolean addCustomer(@RequestBody Customer customer,
                               @RequestBody User user){
        return anonymousFacade.add_customer(customer, user);
    }

}
