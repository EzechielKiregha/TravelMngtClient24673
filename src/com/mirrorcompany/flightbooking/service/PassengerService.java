package com.mirrorcompany.flightbooking.service;
import com.mirrorcompany.flightbooking.model.AirlinePassenger;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PassengerService extends Remote{
    AirlinePassenger recordPassenger(AirlinePassenger passenger) throws RemoteException;
    AirlinePassenger deletePassenger(AirlinePassenger passenger) throws RemoteException;
    AirlinePassenger updatePassenger(AirlinePassenger passenger) throws RemoteException;
    AirlinePassenger searchPassenger(AirlinePassenger passenger) throws RemoteException;
    List<AirlinePassenger> thepassenger() throws RemoteException;
}
