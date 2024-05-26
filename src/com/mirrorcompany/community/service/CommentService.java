/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.community.service;

import com.mirrorcompany.community.model.Comment;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CommentService extends Remote {
    void createComment(Comment comment) throws RemoteException;
    Comment getCommentById(Long commentId) throws RemoteException;
    List<Comment> getAllComments() throws RemoteException;
    void updateComment(Comment comment) throws RemoteException;
    void deleteComment(Long commentId) throws RemoteException;
    List<Comment> getCommentsByUser(Long userId) throws RemoteException;
    List<Comment> getCommentsByUpdate(Long updateId) throws RemoteException;
}

