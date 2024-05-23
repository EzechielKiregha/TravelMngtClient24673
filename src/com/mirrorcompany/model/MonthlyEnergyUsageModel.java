/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.model;

/**
 *
 * @author ekire
 */
public class MonthlyEnergyUsageModel {
    private int monthlyEnergyUsageId;
    private int userId;
    private double userFullEnergy;
    private String month;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public MonthlyEnergyUsageModel() {
    }

    public MonthlyEnergyUsageModel(int monthlyEnergyUsageId, int userId, double userFullEnergy, String month) {
        this.monthlyEnergyUsageId = monthlyEnergyUsageId;
        this.userId = userId;
        this.userFullEnergy = userFullEnergy;
        this.month = month;
    }

    public int getMonthlyEnergyUsageId() {
        return monthlyEnergyUsageId;
    }

    public void setMonthlyEnergyUsageId(int monthlyEnergyUsageId) {
        this.monthlyEnergyUsageId = monthlyEnergyUsageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getUserFullEnergy() {
        return userFullEnergy;
    }

    public void setUserFullEnergy(double userFullEnergy) {
        this.userFullEnergy = userFullEnergy;
    }
    
    
}
