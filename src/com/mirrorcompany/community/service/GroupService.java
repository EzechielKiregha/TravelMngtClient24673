package com.mirrorcompany.community.service;

import com.mirrorcompany.community.model.Group;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ekire
 */
public interface GroupService extends Remote {
    boolean createGroup(Group group) throws RemoteException;
    boolean joinGroup(Long userId, Long groupId) throws RemoteException;
    boolean leaveGroup(Long userId, Long groupId) throws RemoteException;
    List<Group> getGroupsByUserId(Long userId) throws RemoteException;
}
