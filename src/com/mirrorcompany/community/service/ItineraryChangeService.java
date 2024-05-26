/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.community.service;

import com.mirrorcompany.community.model.ItineraryChange;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ItineraryChangeService extends Remote {
    void createItineraryChange(ItineraryChange itineraryChange) throws RemoteException;
    ItineraryChange getItineraryChangeById(Long changeId) throws RemoteException;
    List<ItineraryChange> getAllItineraryChanges() throws RemoteException;
    void updateItineraryChange(ItineraryChange itineraryChange) throws RemoteException;
    void deleteItineraryChange(Long changeId) throws RemoteException;
    List<ItineraryChange> getItineraryChangesByUser(Long userId) throws RemoteException;
    List<ItineraryChange> getItineraryChangesByItinerary(Long itineraryId) throws RemoteException;
}

