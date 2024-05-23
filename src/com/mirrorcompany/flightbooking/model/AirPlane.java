package com.mirrorcompany.flightbooking.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class AirPlane implements Serializable{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer Id;
    @Column(name = "Number",unique = true)
    private String number;
    private String type;
    private String editionDate;
    private String status;
    private String No_of_seat;
    private String line;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn( name = "Last_Maintenance")
    private AirlineMaintenance maintenance;
    
    public AirPlane(){
    
    }

    public AirPlane(String number) {
        this.number = number;
    }

    public AirPlane(Integer Id, String number, String type, String editionDate, String status, String No_of_seat, String line, AirlineMaintenance maintenance) {
        this.Id = Id;
        this.number = number;
        this.type = type;
        this.editionDate = editionDate;
        this.status = status;
        this.No_of_seat = No_of_seat;
        this.line = line;
        this.maintenance = maintenance;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(String editionDate) {
        this.editionDate = editionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNo_of_seat() {
        return No_of_seat;
    }

    public void setNo_of_seat(String No_of_seat) {
        this.No_of_seat = No_of_seat;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public AirlineMaintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(AirlineMaintenance maintenance) {
        this.maintenance = maintenance;
    }
   }
