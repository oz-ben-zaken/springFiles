package com.demo.DrFlight.DAO;

import com.demo.DrFlight.Misc.Repository;
import com.demo.DrFlight.Poco.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao implements Dao<User>{

    List<User> users = new ArrayList<>();

    Connection con = Repository.getCon();
    Statement stm = Repository.getStm();

    @Override
    public User get(long id) {
        User user = null;
        try {
            var rs = stm.executeQuery("SELECT * FROM users where users.id =" + id);
            if (rs.next())
                user = new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("user_role"),
                        rs.getString("thumbnail"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        users.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM users  ORDER BY id");
            while (rs.next())
                users.add(new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("user_role"),
                        rs.getString("thumbnail")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;    }

    @Override
    public boolean add(User user) {
        int res = 0;
        try {
            res = stm.executeUpdate("INSERT INTO users (username,password,email,user_role,thumbnail) " +
                    "VALUES ('" +
                    user.username + "','" +
                    user.password + "','" +
                    user.email + "'," +
                    user.userRole + ",'" +
                    user.imgUrl + "')");
            System.out.println("inserted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean remove(User user) {
        int res = 0;
        try {
            res = stm.executeUpdate("DELETE FROM users WHERE users.id = " +user.id);
            System.out.println("deleted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean update(User user) {
        int res = 0;
        try {
            res = stm.executeUpdate("UPDATE users SET " +
                    "username = '"   + user.username +
                    "',password = '"  + user.password +
                    "',email = '"     + user.email +
                    "',user_role = " + user.userRole +
                    ",user_role = '" + user.imgUrl +
                    "' WHERE users.id ="+user.id);
            System.out.println("updated " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public void close() {
        System.out.println("closing user dao connections");
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
     * @param username
     * @return User with matching username from users table in the database.<
     */
    public User getUserByUsername(String username){
        User user = null;
        try {
            var rs = stm.executeQuery("SELECT * FROM get_user_by_username('"+username+"')");
            if (rs.next())
                user = new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("user_role"),
                        rs.getString("thumbnail"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;    }
}