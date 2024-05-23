package com.mirrorcompany.flightbooking.service;

import com.mirrorcompany.flightbooking.model.AirPlane;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PlaneService extends Remote{
    AirPlane recordPlane(AirPlane plane) throws RemoteException;
    AirPlane deletePlane(AirPlane plane) throws RemoteException;
    AirPlane updatePlane(AirPlane plane) throws RemoteException;
    AirPlane searchPlane(AirPlane plane) throws RemoteException;
    List<AirPlane> theplane() throws RemoteException;
}
