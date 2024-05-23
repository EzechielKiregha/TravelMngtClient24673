package com.mirrorcompany.dao;

/**
 *
 * @author ekire
 */
import com.mirrorcompany.model.Itinerary;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItineraryDao {

    public ItineraryDao() {
    }

    public boolean addItinerary(Itinerary itinerary) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(itinerary);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();    
        }
        return false;
    }

    public Itinerary findItineraryById(Long itineraryId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("FROM Itinerary i WHERE i.itineraryId = :id");
            q.setParameter("id", itineraryId);
            session.close();
            List<Itinerary> itinerary = q.list();
            if(!itinerary.isEmpty()){
                return itinerary.get(0);                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateItinerary(Itinerary itinerary) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(itinerary);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();    
        }
        return false;
    }

    public boolean deleteItinerary(Itinerary itinerary) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(itinerary);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Itinerary> findAllItinerariesByUserId(Long userId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Itinerary> itineraries = session.createQuery("FROM Itinerary i WHERE i.user.userId = :userId")
                    .setParameter("userId", userId)
                    .list();
            session.close();
            return itineraries;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
