package com.demo.DrFlight.DAO;

import com.demo.DrFlight.Misc.Repository;
import com.demo.DrFlight.Poco.Ticket;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements Dao<Ticket>{

    List<Ticket> tickets = new ArrayList<>();
    Repository sqlCon = new Repository();
    Connection con = sqlCon.getCon();
    Statement stm = sqlCon.getStm();

    @Override
    public Ticket get(long id) {
        Ticket ticket=null;
        try {
            var rs = stm.executeQuery("SELECT * FROM tickets WHERE tickets.id="+id);
            if (rs.next())
                ticket = (new Ticket(
                        rs.getLong("id"),
                        rs.getLong("flight_id"),
                        rs.getLong("customer_id")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        tickets.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM tickets ORDER BY id");
            while (rs.next())
                tickets.add(new Ticket(
                        rs.getLong("id"),
                        rs.getLong("flight_id"),
                        rs.getLong("customer_id")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public boolean add(Ticket ticket) {
        int res = 0;
        try {
            res = stm.executeUpdate("INSERT INTO tickets (flight_id,customer_id) " +
                    "VALUES (" +
                    ticket.flightId + "," +
                    ticket.customerId + ")");
            System.out.println("inserted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean remove(Ticket ticket) {
        int res = 0;
        try {
            res = stm.executeUpdate("DELETE FROM tickets WHERE tickets.id=" +ticket.id);
            System.out.println("deleted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public boolean update(Ticket ticket) {
        int res = 0;
        try {
            res = stm.executeUpdate("UPDATE tickets SET " +
                    "flight_id = " + ticket.flightId +
                    ",customer_id = " + ticket.customerId+
                    " WHERE tickets.id=" + ticket.id);
            System.out.println("updated " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(res == 0);
    }

    @Override
    public void close() {
        System.out.println("closing ticket dao connections");
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
     * @param customerId
     * @return List of all tickets that belong to a specific customer.
     */
    public List<Ticket> getTicketsByCustomer(long customerId){
        tickets.clear();
        try {
            var rs = stm.executeQuery("SELECT * FROM get_tickets_by_customer("+customerId+") ORDER BY id");
            while (rs.next())
                tickets.add(new Ticket(
                        rs.getLong("id"),
                        rs.getLong("flight_id"),
                        rs.getLong("customer_id")));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}