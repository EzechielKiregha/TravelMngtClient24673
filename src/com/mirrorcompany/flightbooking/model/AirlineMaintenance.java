package com.mirrorcompany.flightbooking.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class AirlineMaintenance implements Serializable{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer Id;
    @Column(name = "TrackingNo",unique = true)
    private String trackingNo;
    private Date date;
    @OneToOne(mappedBy = "maintenance",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private AirPlane planeNo;
    private String cost;
    private String observation;
    private String period;

    public AirlineMaintenance() {
    }

    public AirlineMaintenance(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public AirlineMaintenance(Integer Id, String trackingNo, Date date, AirPlane planeNo, String cost, String observation, String period) {
        this.Id = Id;
        this.trackingNo = trackingNo;
        this.date = date;
        this.planeNo = planeNo;
        this.cost = cost;
        this.observation = observation;
        this.period = period;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AirPlane getPlaneNo() {
        return planeNo;
    }

    public void setPlaneNo(AirPlane planeNo) {
        this.planeNo = planeNo;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
    
    
}
