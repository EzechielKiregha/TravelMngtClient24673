/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.flightbooking.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author DRG
 */
@Entity
public class AirlineBooking implements Serializable {
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer Id;
    @Column(name = "BookingNo",unique = true)
    private String booking_no;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "mail")
    private AirlinePassenger passenger;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "FlightNo")
    private Flight1 flight;
    private String departure;
    private Date date =new Date();
    private String arrival;
    @Column(name = "Origine")
    private String from;
    @Column(name = "Destination")
    private String to;
    private String classe;
    private String price;
    private String formOfPayment;
    private String status;
    

    public AirlineBooking() {
    }

    public AirlineBooking(String booking_no) {
        this.booking_no = booking_no;
    }

    public AirlineBooking(Integer Id, String booking_no, AirlinePassenger passenger,Flight1 flight, String departure, String arrival, String from, String to, String classe, String price, String formOfPayment, String status) {
        this.Id = Id;
        this.booking_no = booking_no;
        this.passenger = passenger;
        this.flight = flight;
        this.departure = departure;
        this.arrival = arrival;
        this.from = from;
        this.to = to;
        this.classe = classe;
        this.price = price;
        this.formOfPayment = formOfPayment;
        this.status = status;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getBooking_no() {
        return booking_no;
    }

    public void setBooking_no(String booking_no) {
        this.booking_no = booking_no;
    }

    public AirlinePassenger getPassenger() {
        return passenger;
    }

    public void setPassenger(AirlinePassenger passenger) {
        this.passenger = passenger;
    }

    public Flight1 getFlight() {
        return flight;
    }

    public void setFlight(Flight1 flight) {
        this.flight = flight;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFormOfPayment() {
        return formOfPayment;
    }

    public void setFormOfPayment(String formOfPayment) {
        this.formOfPayment = formOfPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
      
}
