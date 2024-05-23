package com.mirrorcompany.flightbooking.service;

import com.mirrorcompany.flightbooking.model.AirlineMaintenance;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MaintenanceService extends Remote{
    AirlineMaintenance recordMaintenance(AirlineMaintenance maintenance) throws RemoteException;
    AirlineMaintenance deleteMaintenance(AirlineMaintenance maintenance) throws RemoteException;
    AirlineMaintenance updateMaintenance(AirlineMaintenance maintenance) throws RemoteException;
    AirlineMaintenance searchMaintenance(AirlineMaintenance maintenance) throws RemoteException;
    List<AirlineMaintenance> themaintenance() throws RemoteException;
}
