package com.example.demo;


public class Restaurant {
    public int id;
    public String name;
    public Address address;
    public String foodType;
    public double mealPrice;

    public Restaurant() {}

    public Restaurant(int id, String name, Address address, String foodType, double mealPrice) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.foodType = foodType;
        this.mealPrice = mealPrice;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", foodType='" + foodType + '\'' +
                ", mealPrice=" + mealPrice +
                '}';
    }
}
