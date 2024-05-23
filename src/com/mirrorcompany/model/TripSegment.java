/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.model;

/**
 *
 * @author ekire
 */
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trip_segments")
public class TripSegment implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "segment_id")
    private Long segmentId;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "accommodation")
    private String accommodation;

    @Column(name = "transportation")
    private String transportation;

    @Column(name = "activities")
    private String activities;

    @ManyToOne
    @JoinColumn(name = "itinerary_id", nullable = false)
    private Itinerary itinerary;

    // Constructors, getters, and setters

    public TripSegment() {
    }

    public TripSegment(String destination, LocalDate startDate, LocalDate endDate, String accommodation, String transportation, String activities, Itinerary itinerary) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accommodation = accommodation;
        this.transportation = transportation;
        this.activities = activities;
        this.itinerary = itinerary;
    }
    
    

    // Getters and setters

    public Long getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Long segmentId) {
        this.segmentId = segmentId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }
    

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }
}
