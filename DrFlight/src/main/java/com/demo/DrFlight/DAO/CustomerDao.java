package com.demo.DrFlight.DAO;

import com.demo.DrFlight.Misc.Repository;
import com.demo.DrFlight.Poco.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements Dao<Customer> {

    List<Customer> customers = new ArrayList<>();
    Repository sqlCon = new Repository();
    Connection con = sqlCon.getCon();
    Statement stm = sqlCon.getStm();

    @Override
    public Customer get(long id) {
        Customer customer = null;
        try {
            var rs = stm.executeQuery("SELECT * FROM customers WHERE customers.id=" + id);
            if (rs.next())
                customer = new Customer(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("phone_no"),
                        rs.getString("credit_card_no"),
                        rs.getLong("user_id"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        customers.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM customers ORDER BY id");
            while (rs.next())
                customers.add(new Customer(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("phone_no"),
                        rs.getString("credit_card_no"),
                        rs.getLong("user_id")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public boolean add(Customer customer) {
        int res = 0;
        try {
            res = stm.executeUpdate("INSERT INTO customers (first_name,last_name,address,phone_no,credit_card_no,user_id) " +
                    "VALUES ('" +
                    customer.firstName + "','" +
                    customer.lastName + "','" +
                    customer.address + "','" +
                    customer.phoneNumber + "','" +
                    customer.creditCardNumber + "'," +
                    customer.userId + ")");
            System.out.println("updated " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean remove(Customer customer) {
        int res = 0;
        try {
            res = stm.executeUpdate("DELETE FROM customers WHERE customers.id=" + customer.id);
            System.out.println("deleted " + res);
        } catch (SQLException e) {
            System.out.println("Can't delete customer. There might be active tickets assign.");
        }
        return !(res == 0);
    }

    @Override
    public boolean update(Customer customer) {
        int res = 0;
        try {
            res = stm.executeUpdate("UPDATE customers SET " +
                    "first_name = '" + customer.firstName +
                    "'last_name = '" + customer.lastName +
                    "'address = '" + customer.address +
                    "'phone_no = '" + customer.phoneNumber +
                    "'credit_card_no = '" + customer.creditCardNumber +
                    "'user_id = " + customer.userId +
                    " WHERE flights.id=" + customer.id);
            System.out.println("updated " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public void close() {
        System.out.println("closing customer dao connections");
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



