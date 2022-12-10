package com.ooadproject.hotelmanagement.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Booking {
    private String bookingId;
    private Customer customer;
    private int guestAmount;
    private List<Room> rooms;
    private PaymentInfo paymentInfo;

    public Booking() {
    }

    public Booking(String bookingId, Customer customer, int guestAmount, List<Room> rooms, PaymentInfo paymentInfo) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.guestAmount = guestAmount;
        this.rooms = rooms;
        this.paymentInfo = paymentInfo;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getGuestAmount() {
        return guestAmount;
    }

    public void setGuestAmount(int guestAmount) {
        this.guestAmount = guestAmount;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
