package com.mirrorcompany.community.service;

import com.mirrorcompany.community.model.Message;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MessageService extends Remote {
    void createMessage(Message message) throws RemoteException;
    Message getMessageById(Long messageId) throws RemoteException;
    List<Message> getAllMessages() throws RemoteException;
    void updateMessage(Message message) throws RemoteException;
    void deleteMessage(Long messageId) throws RemoteException;
    List<Message> getMessagesByChat(Long chatId) throws RemoteException;
    List<Message> getMessagesByUser(Long userId) throws RemoteException;
}

