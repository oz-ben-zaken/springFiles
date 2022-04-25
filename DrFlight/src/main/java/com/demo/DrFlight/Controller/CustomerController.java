package com.demo.DrFlight.Controller;

import com.demo.DrFlight.Facade.CustomerFacade;
import com.demo.DrFlight.Misc.LoginToken;
import com.demo.DrFlight.Poco.Customer;
import com.demo.DrFlight.Poco.Ticket;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    LoginToken token = new LoginToken(1, "1", 1);
    CustomerFacade customerFacade = new CustomerFacade(token);


    @PutMapping("/{id}")
    public void updateCustomer(@RequestBody Customer customer) {
        customerFacade.update_customer(customer, token);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/ticket")
    public boolean addTicket(@RequestBody Ticket ticket) {
        return customerFacade.add_ticket(ticket, token);
    }

    @DeleteMapping("/ticket/{id}")
    public void removeTicket(@PathVariable int id) {
        var ticket = customerFacade.get_my_tickets(token).stream().filter(t->t.id == id).findFirst().orElse(new Ticket(0,0,0));
        customerFacade.remove_ticket(ticket, token);
    }

    @GetMapping("/ticket")
    public List<Ticket> getMyTickets() {
        return customerFacade.get_my_tickets(token);
    }
}
