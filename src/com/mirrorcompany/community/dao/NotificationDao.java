package com.mirrorcompany.community.dao;

import com.mirrorcompany.community.model.Notification;
import com.mirrorcompany.dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import org.hibernate.Query;

public class NotificationDao {

    public boolean createNotification(Notification notification) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(notification);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Notification getNotificationById(Long notificationId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Notification n WHERE n.notificationId = :id");
            query.setParameter("id", notificationId);
            List<Notification> notifications = query.list();
            if (!notifications.isEmpty()) {
                return notifications.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Notification> getAllNotifications() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Notification");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateNotification(Notification notification) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(notification);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteNotification(Long notificationId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Notification n WHERE n.notificationId = :notificationId");
            query.setParameter("notificationId", notificationId);
            List<Notification> notificationList = query.list();
            if (!notificationList.isEmpty()) {
                Notification notification = notificationList.get(0);
                session.delete(notification);
                transaction.commit();
                return true;
            }
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Notification> getNotificationsByUser(Long userId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Notification n WHERE n.recipient.userId = :userId ORDER BY n.timestamp DESC");
            query.setParameter("userId", userId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean markAsRead(Long notificationId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Notification n WHERE n.notificationId = :notificationId");
            query.setParameter("notificationId", notificationId);
            List<Notification> notificationList = query.list();
            if (!notificationList.isEmpty()) {
                Notification notification = notificationList.get(0);
                notification.setIsRead(true);
                session.update(notification);
                transaction.commit();
                return true;
            }
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
