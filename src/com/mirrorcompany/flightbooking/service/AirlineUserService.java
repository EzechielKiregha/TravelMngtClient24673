package com.mirrorcompany.flightbooking.service;

import com.mirrorcompany.flightbooking.model.AirlineUsers;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AirlineUserService extends Remote{
    AirlineUsers recordUsers(AirlineUsers user) throws RemoteException;
    AirlineUsers deleteUsers(AirlineUsers user) throws RemoteException;
    AirlineUsers updateUsers(AirlineUsers user) throws RemoteException;
    AirlineUsers searchUsers(AirlineUsers user) throws RemoteException;
    AirlineUsers checkCredentials(AirlineUsers user) throws RemoteException;
    AirlineUsers checkEmail(AirlineUsers user) throws RemoteException;
    List<AirlineUsers> theuser() throws RemoteException;
}
