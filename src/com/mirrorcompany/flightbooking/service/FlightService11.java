package com.mirrorcompany.flightbooking.service;

import com.mirrorcompany.flightbooking.model.Flight1;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FlightService11 extends Remote{
    Flight1 recordFlight(Flight1 flight) throws RemoteException;
    Flight1 deleteFlight(Flight1 flight) throws RemoteException;
    Flight1 updateFlight(Flight1 flight) throws RemoteException;
    Flight1 searchFlight(Flight1 flight) throws RemoteException;
    List<Flight1> allFlights1() throws RemoteException;
}
