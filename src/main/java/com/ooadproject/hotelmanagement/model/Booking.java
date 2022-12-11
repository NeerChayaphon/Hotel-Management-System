package com.ooadproject.hotelmanagement.model;


import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "booking")
@Data
public class Booking {
    @Id
    @ApiModelProperty(readOnly = true)
    private String bookingId;
    private String customerId;
    private int guestAmount;
    private String roomId;
    private PaymentInfo paymentInfo;

    public Booking() {
    }

    public Booking(String bookingId, String customerId, int guestAmount, String roomId, PaymentInfo paymentInfo) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.guestAmount = guestAmount;
        this.roomId = roomId;
        this.paymentInfo = paymentInfo;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
