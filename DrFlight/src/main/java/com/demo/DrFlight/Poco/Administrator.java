package com.demo.DrFlight.Poco;

public class Administrator implements Poco{
    public int id;
    public String firstName;
    public String lastName;
    public long userId;

    public Administrator(int id, String firstName, String lastName, long userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
