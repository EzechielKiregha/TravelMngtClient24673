package com.mirrorcompany.model;

/**
 *
 * @author ekire
 */
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "itineraries")
public class Itinerary implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itinerary_id")
    private Long itineraryId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TripSegment> tripSegments;

    // Constructors, getters, and setters

    public Itinerary() {
    }

    public Itinerary(String title, LocalDate startDate, LocalDate endDate, String description, User user, List<TripSegment> tripSegments) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.user = user;
        this.tripSegments = tripSegments;
    }
    
    

    // Getters and setters

    public Long getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TripSegment> getTripSegments() {
        return tripSegments;
    }

    public void setTripSegments(List<TripSegment> tripSegments) {
        this.tripSegments = tripSegments;
    }
}
