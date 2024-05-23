package com.mirrorcompany.model;

import java.sql.Timestamp;

/**
 *
 * @author ekire
 */
public class UserDetails {
    private int detailID;
    private int userID;
    private Timestamp detailDate;
    private String city;
    private String district;
    private String quartier;
    private String phone;
    private String homeAge;
    private String noOfRooms;
    private String typeOfHome;

    public UserDetails() {
    }

    public UserDetails(int detailID, int userID, Timestamp detailDate, String city, String district, String quartier, String phone, String homeAge, String noOfRooms, String typeOfHome) {
        this.detailID = detailID;
        this.userID = userID;
        this.detailDate = detailDate;
        this.city = city;
        this.district = district;
        this.quartier = quartier;
        this.phone = phone;
        this.homeAge = homeAge;
        this.noOfRooms = noOfRooms;
        this.typeOfHome = typeOfHome;
    }

    
    
    public UserDetails(int userID, Timestamp detailDate, String city, String district, String quartier, String phone, String homeAge, String noOfRooms, String typeOfHome) {
        
        this.userID = userID;
        this.detailDate = detailDate;
        this.city = city;
        this.district = district;
        this.quartier = quartier;
        this.phone = phone;
        this.homeAge = homeAge;
        this.noOfRooms = noOfRooms;
        this.typeOfHome = typeOfHome;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Timestamp getDetailDate() {
        return detailDate;
    }

    public void setDetailDate(Timestamp detailDate) {
        this.detailDate = detailDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomeAge() {
        return homeAge;
    }

    public void setHomeAge(String homeAge) {
        this.homeAge = homeAge;
    }

    public String getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(String noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getTypeOfHome() {
        return typeOfHome;
    }

    public void setTypeOfHome(String typeOfHome) {
        this.typeOfHome = typeOfHome;
    }
    
    
    
}
