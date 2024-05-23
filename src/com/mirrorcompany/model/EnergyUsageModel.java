package com.mirrorcompany.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author ekire
 */
public class EnergyUsageModel {
    private int energyId;
    private int deviceId;
    private String deviceName;
    private double wattPerHour;
    private Timestamp dateConnected;
    private double kiloWattTrend;
    private String status;
    private double rating;
    private int userId;
    private Timestamp kWh_trend_updated_at;

    public Timestamp getkWh_trend_updated_at() {
        return kWh_trend_updated_at;
    }

    public void setkWh_trend_updated_at(Timestamp kWh_trend_updated_at) {
        this.kWh_trend_updated_at = kWh_trend_updated_at;
    }

    public EnergyUsageModel(int energyId, int deviceId, String deviceName, double wattPerHour, Timestamp dateConnected, double kiloWattTrend, String status, double rating, int userId, Timestamp kWh_trend_updated_at) {
        this.energyId = energyId;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.wattPerHour = wattPerHour;
        this.dateConnected = dateConnected;
        this.kiloWattTrend = kiloWattTrend;
        this.status = status;
        this.rating = rating;
        this.userId = userId;
        this.kWh_trend_updated_at = kWh_trend_updated_at;
    }

    public EnergyUsageModel(int energyId, int deviceId, String deviceName, double wattPerHour, Timestamp dateConnected, double kiloWattTrend, String status, double rating, int userId) {
        this.energyId = energyId;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.wattPerHour = wattPerHour;
        this.dateConnected = dateConnected;
        this.kiloWattTrend = kiloWattTrend;
        this.status = status;
        this.rating = rating;
        this.userId = userId;
    }

    public EnergyUsageModel() {
    }

    public int getEnergyId() {
        return energyId;
    }

    public void setEnergyId(int energyId) {
        this.energyId = energyId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public double getWattPerHour() {
        return wattPerHour;
    }

    public void setWattPerHour(double wattPerHour) {
        this.wattPerHour = wattPerHour;
    }

    public Timestamp getDateConnected() {
        return dateConnected;
    }

    public void setDateConnected(Timestamp dateConnected) {
        this.dateConnected = dateConnected;
    }

    public double getKiloWattTrend() {
        return kiloWattTrend;
    }

    public void setKiloWattTrend(double kiloWattTrend) {
        this.kiloWattTrend = kiloWattTrend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public Object[] dataToEnergyUsageTable(){
        return new Object[]{deviceId, deviceName, wattPerHour, dateConnected, kiloWattTrend, status };
    }
    public Object[] dataToEnergyUsageAdminTable(){
        return new Object[]{energyId, deviceId, deviceName, wattPerHour, dateConnected, kiloWattTrend, status, rating, userId };
    }
    
    public Object[] toEnergyUsageUserTable(){
        return new Object[]{deviceId, deviceName, wattPerHour, dateConnected, kiloWattTrend, status, rating, kWh_trend_updated_at };
    }
}
