package com.demo.DrFlight.Facade;

import com.demo.DrFlight.Misc.LoginToken;
import com.demo.DrFlight.Poco.AirlineCompany;
import com.demo.DrFlight.Poco.Flight;

import java.util.List;

public class AirlineFacade extends AnonymousFacade {

    private final LoginToken loginToken;

    public AirlineFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    /**
     * Gets token to authenticate airlineCompany.
     * @param loginToken
     * @return List of all the flights that belong to this airline company.
     */
    public List<Flight> get_my_flights(LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return null;
        }
        return this.flightDao.getFlightsByAirlineCompanyId(loginToken.GetId());
    }

    /**
     * Gets token to authenticate airlineCompany.
     * try to update this airline company details.
     * @param airline
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean update_airline(AirlineCompany airline, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        if (loginToken.GetId() != airline.id) {
            System.out.println("Access denied! You can't change a different airline company info.");
            return false;
        }
        if (airline.countryId <= 0) {
            System.out.println("country id must be a positive number");
            return false;
        }
        return this.airlineCompanyDao.update(airline);
    }

    /**
     * Gets token to authenticate airlineCompany.
     * try to add flight that belong to this airline company.
     * @param flight
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean add_flight(Flight flight, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        if (loginToken.GetId() != flight.airlineCompanyId) {
            System.out.println("Access denied! You can't add flights to a different airline company.");
            return false;
        }
        if (flight.originCountryId == flight.destinationCountryId) {
            System.out.println("Destination country can't be the same as origin country.");
            return false;
        }
        if (flight.departureTime.getTime() >= flight.landingTime.getTime()) {
            System.out.println("Landing time can't be before departure time.");
            return false;
        }
        if (flight.remainingTickets < 0) {
            System.out.println("Remaining tickets can't be negative.");
            return false;
        }
        return this.flightDao.add(flight);
    }

    /**
     * Gets token to authenticate airlineCompany.
     * try to update a flight that belong to this airline company.
     * @param flight
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean update_flight(Flight flight, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        if (loginToken.GetId() != flight.airlineCompanyId) {
            System.out.println("Access denied! You can't change a flight that's belong to a different airline company.");
            return false;
        }
        if (flight.originCountryId == flight.destinationCountryId) {
            System.out.println("Destination country can't be the same as origin country.");
            return false;
        }
        if (flight.departureTime.getTime() >= flight.landingTime.getTime()) {
            System.out.println("Flight can't land before she depart.");
            return false;
        }
        if (flight.remainingTickets < 0) {
            System.out.println("Remaining tickets can't be negative.");
            return false;
        }
        return this.flightDao.update(flight);
    }

    /**
     * Gets token to authenticate airlineCompany.
     * try to remove a flight that belong to this airline company.
     * @param flight
     * @param loginToken
     * @return True if succeeds or false if not.
     */
    public boolean remove_flight(Flight flight, LoginToken loginToken) {
        if (!this.loginToken.toString().equals(loginToken.toString())) {
            printAuthenticationError();
            return false;
        }
        if (loginToken.GetId() != flight.airlineCompanyId) {
            System.out.println("Access denied! You can't remove a flight that's belong to a different airline company.");
            return false;
        }
        if (flight.originCountryId == flight.destinationCountryId) {
            System.out.println("Destination country can't be the same as origin country.");
            return false;
        }
        if (flight.departureTime.getTime() >= flight.landingTime.getTime()) {
            System.out.println("Flight can't land before she depart.");
            return false;
        }
        if (flight.remainingTickets < 0) {
            System.out.println("Remaining tickets can't be negative.");
            return false;
        }
        return this.flightDao.remove(flight);
    }
}
