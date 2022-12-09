package com.ooadproject.hotelmanagement.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInfo {
    private Integer paymentId;
    private Boolean paymentStatus;
    private Date paymentDate;
    private Double amount;

    public PaymentInfo() {
    }

    public PaymentInfo(Integer paymentId, Boolean paymentStatus, Date paymentDate, Double amount) {
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public Boolean isPaymentStatus() {
        return paymentStatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
