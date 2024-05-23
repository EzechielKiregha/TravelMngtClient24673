/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.service;

import com.mirrorcompany.model.Expense;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ExpenseService extends Remote {
    boolean addExpense(Expense expense) throws RemoteException;
    Expense findExpenseById(Long expenseId) throws RemoteException;
    boolean updateExpense(Expense expense) throws RemoteException;
    boolean deleteExpense(Expense expense) throws RemoteException;
    List<Expense> findAllExpensesByUserId(Long userId) throws RemoteException;
}
