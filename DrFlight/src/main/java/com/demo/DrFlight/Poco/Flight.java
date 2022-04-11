package com.demo.DrFlight.Poco;

import java.sql.Timestamp;

public class Flight implements Poco {
    public long id;
    public long airlineCompanyId;
    public int originCountryId;
    public int destinationCountryId;
    public Timestamp departureTime;
    public Timestamp landingTime;
    public int remainingTickets;

    public Flight(long id, long airlineCompanyId, int originCountryId, int destinationCountryId, Timestamp departureTime, Timestamp landingTime, int remainingTickets) {
        this.id = id;
        this.airlineCompanyId = airlineCompanyId;
        this.originCountryId = originCountryId;
        this.destinationCountryId = destinationCountryId;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.remainingTickets = remainingTickets;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airlineId=" + airlineCompanyId +
                ", originId=" + originCountryId +
                ", destinationId=" + destinationCountryId +
                ", departure= " + departureTime.toString().split("\\.")[0] +
                ", landing= " + landingTime.toString().split("\\.")[0] +
                ", remainingTickets=" + remainingTickets +
                '}';
    }
}
