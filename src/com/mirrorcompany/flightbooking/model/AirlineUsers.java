/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.flightbooking.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author DRG
 */
@Entity
public class AirlineUsers implements Serializable{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer Id;
    private String first_name;
    private String last_name;
    @Column(name = "Email",unique = true)
    private String mail;
    private String passd;
    private String username;

    public AirlineUsers() {
    }

    public AirlineUsers(String mail) {
        this.mail = mail;
    }

    public AirlineUsers(Integer Id, String first_name, String last_name, String mail, String passd, String username) {
        this.Id = Id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mail = mail;
        this.passd = passd;
        this.username = username;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassd() {
        return passd;
    }

    public void setPassd(String passd) {
        this.passd = passd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    }
