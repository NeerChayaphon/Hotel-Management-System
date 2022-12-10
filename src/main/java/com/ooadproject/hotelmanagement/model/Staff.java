package com.ooadproject.hotelmanagement.model;

public class Staff extends User{
    private String position;
    private double salary;

    public Staff() {
    }

    public Staff(String position, double salary) {
        this.position = position;
        this.salary = salary;
    }

    public Staff(String id, String name, String email, String password, String phone, String position, double salary) {
        super(id, name, email, password, phone);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

