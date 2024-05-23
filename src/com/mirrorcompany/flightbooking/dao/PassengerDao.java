/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.flightbooking.dao;

import com.mirrorcompany.dao.HibernateUtil;
import com.mirrorcompany.flightbooking.model.AirlinePassenger;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author drg
 */
public class PassengerDao {

    public PassengerDao() {
    }
    public AirlinePassenger add_Passenger(AirlinePassenger passenger){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            ss.save(passenger);
            ss.beginTransaction().commit();
            return passenger;
            
        } catch (Exception e) {
            e.printStackTrace();     
        }
        return null;
    }
    public AirlinePassenger delete_Passenger(AirlinePassenger passenger){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            ss.delete(passenger);
            ss.beginTransaction().commit();
            return passenger;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }   
    
    public AirlinePassenger update_Passenger(AirlinePassenger passenger){
            try {
                Session ss=HibernateUtil.getSessionFactory().openSession();
                ss.update(passenger);
                ss.beginTransaction().commit();
                return passenger;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
    
    public  List<AirlinePassenger> passenger(){
        try {
            Session ss= HibernateUtil.getSessionFactory().openSession();
            List <AirlinePassenger> thePassenger = ss.createQuery("SELECT thePassenger from Passenger thePassenger").list();
            ss.close();
            return thePassenger;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public AirlinePassenger search_passenger(AirlinePassenger passenger){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
//          Passenger passenger1 = (Passenger) ss.get(Passenger.class, passenger.getPhone());
            List<AirlinePassenger> p =ss.createQuery("select p from Passenger p where p.phone ='"+passenger.getPhone()+"'").list();
            ss.close();
            if(p!=null)
            {
            
            return p.get(0);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return null;
    }
}
