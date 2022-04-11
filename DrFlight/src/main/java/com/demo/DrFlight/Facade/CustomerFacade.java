package com.demo.DrFlight.Facade;

import com.demo.DrFlight.DAO.TicketDao;
import com.demo.DrFlight.Misc.LoginToken;
import com.demo.DrFlight.Poco.Customer;
import com.demo.DrFlight.Poco.Ticket;

import java.util.List;

public class CustomerFacade extends AnonymousFacade {

    protected TicketDao ticketDao = new TicketDao();
    private final LoginToken loginToken;

    public CustomerFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

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
        if (loginToken.GetId() != customer.id) {
            System.out.println("Access denied! You can't change a different user info.");
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
        if (loginToken.GetId() != ticket.customerId) {
            System.out.println("Access denied! You can't add ticket to a different user.");
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
        if (loginToken.GetId() != ticket.customerId) {
            System.out.println("Access denied! You can't remove ticket from a different user.");
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
        return this.ticketDao.getTicketsByCustomer(this.loginToken.GetId());
    }
}
