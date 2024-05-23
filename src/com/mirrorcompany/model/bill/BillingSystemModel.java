/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.model.bill;

import java.sql.Date;

/**
 *
 * @author ekire
 */
public class BillingSystemModel {
    private int billingSystemId;
    private int userId;
    private Date subscriptionDate;
    private String subscriptionType;
    private boolean active;
    private String meterNumber;

    public BillingSystemModel() {
    }

    public BillingSystemModel(int billingSystemId, int userId, Date subscriptionDate, String subscriptionType, boolean active, String meterNumber) {
        this.billingSystemId = billingSystemId;
        this.userId = userId;
        this.subscriptionDate = subscriptionDate;
        this.subscriptionType = subscriptionType;
        this.active = active;
        this.meterNumber = meterNumber;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }
    
    public int getBillingSystemId() {
        return billingSystemId;
    }

    public void setBillingSystemId(int billingSystemId) {
        this.billingSystemId = billingSystemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
