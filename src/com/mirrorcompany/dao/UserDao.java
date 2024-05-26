package com.mirrorcompany.dao;

/**
 *
 * @author ekire
 */
import com.mirrorcompany.model.User;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDao {
    
    public boolean addUser(User user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();    
        }
        return false;
    }
    
    public User findUserByEmail(String email){
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            List<User> u = ss.createQuery("select u from User u where u.username = '"+email+"'").list();
            ss.close();
            if(u != null)
            {
                return u.get(0);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(User user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();    
        }
        return false;
    }

    public boolean deleteUser(User user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> findAllUsers() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM User").list();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    

    public boolean registerUser(User user) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            
        }return false;
    }

    public boolean isEmailDuplicated(String email) {
        User user = findUserByEmail(email);
        return user != null;
    }

    public boolean isUsernameDuplicated(String username) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM User WHERE username = :username");
            query.setParameter("username", username);
            
            return query.list() != null;
        } catch (NoResultException e) {
           e.printStackTrace();
        }
        return false;
    }

    public boolean verifyUser(Long userId, String code) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE userId = :userId AND verificationCode = :code");
            query.setParameter("userId", userId);
            query.setParameter("code", code);
            List<User> u = query.list();
            User user = u.get(0);
            if (user != null) {
                user.setVerificationCode(null);
                user.setStatus("Verified");
                session.update(user);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            
        }return false;
    }

    public boolean loginUser(String email, String password) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM User u WHERE u.email = :email AND u.password = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.list() != null;
        } catch (NoResultException e) {
            e.printStackTrace();
        }return false;
    }

    public String generateVerificationCode() {
        DecimalFormat df = new DecimalFormat("000000");
        Random rand = new Random();
        String code = df.format(rand.nextInt(1000000));
        while (isVerificationCodeDuplicated(code)){
            code = df.format(rand.nextInt(1000000));
        }
        return code;
    }

    public boolean isVerificationCodeDuplicated(String code) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM User WHERE verificationCode = :code");
            query.setParameter("code", code);
            return query.list() != null;
        } catch (NoResultException e) {
            e.printStackTrace();
        }return false;
    }
}
