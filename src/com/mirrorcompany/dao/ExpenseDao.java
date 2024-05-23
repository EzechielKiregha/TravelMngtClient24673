package com.mirrorcompany.dao;

/**
 *
 * @author ekire
 */
import com.mirrorcompany.model.Expense;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ExpenseDao {

    public ExpenseDao() {
    }

    public boolean addExpense(Expense expense) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(expense);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Expense findExpenseById(Long expenseId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Expense e WHERE e.expenseId = :expenseId");
            query.setParameter("expenseId", expenseId);
            List<Expense> expense = query.list();
            session.close();
            if(expense != null && !expense.isEmpty()){
                return expense.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateExpense(Expense expense) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(expense);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteExpense(Expense expense) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(expense);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Expense> findAllExpensesByUserId(Long userId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Expense> expenses = session.createQuery("FROM Expense e WHERE e.user.userId = :userId")
                    .setParameter("userId", userId)
                    .list();
            session.close();
            return expenses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}