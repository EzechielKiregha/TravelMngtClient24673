package com.mirrorcompany.community.service;

import com.mirrorcompany.community.model.Update;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UpdateService extends Remote {

   
    void createUpdate(Update update) throws RemoteException;

    Update getUpdateById(Long updateId) throws RemoteException;

    List<Update> getAllUpdates() throws RemoteException;

    void updateUpdate(Update update) throws RemoteException;

    void deleteUpdate(Long updateId) throws RemoteException;

    List<Update> getUpdatesByUser(Long userId) throws RemoteException;

    void likeUpdate(Long userId, Long updateId) throws RemoteException;

    void unlikeUpdate(Long userId, Long updateId) throws RemoteException;
}

