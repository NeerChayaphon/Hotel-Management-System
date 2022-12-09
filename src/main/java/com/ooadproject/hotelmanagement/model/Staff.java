package com.ooadproject.hotelmanagement.model;

public class Staff extends User{
    private String position;
    private Double salary;

    public Staff(){
    }

    public Staff(String name, String email, String password, String phone, String position, Double salary) {
        super(name, email, phone, password);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}

