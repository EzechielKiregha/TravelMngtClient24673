package com.mirrorcompany.community.dao;

import com.mirrorcompany.community.model.ItineraryChange;
import com.mirrorcompany.dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import org.hibernate.Query;

public class ItineraryChangeDao {

    public boolean createItineraryChange(ItineraryChange itineraryChange) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(itineraryChange);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ItineraryChange> getAllItineraryChanges() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM ItineraryChange");
            List<ItineraryChange> itineraryChanges = query.list();
            return itineraryChanges;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateItineraryChange(ItineraryChange itineraryChange) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();            Transaction transaction = session.beginTransaction();
            session.update(itineraryChange);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteItineraryChange(Long changeId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM ItineraryChange ic WHERE ic.changeId = :changeId");
            query.setParameter("changeId", changeId);
            List<ItineraryChange> itineraryChangeList = query.list();
            if (!itineraryChangeList.isEmpty()) {
                ItineraryChange itineraryChange = itineraryChangeList.get(0);
                session.delete(itineraryChange);
                transaction.commit();
                return true;
            }
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ItineraryChange> getItineraryChangesByUser(Long userId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM ItineraryChange ic WHERE ic.suggestedBy.userId = :userId");
            query.setParameter("userId", userId);
            List<ItineraryChange> itineraryChanges = query.list();
            return itineraryChanges;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ItineraryChange getItineraryChangeById(Long itineraryChangeId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM ItineraryChange ic WHERE ic.suggestedBy.itineraryChangeId = :Id");
            query.setParameter("Id", itineraryChangeId);
            List<ItineraryChange> itineraryChanges = query.list();
            if (itineraryChanges != null && !itineraryChanges.isEmpty())
                return itineraryChanges.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ItineraryChange> getItineraryChangesByItinerary(Long itineraryId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM ItineraryChange ic WHERE ic.itinerary.itineraryId = :itineraryId");
            query.setParameter("itineraryId", itineraryId);
            List<ItineraryChange> itineraryChanges = query.list();
            return itineraryChanges;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


