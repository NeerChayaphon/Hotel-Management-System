package com.ooadproject.hotelmanagement.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Booking {
    private Integer bookingId;
    private String customer; //Change type Sting to Customer
    private Integer guests;
    private List<String> room; //Change type String to Room


    public Integer getBookingId() {
        return bookingId;
    }

    public String getCustomer() {
        return customer;
    }

    public Integer getGuests() {
        return guests;
    }

    public List<String> getRoom() {
        return room;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
    }

    public void setRoom(List<String> room) {
        this.room = room;
    }
}
