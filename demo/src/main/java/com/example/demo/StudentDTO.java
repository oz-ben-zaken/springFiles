package com.example.demo;

public class StudentDTO {
    private String name;
    private String lastname;
    private int id;
    private int year;
    private double avg;

    public StudentDTO() {
    }

    public StudentDTO(String name, String lastname, int id, int year, double avg) {
        this.name = name;
        this.lastname = lastname;
        this.id = id;
        this.year = year;
        this.avg = avg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }
}
