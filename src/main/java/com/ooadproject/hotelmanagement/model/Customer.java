package com.ooadproject.hotelmanagement.model;

import java.util.Date;

public class Customer extends User{
    private Date signupDate;

    public Customer(Date signupDate) {
        this.signupDate = signupDate;
    }

    public Customer() {
    }

    public Customer(String name, String email, String password, String phone, Date signupDate) {
        super(name, email, password, phone);
        this.signupDate = signupDate;
    }
}
