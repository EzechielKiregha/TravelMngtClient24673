/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.model.bill;

/**
 *
 * @author ekire
 */
import java.math.BigDecimal;
import java.sql.Date;

public class EnergyBillingModel {
    private int billId;
    private int billingSystemId;
    private BigDecimal savedAmount;
    private Date billDate;
    private int userId;
    private BigDecimal userFullEnergy;
    private String month;
    private BigDecimal amountPaid;
    private Date paymentDate;
    private String paymentStatus;

    public EnergyBillingModel(int billingSystemId, BigDecimal savedAmount, Date billDate, int userId, BigDecimal userFullEnergy, String month, BigDecimal amountPaid, String paymentStatus) {
        this.billingSystemId = billingSystemId;
        this.savedAmount = savedAmount;
        this.billDate = billDate;
        this.userId = userId;
        this.userFullEnergy = userFullEnergy;
        this.month = month;
        this.amountPaid = amountPaid;
        this.paymentStatus = paymentStatus;
    }

    public EnergyBillingModel(int billingSystemId, BigDecimal savedAmount, int userId, BigDecimal userFullEnergy, String month, BigDecimal amountPaid, String paymentStatus) {
        this.billingSystemId = billingSystemId;
        this.savedAmount = savedAmount;
        this.userId = userId;
        this.userFullEnergy = userFullEnergy;
        this.month = month;
        this.amountPaid = amountPaid;
        this.paymentStatus = paymentStatus;
    }

    public EnergyBillingModel(int billId, int billingSystemId, BigDecimal savedAmount, Date billDate, int userId, BigDecimal userFullEnergy, String month, BigDecimal amountPaid, Date paymentDate, String paymentStatus) {
        this.billId = billId;
        this.billingSystemId = billingSystemId;
        this.savedAmount = savedAmount;
        this.billDate = billDate;
        this.userId = userId;
        this.userFullEnergy = userFullEnergy;
        this.month = month;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }
    

    public EnergyBillingModel() {
    }
    
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getBillingSystemId() {
        return billingSystemId;
    }

    public void setBillingSystemId(int billingSystemId) {
        this.billingSystemId = billingSystemId;
    }

    public BigDecimal getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(BigDecimal savedAmount) {
        this.savedAmount = savedAmount;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getUserFullEnergy() {
        return userFullEnergy;
    }

    public void setUserFullEnergy(BigDecimal userFullEnergy) {
        this.userFullEnergy = userFullEnergy;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public Object[] dataToBillTable(){
        
        return new Object[]{billId, billDate, month, userId, userFullEnergy, amountPaid, savedAmount, paymentDate, paymentStatus};
    }
}
