
package com.mirrorcompany.service;

import com.mirrorcompany.model.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserService extends Remote {
    boolean addUser(User user) throws RemoteException;
    User findUserByEmail(User user) throws RemoteException;
    boolean updateUser(User user) throws RemoteException;
    boolean deleteUser(User user) throws RemoteException;
    boolean verifyUserCredentials(User user) throws RemoteException;
    List<User> findAllUsers() throws RemoteException;
}
