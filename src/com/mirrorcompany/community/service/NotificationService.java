/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.community.service;

import com.mirrorcompany.community.model.Notification;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface NotificationService extends Remote {
    void createNotification(Notification notification) throws RemoteException;
    Notification getNotificationById(Long notificationId) throws RemoteException;
    List<Notification> getAllNotifications() throws RemoteException;
    void updateNotification(Notification notification) throws RemoteException;
    void deleteNotification(Long notificationId) throws RemoteException;
    List<Notification> getNotificationsByUser(Long userId) throws RemoteException;
    void markAsRead(Long notificationId) throws RemoteException;
}

