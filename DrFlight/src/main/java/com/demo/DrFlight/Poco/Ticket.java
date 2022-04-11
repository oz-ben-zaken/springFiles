package com.demo.DrFlight.Poco;

public class Ticket implements Poco{
    public long id;
    public long flightId;
    public long customerId;

    public Ticket(long id, long flightId, long customerId) {
        this.id = id;
        this.flightId = flightId;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", customerId=" + customerId +
                '}';
    }
}
