package com.mirrorcompany.flightbooking.service;

import com.mirrorcompany.flightbooking.model.AirlineBooking;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BookingService extends Remote{
    AirlineBooking recordBooking(AirlineBooking booking) throws RemoteException;
    AirlineBooking deleteBooking(AirlineBooking booking) throws RemoteException;
    AirlineBooking updateBooking(AirlineBooking booking) throws RemoteException;
    AirlineBooking searchBooking(AirlineBooking booking) throws RemoteException;
    List<AirlineBooking> thebooking() throws RemoteException;
}
