package com.demo.DrFlight.DAO;

import com.demo.DrFlight.Misc.Repository;
import com.demo.DrFlight.Poco.Administrator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdministratorDao implements Dao<Administrator> {

    List<Administrator> administrators = new ArrayList<>();

    Connection con = Repository.getCon();
    Statement stm = Repository.getStm();

    @Override
    public Administrator get(long id) {
        Administrator administrator = null;
        try {
            var rs = stm.executeQuery("SELECT * FROM administrators where administrators.id =" + id);
            if (rs.next())
                administrator = new Administrator(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getLong("user_id"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrator;
    }

    @Override
    public List<Administrator> getAll() {
        administrators.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM administrators  ORDER BY id");
            if (rs.next())
                administrators.add(new Administrator(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getLong("user_id")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrators;
    }

    @Override
    public boolean add(Administrator administrator) {
        int res = 0;
        try {
            res = stm.executeUpdate("INSERT INTO administrators (first_name,last_name,user_id) " +
                    "VALUES ('" +
                    administrator.firstName + "','" +
                    administrator.lastName + "'," +
                    administrator.userId + ")");
            System.out.println("inserted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean remove(Administrator administrator) {
        int res = 0;
        try {
            res = stm.executeUpdate("DELETE FROM administrators WHERE administrators.id=" + administrator.id);
            System.out.println("inserted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean update(Administrator administrator) {
        int res = 0;
        try {
            res = stm.executeUpdate("UPDATE administrators SET" +
                    "first_name = '" + administrator.firstName +
                    "',last_name = '" + administrator.lastName +
                    "',user_id = " + administrator.userId +
                    " WHERE administrator.id=" + administrator.id);
            System.out.println("updated " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public void close() {
        System.out.println("closing administrator dao connections");
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
