/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.flightbooking.dao;

import com.mirrorcompany.dao.HibernateUtil;
import com.mirrorcompany.flightbooking.model.Flight;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author drg
 */
public class FlightDao {

    public FlightDao() {
    }
    public Flight add_Flight(Flight flight){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            Transaction tr =ss.beginTransaction();
            ss.save(flight);
            tr.commit();
            
            return flight;
            
        } catch (Exception e) {
            e.printStackTrace();     
        }
        return null;
    }
    public Flight delete_Flight(Flight flight){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            ss.delete(flight);
            ss.beginTransaction().commit();
            return flight;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }   
    
    public Flight update_Flight(Flight flight){
            try {
                Session ss=HibernateUtil.getSessionFactory().openSession();
                ss.update(flight);
                ss.beginTransaction().commit();
                ss.close();
                return flight;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
    
    public  List<Flight> flight(){
        try {
            Session ss= HibernateUtil.getSessionFactory().openSession();
            List <Flight> theFlight = ss.createQuery("SELECT theFlight from Flight theFlight").list();
            ss.close();
            return theFlight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Flight search_flight(Flight flight){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
        //    Flight p = (Flight) ss.get(Flight.class, flight.getFlightNo());
          List<Flight> fl =ss.createQuery("select fl from Flight fl where fl.flightNo ='"+flight.getFlightNo()+"'").list();
            ss.close();
            if(fl!=null)
            {
            
            return fl.get(0);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return null;
    }
}
