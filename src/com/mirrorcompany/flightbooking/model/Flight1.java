/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.flightbooking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author drg
 */
@Entity
public class Flight1 implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "FlightNo", unique = true)
    private String FlightNo;
    private Date date;
    private String hours;
    private String origine;
    private String departure;
    private String arrival;
    private String destination;
    @OneToMany(mappedBy="flight1",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<AirlinePassenger> passenger;
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<AirlineBooking> booking;
    private  String status;

    public Flight1() {
    }

    public Flight1(String FlightNo) {
        this.FlightNo = FlightNo;
    }

    public Flight1(Integer Id, String FlightNo, Date date, String hours, String origine, String departure, String arrival, String destination, List<AirlinePassenger> passenger, List<AirlineBooking> booking, String status) {
        this.Id = Id;
        this.FlightNo = FlightNo;
        this.date = date;
        this.hours = hours;
        this.origine = origine;
        this.departure = departure;
        this.arrival = arrival;
        this.destination = destination;
        this.passenger = passenger;
        this.booking = booking;
        this.status = status;
    }

    
    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getFlightNo() {
        return FlightNo;
    }

    public void setFlightNo(String FlightNo) {
        this.FlightNo = FlightNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<AirlinePassenger> getPassenger() {
        return passenger;
    }

    public void setPassenger(List<AirlinePassenger> passenger) {
        this.passenger = passenger;
    }

    public List<AirlineBooking> getBooking() {
        return booking;
    }

    public void setBooking(List<AirlineBooking> booking) {
        this.booking = booking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
}
