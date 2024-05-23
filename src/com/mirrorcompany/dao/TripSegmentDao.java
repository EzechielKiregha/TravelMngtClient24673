package com.mirrorcompany.dao;

import com.mirrorcompany.model.TripSegment;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TripSegmentDao {

    public TripSegmentDao() {
    }

    public boolean addTripSegment(TripSegment tripSegment) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(tripSegment);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public TripSegment findTripSegmentById(Long segmentId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("FROM TripSegment t WHERE t.segmentId = :id");
            q.setParameter("id", segmentId);
            session.close();
            List<TripSegment> tripSegment = q.list();
            if (!tripSegment.isEmpty()){
                return tripSegment.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateTripSegment(TripSegment tripSegment) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(tripSegment);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTripSegment(TripSegment tripSegment) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(tripSegment);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TripSegment> findAllTripSegmentsByItineraryId(Long itineraryId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM TripSegment ts WHERE ts.itinerary.itineraryId = :itineraryId");
            query.setParameter("itineraryId", itineraryId);
            List<TripSegment> tripSegments = query.list();
            session.close();
            return tripSegments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}