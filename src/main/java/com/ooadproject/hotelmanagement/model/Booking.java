package com.ooadproject.hotelmanagement.model;


import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;


@Document(collection = "booking")
@Data
public class Booking {
    @Id
    @ApiModelProperty(readOnly = true)
    private String bookingId;
    private String customerId;
    private int guestAmount;
    private List<String> roomId;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private PaymentInfo paymentInfo;

    public Booking() {
    }

    public Booking(String bookingId, String customerId, int guestAmount, List<String> roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.guestAmount = guestAmount;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getGuestAmount() {
        return guestAmount;
    }

    public void setGuestAmount(int guestAmount) {
        this.guestAmount = guestAmount;
    }

    public List<String> getRoomId() {
        return roomId;
    }

    public void setRoomId(List<String> roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }


    public void setPaymentInfo(PaymentInfo paymentInfoId) {
        this.paymentInfo = paymentInfoId;
    }
}
