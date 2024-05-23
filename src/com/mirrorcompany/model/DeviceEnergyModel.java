package com.mirrorcompany.model;

public class DeviceEnergyModel {
    
    private int energy_id;
    private int device_id;
    private String device_name;
    private double device_price;
    private double watts_per_hour;
    private String date_connected;
    private double kws_trends;
    private String status;
    private int device_stars;
    private int user_id;

    public DeviceEnergyModel(int energy_id, int device_id, String device_name, double device_price, double watts_per_hour, String date_connected, double kws_trends, String status, int device_stars, int user_id) {
        this.energy_id = energy_id;
        this.device_id = device_id;
        this.device_name = device_name;
        this.device_price = device_price;
        this.watts_per_hour = watts_per_hour;
        this.date_connected = date_connected;
        this.kws_trends = kws_trends;
        this.status = status;
        this.device_stars = device_stars;
        this.user_id = user_id;
    }

    // Getters and setters

    public int getEnergy_id() {
        return energy_id;
    }

    public void setEnergy_id(int energy_id) {
        this.energy_id = energy_id;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public double getDevice_price() {
        return device_price;
    }

    public void setDevice_price(double device_price) {
        this.device_price = device_price;
    }

    public double getWatts_per_hour() {
        return watts_per_hour;
    }

    public void setWatts_per_hour(double watts_per_hour) {
        this.watts_per_hour = watts_per_hour;
    }

    public String getDate_connected() {
        return date_connected;
    }

    public void setDate_connected(String date_connected) {
        this.date_connected = date_connected;
    }

    public double getKws_trends() {
        return kws_trends;
    }

    public void setKws_trends(double kws_trends) {
        this.kws_trends = kws_trends;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDevice_stars() {
        return device_stars;
    }

    public void setDevice_stars(int device_stars) {
        this.device_stars = device_stars;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
}

