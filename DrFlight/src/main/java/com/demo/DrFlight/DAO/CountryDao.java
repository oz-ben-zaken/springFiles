package com.demo.DrFlight.DAO;

import com.demo.DrFlight.Misc.Repository;
import com.demo.DrFlight.Poco.Country;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDao implements Dao<Country>{

    List<Country> countries = new ArrayList<>();
    Repository sqlCon = new Repository();
    Connection con = sqlCon.getCon();
    Statement stm = sqlCon.getStm();

    @Override
    public Country get(long id) {
        Country country = null;
        try {
            var rs = stm.executeQuery("SELECT * FROM countries where countries.id =" + id);
            if (rs.next())
                country = new Country(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("flag"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    @Override
    public List<Country> getAll() {
        countries.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM countries ORDER BY id");
            while (rs.next())
                countries.add(new Country(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("flag")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public boolean add(Country country) {
        int res = 0;
        try {
            res = stm.executeUpdate("INSERT INTO countries (name, flag) VALUES ('"+
                    country.name + "','" +
                    country.flagUrl + "')");
            System.out.println("inserted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean remove(Country country) {
        int res = 0;
        try {
            res = stm.executeUpdate("DELETE FROM countries WHERE countries.id=" + country.id);
            System.out.println("deleted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean update(Country country) {
        int res = 0;
        try {
            res = stm.executeUpdate("UPDATE countries SET " +
                    "  name ='"  + country.name +
                    "',flag ='" +country.flagUrl +
                    "' WHERE countries.id=" + country.id);
            System.out.println("updated " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public void close() {
        System.out.println("closing country dao connections");
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
}