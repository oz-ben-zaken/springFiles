package com.example.demo;

public class Address {
    public String Street;
    public int house;
    public short floor;

    public Address() {}

    public Address(String street, int house, short floor) {
        Street = street;
        this.house = house;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Address{" +
                "Street='" + Street + '\'' +
                ", house=" + house +
                ", floor=" + floor +
                '}';
    }
}
