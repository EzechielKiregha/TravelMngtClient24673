package com.mirrorcompany.community.dao;

import com.mirrorcompany.community.model.Comment;
import com.mirrorcompany.dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import org.hibernate.Query;

public class CommentDao {

    public boolean createComment(Comment comment) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(comment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Comment> getAllComments() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Comment");
            List<Comment> comments = query.list();
            return comments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Comment getCommentById(Long commentId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Comment c WHERE c.commentId = :id");
            query.setParameter("id", commentId);
            List<Comment> comments = query.list();
            if (comments != null && !comments.isEmpty())
                return comments.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateComment(Comment comment) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(comment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteComment(Long commentId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Comment c WHERE c.commentId = :commentId");
            query.setParameter("commentId", commentId);
            List<Comment> commentList = query.list();
            if (!commentList.isEmpty()) {
                Comment comment = commentList.get(0);
                session.delete(comment);
                transaction.commit();
                return true;
            }
            transaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Comment> getCommentsByUser(Long userId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Comment c WHERE c.postedBy.userId = :userId");
            query.setParameter("userId", userId);
            List<Comment> comments = query.list();
            return comments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Comment> getCommentsByUpdate(Long updateId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Comment c WHERE c.update.updateId = :updateId");
            query.setParameter("updateId", updateId);
            List<Comment> comments = query.list();
            return comments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
