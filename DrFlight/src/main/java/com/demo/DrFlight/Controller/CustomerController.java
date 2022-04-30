package com.demo.DrFlight.Controller;

import com.demo.DrFlight.Facade.CustomerFacade;
import com.demo.DrFlight.Misc.LoginToken;
import com.demo.DrFlight.Poco.Customer;
import com.demo.DrFlight.Poco.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    LoginToken token;
    @Autowired
    CustomerFacade customerFacade;


    @PutMapping("/{id}")
    public void updateCustomer(@RequestBody Customer customer) {
        initializeFacade();
        customerFacade.update_customer(customer, token);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/ticket")
    public boolean addTicket(@RequestBody Ticket ticket) {
        initializeFacade();
        return customerFacade.add_ticket(ticket, token);
    }

    @DeleteMapping("/ticket/{id}")
    public void removeTicket(@PathVariable int id) {
        initializeFacade();
        var ticket = customerFacade.get_my_tickets(token).stream().filter(t -> t.id == id).findFirst().orElse(new Ticket(0, 0, 0));
        customerFacade.remove_ticket(ticket, token);
    }

    @GetMapping("/ticket")
    public List<Ticket> getMyTickets() {
        initializeFacade();
        return customerFacade.get_my_tickets(token);
    }

    /**
     * instantiate the token in the following cases:
     * <p>--->The token in the facade is not instantiated.
     * <p>--->The token is instantiated but is outdated and does not contain the last login values.
     */
    public void initializeFacade() {
        if (this.token == null || !SecurityContextHolder.getContext().getAuthentication().getName().equalsIgnoreCase(this.token.getName())) {
            this.token = customerFacade.getToken(SecurityContextHolder.getContext().getAuthentication().getName());
            this.customerFacade.setToken(token);
        }
    }
}
