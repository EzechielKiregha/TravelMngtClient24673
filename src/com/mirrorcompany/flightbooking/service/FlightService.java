
package com.mirrorcompany.flightbooking.service;

import com.mirrorcompany.flightbooking.model.Flight;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FlightService extends Remote{
    Flight recordFlight(Flight flight) throws RemoteException;
    Flight deleteFlight(Flight flight) throws RemoteException;
    Flight updateFlight(Flight flight) throws RemoteException;
    Flight searchFlight(Flight flight) throws RemoteException;
    List<Flight> theflight() throws RemoteException;
}
