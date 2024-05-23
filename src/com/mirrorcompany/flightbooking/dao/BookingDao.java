/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.flightbooking.dao;

import com.mirrorcompany.dao.HibernateUtil;
import com.mirrorcompany.flightbooking.model.AirlineBooking;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author drg
 */
public class BookingDao {

    public BookingDao() {
    }
    public AirlineBooking add_Booking(AirlineBooking booking){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            ss.save(booking);
            ss.beginTransaction().commit();
            return booking;
            
        } catch (Exception e) {
            e.printStackTrace();     
        }
        return null;
    }
    public AirlineBooking delete_Booking(AirlineBooking booking){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            ss.delete(booking);
            ss.beginTransaction().commit();
            return booking;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }   
    
    public AirlineBooking update_Booking(AirlineBooking booking){
            try {
                Session ss=HibernateUtil.getSessionFactory().openSession();
                ss.update(booking);
                ss.beginTransaction().commit();
                return booking;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
    
    public  List<AirlineBooking> booking(){
        try {
            Session ss= HibernateUtil.getSessionFactory().openSession();
            List <AirlineBooking> theBooking = ss.createQuery("SELECT theBooking from Booking theBooking").list();
            ss.close();
            return theBooking;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public AirlineBooking search_booking(AirlineBooking booking){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
       //   Booking thebooking = (Booking) ss.get(Booking.class, booking.getBooking_no());
            List<AirlineBooking> b =ss.createQuery("select b from Booking b where b.booking_no ='"+booking.getBooking_no()+"'").list();
            ss.close();
            if(b!=null)
            {
                return b.get(0);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return null;
    }
}
