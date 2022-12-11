package com.ooadproject.hotelmanagement.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class PaymentInfo {
    @Id
    @ApiModelProperty(readOnly = true)
    private String paymentInfoId;
    private boolean paymentComplete;
    private Date paymentDate;
    private double amount;

    public PaymentInfo() {
    }

    public PaymentInfo(String paymentInfoId, boolean paymentComplete, Date paymentDate, double amount) {
        this.paymentInfoId = paymentInfoId;
        this.paymentComplete = paymentComplete;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public String getPaymentInfoId() {
        return paymentInfoId;
    }

    public void setPaymentInfoId(String paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
    }

    public boolean isPaymentComplete() {
        return paymentComplete;
    }

    public void setPaymentComplete(boolean paymentComplete) {
        this.paymentComplete = paymentComplete;
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
}
