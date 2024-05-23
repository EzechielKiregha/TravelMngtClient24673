// RecommendationDao.java
package com.mirrorcompany.dao;

import com.mirrorcompany.model.Recommendation;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RecommendationDao {

    public RecommendationDao() {
    }

    public boolean addRecommendation(Recommendation recommendation) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(recommendation);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Recommendation findRecommendationById(Long recommendationId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("FROM Recommendation r WHERE r.recommendationId =:id");
            q.setParameter("id", recommendationId);
            session.close();
            List<Recommendation> recommendation = q.list();
            if(!recommendation.isEmpty()){
                return recommendation.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateRecommendation(Recommendation recommendation) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(recommendation);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRecommendation(Recommendation recommendation) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(recommendation);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Recommendation> findAllRecommendationsByUserId(Long userId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT r FROM Recommendation r JOIN r.users u WHERE u.userId = :userId");
            query.setParameter("userId", userId);
            List<Recommendation> recommendations = query.list();
            session.close();
            return recommendations;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}