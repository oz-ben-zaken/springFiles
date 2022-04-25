package com.demo.DrFlight.DAO;

import com.demo.DrFlight.Misc.Repository;
import com.demo.DrFlight.Poco.Flight;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class FlightDao implements Dao<Flight> {

    List<Flight> flights = new ArrayList<>();
    Repository sqlCon = new Repository();
    Connection con = sqlCon.getCon();
    Statement stm = sqlCon.getStm();

    @Override
    public Flight get(long id) {
        Flight flight = null;
        try {
            var rs = stm.executeQuery("SELECT * FROM flights where flights.id =" + id);
            if (rs.next())
                flight = new Flight(
                        rs.getLong("id"),
                        rs.getLong("airline_company_id"),
                        rs.getInt("origin_country_id"),
                        rs.getInt("destination_country_id"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("landing_time"),
                        rs.getInt("remaining_tickets"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    @Override
    public List<Flight> getAll() {
        flights.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM flights ORDER BY id");
            while (rs.next())
                flights.add(new Flight(
                        rs.getLong("id"),
                        rs.getLong("airline_company_id"),
                        rs.getInt("origin_country_id"),
                        rs.getInt("destination_country_id"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("landing_time"),
                        rs.getInt("remaining_tickets")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public boolean add(Flight flight) {
        int res = 0;
        try {
            res = stm.executeUpdate("INSERT INTO flights (airline_company_id,origin_country_id,destination_country_id,departure_time,landing_time,remaining_tickets) " +
                    "VALUES (" +
                    flight.airlineCompanyId + "," +
                    flight.originCountryId + "," +
                    flight.destinationCountryId + ",'" +
                    flight.departureTime + "','" +
                    flight.landingTime + "'," +
                    flight.remainingTickets + ")");
            System.out.println("inserted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean remove(Flight flight) {
        int res = 0;
        try {
            res = stm.executeUpdate("DELETE FROM flights WHERE flights.id=" + flight.id);
            System.out.println("deleted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean update(Flight flight) {
        int res = 0;
        try {
            res = stm.executeUpdate("UPDATE flights SET " +
                    "airline_company_id = " + flight.airlineCompanyId +
                    ",origin_country_id = " + flight.originCountryId +
                    ",destination_country_id = " + flight.destinationCountryId +
                    ",departure_time = '" + flight.departureTime +
                    "',landing_time = '" + flight.landingTime +
                    "',remaining_tickets = " + flight.remainingTickets +
                    " WHERE flights.id=" + flight.id);
            System.out.println("updated " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public void close() {
        System.out.println("closing flight dao connections");
        try {
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parameters can be sent with null values to ignore them, if all parameters are with null values behave like getAll().
     *
     * @param origin
     * @param destination
     * @param departure
     * @param arrival
     * @return List of all flights that have the specified parameters in them.
     */
    public List<Flight> getFlightsByParameters(int origin, int destination, Timestamp departure, Timestamp arrival) {
        flights.clear();
        String parameters = "";
        if (origin > 0) parameters += ",_origin_country_id:=" + origin;
        if (destination > 0) parameters += ",_destination_country_id:=" + destination;
        if (departure != null) parameters += ",_departure_date:='" + departure + "'";
        if (arrival != null) parameters += ",_arrival_date:='" + arrival + "'";
        if (parameters.length() > 0)
            parameters = parameters.substring(1);
        try {
            var rs = stm.executeQuery("SELECT * FROM get_flights_by_parameters(" + parameters + ") ORDER BY id");
            while (rs.next())
                flights.add(new Flight(
                        rs.getLong("id"),
                        rs.getLong("airline_company_id"),
                        rs.getInt("origin_country_id"),
                        rs.getInt("destination_country_id"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("landing_time"),
                        rs.getInt("remaining_tickets")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    /**
     * @param countryId
     * @return list of all flights from the specified origin country id
     */
    public List<Flight> getFlightsByOriginCountryId(int countryId) {
        flights.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM get_flights_by_parameters( _origin_country_id := " + countryId + ") ORDER BY id");
            while (rs.next())
                flights.add(new Flight(
                        rs.getLong("id"),
                        rs.getLong("airline_company_id"),
                        rs.getInt("origin_country_id"),
                        rs.getInt("destination_country_id"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("landing_time"),
                        rs.getInt("remaining_tickets")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    /**
     * @param countryId
     * @return List of all flights to the specified destination country id.
     */
    public List<Flight> getFlightsByDestinationCountryId(int countryId) {
        flights.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM get_flights_by_parameters( _destination_country_id := " + countryId + ") ORDER BY id");
            while (rs.next())
                flights.add(new Flight(
                        rs.getLong("id"),
                        rs.getLong("airline_company_id"),
                        rs.getInt("origin_country_id"),
                        rs.getInt("destination_country_id"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("landing_time"),
                        rs.getInt("remaining_tickets")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    /**
     * @param date
     * @return List of all flights that departs on the specified date.
     */
    public List<Flight> getFlightsByDepartureDate(Timestamp date) {
        flights.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM get_flights_by_parameters(_departure_date:='" + date + "') ORDER BY id");
            while (rs.next())
                flights.add(new Flight(
                        rs.getLong("id"),
                        rs.getLong("airline_company_id"),
                        rs.getInt("origin_country_id"),
                        rs.getInt("destination_country_id"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("landing_time"),
                        rs.getInt("remaining_tickets")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    /**
     * @param date
     * @return List of all flights that lands on the specified date.
     */
    public List<Flight> getFlightsByLandingDate(Timestamp date) {
        flights.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM get_flights_by_parameters(_arrival_date:='" + date + "') ORDER BY id");
            while (rs.next())
                flights.add(new Flight(
                        rs.getLong("id"),
                        rs.getLong("airline_company_id"),
                        rs.getInt("origin_country_id"),
                        rs.getInt("destination_country_id"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("landing_time"),
                        rs.getInt("remaining_tickets")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    /**
     * @param airline_id
     * @return List of all flights that belong to a specific airline company.
     */
    public List<Flight> getFlightsByAirlineCompanyId(long airline_id) {
        flights.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM get_flights_by_airline_id(" + airline_id + ") ORDER BY id");
            while (rs.next())
                flights.add(new Flight(
                        rs.getLong("id"),
                        rs.getLong("airline_company_id"),
                        rs.getInt("origin_country_id"),
                        rs.getInt("destination_country_id"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("landing_time"),
                        rs.getInt("remaining_tickets")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    /**
     * Tries to decrease remaining tickets on a flight with id=flightId.
     *
     * @param flightId
     * @return true if succeeds of false if not.</p>
     * False indicates that a flight is fully booked.
     */
    public boolean decreaseFlightRemainingTickets(long flightId) {
        Flight flight = this.get(flightId);
        if (flight.remainingTickets > 0) {
            flight.remainingTickets -= 1;
            this.update(flight);
            return true;
        }
        return false;
    }

    /**
     * Increase remaining tickets on a flight with id=flightId.
     *
     * @param flightId
     */
    public void increaseFlightRemainingTickets(long flightId) {
        Flight flight = this.get(flightId);
        flight.remainingTickets += 1;
        this.update(flight);
    }
}