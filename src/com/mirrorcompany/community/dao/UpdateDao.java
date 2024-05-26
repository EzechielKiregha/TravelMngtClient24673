package com.mirrorcompany.community.dao;

import com.mirrorcompany.community.model.Update;
import com.mirrorcompany.dao.HibernateUtil;
import com.mirrorcompany.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import org.hibernate.Query;

public class UpdateDao {

    public boolean createUpdate(Update update) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(update);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Update getUpdateById(Long updateId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Update u WHERE u.updateId = :id");
            query.setParameter("id", updateId);
            List<Update> updates = query.list();
            if (!updates.isEmpty()) {
                return updates.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Update> getAllUpdates() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Update");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUpdate(Update update) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(update);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUpdate(Long updateId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Update u WHERE u.updateId = :updateId");
            query.setParameter("updateId", updateId);
            List<Update> updateList = query.list();
            if (!updateList.isEmpty()) {
                Update update = updateList.get(0);
                session.delete(update);
                transaction.commit();
                return true;
            }
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Update> getUpdatesByUser(Long userId) {
        try {
           Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Update u WHERE u.postedBy.userId = :userId ORDER BY u.postedAt DESC");
            query.setParameter("userId", userId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean likeUpdate(Long userId, Long updateId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query userQuery = session.createQuery("FROM User u WHERE u.userId = :userId");
            userQuery.setParameter("userId", userId);
            List<User> userList = userQuery.list();
            if (!userList.isEmpty()) {
                User user = userList.get(0);
                Query updateQuery = session.createQuery("FROM Update u WHERE u.updateId = :updateId");
                updateQuery.setParameter("updateId", updateId);
                List<Update> updateList = updateQuery.list();
                if (!updateList.isEmpty()) {
                    Update update = updateList.get(0);
                    update.getLikes().add(user);
                    session.update(update);
                    transaction.commit();
                    return true;
                }
            }
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean unlikeUpdate(Long userId, Long updateId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query userQuery = session.createQuery("FROM User u WHERE u.userId = :userId");
            userQuery.setParameter("userId", userId);
            List<User> userList = userQuery.list();
            if (!userList.isEmpty()) {
                User user = userList.get(0);
                Query updateQuery = session.createQuery("FROM Update u WHERE u.updateId = :updateId");
                updateQuery.setParameter("updateId", updateId);
                List<Update> updateList = updateQuery.list();
                if (!updateList.isEmpty()) {
                    Update update = updateList.get(0);
                    update.getLikes().remove(user);
                    session.update(update);
                    transaction.commit();
                    return true;
                }
            }
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
