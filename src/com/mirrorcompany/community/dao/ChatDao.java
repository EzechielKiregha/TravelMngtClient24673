package com.mirrorcompany.community.dao;

import com.mirrorcompany.community.model.Chat;
import com.mirrorcompany.dao.HibernateUtil;
import com.mirrorcompany.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ChatDao {

    public boolean createChat(Chat chat) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(chat);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Chat getChatById(Long chatId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Chat WHERE chatId = :chatId");
            query.setParameter("chatId", chatId);
            List<Chat> chatList = query.list();
            if (!chatList.isEmpty()) {
                return chatList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Chat> getAllChats() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Chat");
            List<Chat> chatList = query.list();
            return chatList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateChat(Chat chat) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(chat);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteChat(Long chatId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Chat chat = (Chat) session.get(Chat.class, chatId);
            if (chat != null) {
                session.delete(chat);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addParticipantToChat(Long chatId, User user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Chat chat = (Chat) session.get(Chat.class, chatId);
            if (chat != null) {
                chat.getParticipants().add(user);
                session.update(chat);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeParticipantFromChat(Long chatId, User user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Chat chat = (Chat) session.get(Chat.class, chatId);
            if (chat != null) {
                chat.getParticipants().remove(user);
                session.update(chat);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

