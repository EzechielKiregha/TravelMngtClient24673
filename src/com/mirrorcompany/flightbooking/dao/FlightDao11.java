/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.flightbooking.dao;

import com.mirrorcompany.dao.HibernateUtil;
import com.mirrorcompany.flightbooking.model.Flight1;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author drg
 */
public class FlightDao11 {
    
    public Flight1 saveFlight1(Flight1 flight){
        
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            ss.save(flight);
            ss.beginTransaction().commit();
            ss.close();
            return flight;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    return flight;
    }
    public Flight1 deletePassenger(Flight1 flight){
        try {
            Session ss =HibernateUtil.getSessionFactory().openSession();
            ss.delete(flight);
            ss.beginTransaction().commit();
            ss.close();
            return flight;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    return null;
    }
    
    public Flight1 updatePassenger(Flight1 flight){
    
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.update(flight);
            ss.beginTransaction().commit();
            ss.close();
    return flight;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public List<Flight1>getAllflight(){
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            List <Flight1> flight =ss.createQuery("select flight from Flight1 flight").list();
            ss.close();
            return flight;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Flight1 search(Flight1 flight){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
          //  Flight1 theflight = (Flight1)ss.get(Flight1.class, flight.getFlightNo());
          List<Flight1> fl=ss.createQuery("select fl from Flight1 fl where fl.FlightNo ='"+flight.getFlightNo()+"'").list();
          ss.close();
          if(fl!=null){
              return fl.get(0);
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    return null;
    }
    
}
