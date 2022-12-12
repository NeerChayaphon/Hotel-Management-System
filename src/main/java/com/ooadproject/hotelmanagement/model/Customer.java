package com.ooadproject.hotelmanagement.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "customers")
@Data
public class Customer extends User{
    private Date signupDate;

    public Customer() {
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
