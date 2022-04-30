package com.demo.DrFlight.DAO;

import com.demo.DrFlight.Misc.Repository;
import com.demo.DrFlight.Poco.AirlineCompany;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class AirlineCompanyDao implements Dao<AirlineCompany> {

    List<AirlineCompany> airlineCompanies = new ArrayList<>();

    Connection con = Repository.getCon();
    Statement stm = Repository.getStm();

    @Override
    public AirlineCompany get(long id) {
        AirlineCompany airline = null;
        try {
            var rs = stm.executeQuery("SELECT * FROM airline_companies where airline_companies.id =" + id);
            if (rs.next())
                airline = new AirlineCompany(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getLong("user_id"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airline;
    }

    /**
     * @param userId
     * @return AirlineCompany with matching userId from "airline_companies" table in the database
     */
    public AirlineCompany getByUserId(long userId) {
        AirlineCompany airline = null;
        try {
            var rs = stm.executeQuery("SELECT * FROM airline_companies where airline_companies.user_id =" + userId);
            if (rs.next())
                airline = new AirlineCompany(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getLong("user_id"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airline;
    }

    @Override
    public List<AirlineCompany> getAll() {
        airlineCompanies.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM airline_companies ORDER BY id");
            while (rs.next())
                airlineCompanies.add(new AirlineCompany(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getLong("user_id")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompanies;
    }

    @Override
    public boolean add(AirlineCompany airline) {
        int res = 0;
        try {
            res = stm.executeUpdate("INSERT INTO airline_companies (name,country_id,user_id) " +
                    "VALUES ('" +
                    airline.name + "'," +
                    airline.countryId + "," +
                    airline.userId + ")");
            System.out.println("inserted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean remove(AirlineCompany airline) {
        int res = 0;
        try {
            res = stm.executeUpdate("DELETE FROM airline_companies WHERE airline_companies.id=" + airline.id);
            System.out.println("deleted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean update(AirlineCompany airline) {
        int res = 0;
        try {
            res = stm.executeUpdate("UPDATE airline_companies SET " +
                    "name = '" + airline.name +
                    "',country_id = " + airline.countryId +
                    ",user_id = " + airline.userId +
                    " WHERE airline_companies.id=" + airline.id);
            System.out.println("updated " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public void close() {
        System.out.println("closing airlineCompany dao connections");
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
     * @param country_id
     * @return List of all airlines from a specific country.
     */
    public List<AirlineCompany> getAirlinesByCountry(int country_id) {
        airlineCompanies.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM airline_companies WHERE country_id=" + country_id+" ORDER BY id");
            while (rs.next())
                airlineCompanies.add(new AirlineCompany(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getLong("user_id")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompanies;
    }

    /**
     * Parameters can be sent with null values to ignore them, if all parameters are with null values behave like getAll().
     * @param name
     * @param country
     * @return List of all airlines that have the specific parameters in them.
     */
    public List<AirlineCompany> getAirlinesCompanyByParameters(String name, int country) {
        airlineCompanies.clear();
        String parameters = "";
        if (country > 0) parameters += ",_country:=" + country;
        if (name != null) parameters += ",_name:='" + name+"'";
        if (parameters.length() > 0)
            parameters = parameters.substring(1);
        try {
            var rs = stm.executeQuery("SELECT * FROM get_airlines_by_parameters(" + parameters + ") ORDER BY id");
            while (rs.next())
                airlineCompanies.add(new AirlineCompany(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getLong("user_id")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompanies;
    }
}