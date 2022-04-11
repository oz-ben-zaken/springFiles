package com.demo.DrFlight.Controller;

import com.demo.DrFlight.Facade.AdministratorFacade;
import com.demo.DrFlight.Facade.CustomerFacade;
import com.demo.DrFlight.Misc.LoginToken;
import com.demo.DrFlight.Poco.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
    LoginToken token = new LoginToken(1, "1", 1);
    AdministratorFacade administratorFacade = new AdministratorFacade(token);

    @GetMapping("/customer")
    public List<Customer> getAllCustomers(){
        return administratorFacade.get_all_customers(token);
    }

    @RequestMapping(method= RequestMethod.POST,value = "/airline")
    public boolean addAirline(@RequestBody AirlineCompany airline,
                              @RequestBody User user){
        return administratorFacade.add_airline(airline, user,token);
    }

    @RequestMapping(method= RequestMethod.POST,value = "/customer")
    public boolean addCustomer(@RequestBody Customer customer,
                               @RequestBody User user){
        return administratorFacade.add_customer(customer, user, token);
    }

    @RequestMapping(method= RequestMethod.POST,value = "/admin")
    public boolean addAdministrator(@RequestBody Administrator admin,
                                    @RequestBody User user){
        return administratorFacade.add_administrator(admin, user, token);
    }


    @DeleteMapping("/airline/{id}")
    public void removeAirline(@PathVariable int id) {
        var airline = administratorFacade.get_airline_by_id(id);
        administratorFacade.remove_airline(airline, token);
    }

    @DeleteMapping("/customer/{id}")
    public void removeCustomer(@PathVariable int id) {
        var customer = administratorFacade.get_all_customers(token).stream().filter(c->c.id == id).findFirst().orElse(new Customer(0,null,null,null,null,null,0));
        administratorFacade.remove_customer(customer, token);
    }

    @DeleteMapping("/admin/{id}")
    public void removeAdministrator(@PathVariable int id) {
        var admin = administratorFacade.getAdministratorById(id,token);
        administratorFacade.remove_administrator(admin, token);
    }
}
