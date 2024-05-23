package com.mirrorcompany.service;

import com.mirrorcompany.model.Recommendation;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RecommendationService extends Remote {
    boolean addRecommendation(Recommendation recommendation) throws RemoteException;
    Recommendation findRecommendationById(Long recommendationId) throws RemoteException;
    boolean updateRecommendation(Recommendation recommendation) throws RemoteException;
    boolean deleteRecommendation(Recommendation recommendation) throws RemoteException;
    List<Recommendation> findAllRecommendationsByUserId(Long userId) throws RemoteException;
}
