package com.demo.DrFlight.Facade;

import com.demo.DrFlight.DAO.*;
import com.demo.DrFlight.Misc.LoginToken;
import com.demo.DrFlight.Poco.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public abstract class FacadeBase {

    @Autowired
    protected FlightDao flightDao;
    @Autowired
    protected AirlineCompanyDao airlineCompanyDao;
    @Autowired
    protected CountryDao countryDao;
    @Autowired
    protected UserDao userDao;

    /**
     * Calls to FlightDao.getAll().
     * @return List of all flights.
     */
    public List<Flight> get_all_flights() {
        return this.flightDao.getAll();
    }


    /**
     * Calls to FlightDao.get(id).
     * @param id
     * @return Flight where flight.id = id.
     */
    public Flight get_flight_by_id(long id) {
        return this.flightDao.get(id);
    }

    /**
     * Calls to FlightDao.getFlightsByParameters(origin_country_id, destination_country_id, departure, arrival).
     * @param origin_country_id can be 0 to ignore.
     * @param destination_country_id can be 0 to ignore.
     * @param departure can be null to ignore.
     * @param arrival can be null to ignore.
     * @return List of all flights with the specified parameters.
     */
    public List<Flight> get_flight_by_parameters(int origin_country_id, int destination_country_id, Timestamp departure, Timestamp arrival) {
        return this.flightDao.getFlightsByParameters(origin_country_id, destination_country_id, departure, arrival);
    }

    /**
     * Calls to airlineCompanyDao.getAll().
     * @return List of all airlines.
     */
    public List<AirlineCompany> get_all_airlines() {
        return this.airlineCompanyDao.getAll();
    }

    /**
     * Calls to airlineCompanyDao.get(id).
     * @param id
     * @return AirlineCompany with airline.id = id.
     */
    public AirlineCompany get_airline_by_id(long id) {
        return this.airlineCompanyDao.get(id);
    }

    /**
     * Calls to airlineCompanyDao.getAirlinesCompanyByParameters(name,country).
     * @param name can be null to ignore.
     * @param country can be 0 to ignore.
     * @return List of all airlines with the specified parameters.
     */
    public List<AirlineCompany> get_airline_by_parameters(String name, int country) {
        return this.airlineCompanyDao.getAirlinesCompanyByParameters(name, country);
    }

    /**
     * Calls to countryDao.getAll().
     * @return List of all countries.
     */
    public List<Country> get_all_countries() {
        return this.countryDao.getAll();
    }

    /**
     * Calls to countryDao.get(id).
     * @param id
     * @return Country with country.id = id.
     */
    public Country get_country_by_id(int id) {
        return this.countryDao.get(id);
    }

    /**
     * Calls to userDao.add(user) in order to create new user in the database.
     * @param user
     */
    public void create_new_user(User user) {
        if (user.password.length() < 6) {
            System.out.println("Password must be at least 6 character long.");
            return;
        }
        this.userDao.add(user);
    }


    /**
     * Prints error message when tokens don't match.
     */
    protected void printAuthenticationError() {
        System.out.println("""

                WARNING, you do not have authority to access this function.
                If you believe we made a mistake please contact our support team.
                """);
    }

    /**
     * Checks if a string contain chars that are not numbers.
     * @param string
     * @return False if str is just numbers or true otherwise.
     */
    protected boolean isNotNumeric(String string) {
        for (int i = 0; i < string.length() - 1; i++)
            if (!Character.isDigit(string.charAt(i)))
                return true;
        return false;
    }

    /**
     * @param username
     * @return LoginToken with the information of username
     */
    public LoginToken getToken(String username){
        User user = userDao.getUserByUsername(username);
        return new LoginToken(user.id,username,user.userRole);
    }
}
