package com.mirrorcompany.community.service;

import com.mirrorcompany.community.model.Chat;
import com.mirrorcompany.model.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatService extends Remote {
    void createChat(Chat chat) throws RemoteException;
    Chat getChatById(Long chatId) throws RemoteException;
    List<Chat> getAllChats() throws RemoteException;
    void updateChat(Chat chat) throws RemoteException;
    void deleteChat(Long chatId) throws RemoteException;
    void addParticipantToChat(Long chatId, User user) throws RemoteException;
    void removeParticipantFromChat(Long chatId, User user) throws RemoteException;
}

