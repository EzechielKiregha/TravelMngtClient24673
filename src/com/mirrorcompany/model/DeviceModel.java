package com.mirrorcompany.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author ekire
 */
public class DeviceModel {
    
    private int device_id;
    private String device_name;
    private double kWh_per_hour;
    private int num_users;
    private int num_reviews ;

    public DeviceModel(int device_id, String device_name, double kWhPerHour, int num_users, int num_reviews) {
        this.device_id = device_id;
        this.device_name = device_name;
        this.kWh_per_hour = kWhPerHour;
        this.num_users = num_users;
        this.num_reviews = num_reviews;
    }

    public DeviceModel() {
    }

    public double getkWh_per_hour() {
        return kWh_per_hour;
    }

    public void setkWh_per_hour(double kWh_per_hour) {
        this.kWh_per_hour = kWh_per_hour;
    }
    

    public Object[] toDataTable(){
        return new Object[]{device_id, device_name, kWh_per_hour, num_users, num_reviews };
    }
    
    public Object[] toDeviceList(){
        return new Object[]{device_name, kWh_per_hour};
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

    public int getNum_users() {
        return num_users;
    }

    public void setNum_users(int num_users) {
        this.num_users = num_users;
    }

    public int getNum_reviews() {
        return num_reviews;
    }

    public void setNum_reviews(int num_reviews) {
        this.num_reviews = num_reviews;
    }
}