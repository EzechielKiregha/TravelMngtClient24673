/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.flightbooking.dao;

import com.mirrorcompany.dao.HibernateUtil;
import com.mirrorcompany.flightbooking.model.AirlineUsers;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author drg
 */
public class AirlineUserDao {

    public AirlineUserDao() {
    }
    public AirlineUsers add_User(AirlineUsers user){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            ss.save(user);
            ss.beginTransaction().commit();
            return user;
            
        } catch (Exception e) {
            e.printStackTrace();     
        }
        return null;
    }
    public AirlineUsers delete_User(AirlineUsers user){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            ss.delete(user);
            ss.beginTransaction().commit();
            return user;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }   
    
    public AirlineUsers update_User(AirlineUsers user){
            try {
                Session ss=HibernateUtil.getSessionFactory().openSession();
                ss.update(user);
                ss.beginTransaction().commit();
                return user;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
    
    public  List<AirlineUsers> user(){
        try {
            Session ss= HibernateUtil.getSessionFactory().openSession();
            List <AirlineUsers> theUser = ss.createQuery("SELECT theUser from User theUser").list();
            ss.close();
            return theUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public AirlineUsers search_user(AirlineUsers user){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
           // Users theuser = (Users) ss.get(Users.class, user.getMail());
            List<AirlineUsers> User =ss.createQuery("select User from Users User where User.username ='"+user.getMail()+"'").list();
            ss.close();
            if(User!=null)
            {
            
            return User.get(0);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return null;
    }
    public AirlineUsers checkCredentials(AirlineUsers user) {
    try {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Users WHERE username = :username AND passd = :passd");
        query.setParameter("username", user.getUsername());
        query.setParameter("passd", user.getPassd()); 
        
        List<AirlineUsers> userList = query.list();
        session.close();
        
        if (userList != null && !userList.isEmpty()) {
            return userList.get(0);
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
    public AirlineUsers mailChecker(AirlineUsers user){
    
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Query query = ss.createQuery("FROM Users where mail =:email");
            query.setParameter("email", user.getMail());
            List<AirlineUsers> mailList=query.list();
            ss.close();
            if(mailList!=null){
                return mailList.get(0);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
