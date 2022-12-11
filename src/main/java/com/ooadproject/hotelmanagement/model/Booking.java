package com.ooadproject.hotelmanagement.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
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

    private Date checkIn;
    private Date checkOut;

    private PaymentInfo paymentInfo;

    public Booking() {
    }

    public Booking(String bookingId, String customerId, int guestAmount, List<String> roomId, Date checkIn, Date checkOut) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.guestAmount = guestAmount;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }


    public void setPaymentInfo(PaymentInfo paymentInfoId) {
        this.paymentInfo = paymentInfoId;
    }
}
