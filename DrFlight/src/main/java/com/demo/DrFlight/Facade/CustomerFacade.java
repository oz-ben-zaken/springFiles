package com.demo.DrFlight.Facade;

import com.demo.DrFlight.DAO.TicketDao;
import com.demo.DrFlight.Misc.LoginToken;
import com.demo.DrFlight.Poco.Customer;
import com.demo.DrFlight.Poco.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerFacade extends AnonymousFacade {

    @Autowired
    protected TicketDao ticketDao;

    private LoginToken loginToken;

    public CustomerFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    /**
     * Set the facade loginToken to token
     * @param token
     */
    public void setToken(LoginToken token){this.loginToken = token;}

    /**
     * Gets token to authenticate Customer.
     * try to update this customer details.
     * @param customer
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean update_customer(Customer customer, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        if (isNotNumeric(customer.phoneNumber) || isNotNumeric(customer.creditCardNumber)) {
            System.out.println("The entered values are illogical");
            return false;
        }
        return this.customerDao.update(customer);
    }

    /**
     * Gets token to authenticate Customer.
     * try to add a ticket to this customer.
     * @param ticket
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean add_ticket(Ticket ticket, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        if (this.flightDao.decreaseFlightRemainingTickets(ticket.flightId)) {
            return this.ticketDao.add(ticket);
        }
        return false;
    }

    /**
     * Gets token to authenticate Customer.
     * try to remove a ticket from this customer.
     * @param ticket
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean remove_ticket(Ticket ticket, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        this.flightDao.increaseFlightRemainingTickets(ticket.flightId);
        return this.ticketDao.remove(ticket);
    }

    /**
     * Gets token to authenticate Customer.
     * @param loginToken
     * @return List of all the tickets that belong to this customer.
     */
    public List<Ticket> get_my_tickets(LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return null;
        }
        Customer customer = this.customerDao.getByUserId(this.loginToken.GetId());
        return this.ticketDao.getTicketsByCustomer(customer.id);
    }
}
