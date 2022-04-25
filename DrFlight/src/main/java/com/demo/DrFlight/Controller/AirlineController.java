package com.demo.DrFlight.Controller;

import com.demo.DrFlight.Facade.AirlineFacade;
import com.demo.DrFlight.Misc.LoginToken;
import com.demo.DrFlight.Poco.AirlineCompany;
import com.demo.DrFlight.Poco.Flight;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlineController {

    LoginToken token = new LoginToken(1, "1", 1);
    AirlineFacade airlineFacade = new AirlineFacade(token);

    @GetMapping("/flight")
    public List<Flight> getMyFlights() {
        return airlineFacade.get_my_flights(token);
    }

    @PutMapping("/{id}")
    public void updateAirline(@RequestBody AirlineCompany airline) {
        airlineFacade.update_airline(airline, token);
    }

    @RequestMapping(method = RequestMethod.POST, value = "flight")
    public boolean addFlight(@RequestBody Flight flight) {
        return airlineFacade.add_flight(flight, token);
    }

    @PutMapping("/flight/{id}")
    public void updateFlight(@RequestBody Flight flight) {
        airlineFacade.update_flight(flight, token);
    }

    @DeleteMapping("/ticket/{id}")
    public void removeFlight(@PathVariable int id) {
        var ticket = airlineFacade.get_my_flights(token).stream().filter(t->t.id == id).findFirst().orElse(new Flight(0,0,0,0,null,null,0));
        airlineFacade.remove_flight(ticket, token);
    }
}
