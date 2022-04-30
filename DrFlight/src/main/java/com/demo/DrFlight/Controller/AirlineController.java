package com.demo.DrFlight.Controller;

import com.demo.DrFlight.Facade.AirlineFacade;
import com.demo.DrFlight.Misc.LoginToken;
import com.demo.DrFlight.Poco.AirlineCompany;
import com.demo.DrFlight.Poco.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlineController {

    @Autowired
    LoginToken token;
    @Autowired
    AirlineFacade airlineFacade;

    @GetMapping("/flight")
    public List<Flight> getMyFlights() {
        initializeFacade();
        return airlineFacade.get_my_flights(token);
    }

    @PutMapping("/{id}")
    public void updateAirline(@RequestBody AirlineCompany airline) {
        initializeFacade();
        airlineFacade.update_airline(airline, token);
    }

    @RequestMapping(method = RequestMethod.POST, value = "flight")
    public boolean addFlight(@RequestBody Flight flight) {
        initializeFacade();
        return airlineFacade.add_flight(flight, token);
    }

    @PutMapping("/flight/{id}")
    public void updateFlight(@RequestBody Flight flight) {
        initializeFacade();
        airlineFacade.update_flight(flight, token);
    }

    @DeleteMapping("/ticket/{id}")
    public void removeFlight(@PathVariable int id) {
        initializeFacade();
        var ticket = airlineFacade.get_my_flights(token).stream().filter(t -> t.id == id).findFirst().orElse(new Flight(0, 0, 0, 0, null, null, 0));
        airlineFacade.remove_flight(ticket, token);
    }

    /**
     * instantiate the token in the following cases:
     * <p>--->The token in the facade is not instantiated.
     * <p>--->The token is instantiated but is outdated and does not contain the last login values.
     */
    public void initializeFacade() {
        if (this.token == null || !SecurityContextHolder.getContext().getAuthentication().getName().equalsIgnoreCase(this.token.getName())) {
            this.token = airlineFacade.getToken(SecurityContextHolder.getContext().getAuthentication().getName());
            this.airlineFacade.setToken(token);
        }
    }
}
