package com.mirrorcompany.model;

import com.mirrorcompany.dao.UserDao;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author ekire
 */
public final class DeviceModelForUser {
    
    public Object[] toDataTable(){
        UserDao userDao = new UserDao();
        
        return new Object[]{deviceID, deviceName, powerUsage, dateConnected, location, status};
    }

    public DeviceModelForUser(int deviceID, int userID, String deviceName, Timestamp dateConnected, double powerUsage, String status, String location) {
        this.deviceID = deviceID;
        this.userID = userID;
        this.deviceName = deviceName;
        this.dateConnected = dateConnected;
        this.powerUsage = powerUsage;
        this.status = status;
        this.location = location;
    }
    
    private int deviceID;
    private int userID;
    private String deviceName;
    private Timestamp dateConnected;
    private double powerUsage;
    private String status;
    private String location;

    public DeviceModelForUser( int userID, String deviceName, Timestamp dateConnected, double powerUsage, String status, String location) {
        
        this.userID = userID;
        this.deviceName = deviceName;
        this.dateConnected = dateConnected;
        this.powerUsage = powerUsage;
        this.status = status;
        this.location = location;
    }

    public Timestamp getDateConnected() {
        return dateConnected;
    }

    public void setDateConnected(Timestamp dateConnected) {
        this.dateConnected = dateConnected;
    }
    
    public DeviceModelForUser() {
    }
    
    

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public double getPowerUsage() {
        return powerUsage;
    }

    public void setPowerUsage(double powerUsage) {
        this.powerUsage = powerUsage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
