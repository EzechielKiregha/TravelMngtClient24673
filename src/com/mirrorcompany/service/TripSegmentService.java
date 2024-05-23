package com.mirrorcompany.service;

import com.mirrorcompany.model.TripSegment;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TripSegmentService extends Remote {
    boolean addTripSegment(TripSegment tripSegment) throws RemoteException;
    TripSegment findTripSegmentById(Long segmentId) throws RemoteException;
    boolean updateTripSegment(TripSegment tripSegment) throws RemoteException;
    boolean deleteTripSegment(TripSegment tripSegment) throws RemoteException;
    List<TripSegment> findAllTripSegmentsByItineraryId(Long itineraryId) throws RemoteException;
}
