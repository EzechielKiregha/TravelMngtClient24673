/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.flightbooking.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author DRG
 */
@Entity
public class AirlinePassenger implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String first_name;
    private String last_name;
    private String gender;
    private String Addresse;
    @Column(name = "Phone",unique = true)
    private String phone;
    private String mail;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "passenger")
    private AirlineBooking booking;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "FlightNo")
    private Flight1 flight1;
    
    public AirlinePassenger(){
    }

    public AirlinePassenger(String phone) {
        this.phone = phone;
    }
    public AirlinePassenger(Integer Id, String first_name, String last_name, String gender, String Addresse, String phone, String mail, AirlineBooking booking, Flight1 flight1) {
        this.Id = Id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.Addresse = Addresse;
        this.phone = phone;
        this.mail = mail;
        this.booking = booking;
        this.flight1 = flight1;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddresse() {
        return Addresse;
    }

    public void setAddresse(String Addresse) {
        this.Addresse = Addresse;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public AirlineBooking getBooking() {
        return booking;
    }

    public void setBooking(AirlineBooking booking) {
        this.booking = booking;
    }
    public Flight1 getFlight1() {
        return flight1;
    }

    public void setFlight1(Flight1 flight1) {
        this.flight1 = flight1;
    }
}
