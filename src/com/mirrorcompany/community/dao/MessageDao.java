package com.mirrorcompany.community.dao;

import com.mirrorcompany.community.model.Message;
import com.mirrorcompany.dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import org.hibernate.Query;

public class MessageDao {

    public boolean createMessage(Message message) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(message);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Message getMessageById(Long messageId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Message m WHERE m.messageId = :Id");
            query.setParameter("Id", messageId);
            List<Message> messages = query.list();
            Message message = messages.get(0);
            if (message != null) {
                return message;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Message> getAllMessages() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Message");
            List<Message> messages = query.list();
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateMessage(Message message) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(message);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMessage(Long messageId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Message m WHERE m.messageId = :Id");
            query.setParameter("Id", messageId);
            List<Message> messages = query.list();
            Message message = messages.get(0);
            if (message != null) {
                session.delete(message);
                transaction.commit();
                return true;
            }
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Message> getMessagesByChat(Long chatId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Message m WHERE m.chat.chatId = :chatId");
            query.setParameter("chatId", chatId);
            List<Message> messages = query.list();
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Message> getMessagesByUser(Long userId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Message m WHERE m.sentBy.userId = :userId");
            query.setParameter("userId", userId);
            List<Message> messages = query.list();
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
