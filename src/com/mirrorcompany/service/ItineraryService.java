package com.mirrorcompany.service;

import com.mirrorcompany.model.Itinerary;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ekire
 */
public interface ItineraryService extends Remote {
    boolean addItinerary(Itinerary itinerary) throws RemoteException;
    Itinerary findItineraryById(Long itineraryId) throws RemoteException;
    boolean updateItinerary(Itinerary itinerary) throws RemoteException;
    boolean deleteItinerary(Itinerary itinerary) throws RemoteException;
    List<Itinerary> findAllItinerariesByUserId(Long userId) throws RemoteException;
}
