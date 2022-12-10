package com.ooadproject.hotelmanagement.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInfo {
    private String paymentInfoId;
    private boolean paymentCompete;
    private Date paymentDate;
    private double amount;
    private Staff verifyBy;

    public PaymentInfo() {
    }

    public PaymentInfo(String paymentInfoId, boolean paymentCompete, Date paymentDate, double amount, Staff verifyBy) {
        this.paymentInfoId = paymentInfoId;
        this.paymentCompete = paymentCompete;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.verifyBy = verifyBy;
    }

    public String getPaymentInfoId() {
        return paymentInfoId;
    }

    public void setPaymentInfoId(String paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
    }

    public boolean isPaymentCompete() {
        return paymentCompete;
    }

    public void setPaymentCompete(boolean paymentCompete) {
        this.paymentCompete = paymentCompete;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Staff getVerifyBy() {
        return verifyBy;
    }

    public void setVerifyBy(Staff verifyBy) {
        this.verifyBy = verifyBy;
    }
}
