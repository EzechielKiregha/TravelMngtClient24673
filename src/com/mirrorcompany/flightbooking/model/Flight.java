/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.flightbooking.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author DRG
 */
@Entity
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer Id;
    @Column(name = "FlightNo",unique = true)
    private String flightNo;
    private Date date;
    private String hours;
    private String departure;
    private String arrival;
    @Column(name = "Origine")
    private String from;
    @Column(name = "Destination")
    private String to;
    @Column(name = "Status")
    private String Status;
    @OneToMany(mappedBy = "flight",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<AirlineBooking> booking;

    public Flight() {
    }

    public Flight(String flightNo) {
        this.flightNo = flightNo;
    }

    public Flight(Integer Id, String flightNo, Date date, String hours, String departure, String arrival, String from, String to, String Status, List<AirlineBooking> booking) {
        this.Id = Id;
        this.flightNo = flightNo;
        this.date = date;
        this.hours = hours;
        this.departure = departure;
        this.arrival = arrival;
        this.from = from;
        this.to = to;
        this.Status = Status;
        this.booking = booking;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public List<AirlineBooking> getBooking() {
        return booking;
    }

    public void setBooking(List<AirlineBooking> booking) {
        this.booking = booking;
    }   
        
}
