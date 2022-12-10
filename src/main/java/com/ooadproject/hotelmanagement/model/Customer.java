package com.ooadproject.hotelmanagement.model;

import java.util.Date;

public class Customer extends User{
    private Date signupDate;

    public Customer() {
    }

    public Customer(Date signupDate) {
        this.signupDate = signupDate;
    }

    public Customer(String id, String name, String email, String password, String phone, Date signupDate) {
        super(id, name, email, password, phone);
        this.signupDate = signupDate;
    }

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }
}
