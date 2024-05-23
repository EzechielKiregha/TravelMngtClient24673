/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.flightbooking.dao;

import com.mirrorcompany.dao.HibernateUtil;
import com.mirrorcompany.flightbooking.model.AirPlane;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author drg
 */
public class PlaneDao {

    public PlaneDao() {
    }
    public AirPlane add_Plane(AirPlane plane){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            ss.save(plane);
            ss.beginTransaction().commit();
            return plane;
            
        } catch (Exception e) {
            e.printStackTrace();     
        }
        return null;
    }
    public AirPlane delete_Plane(AirPlane plane){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            ss.delete(plane);
            ss.beginTransaction().commit();
            return plane;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }   
    
    public AirPlane update_Plane(AirPlane plane){
            try {
                Session ss=HibernateUtil.getSessionFactory().openSession();
                ss.update(plane);
                ss.beginTransaction().commit();
                return plane;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
    
    public  List<AirPlane> plane(){
        try {
            Session ss= HibernateUtil.getSessionFactory().openSession();
            List <AirPlane> thePlane = ss.createQuery("SELECT thePlane from Plane thePlane").list();
            ss.close();
            return thePlane;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public AirPlane search_plane(AirPlane plane){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
     //       Plane theplane = (Plane) ss.get(Plane.class, plane.getNumber());
            List<AirPlane> pl =ss.createQuery("select pl from Plane pl where pl.number ='"+plane.getNumber()+"'").list();
            ss.close();
            if(pl!=null)
            {
                return pl.get(0);
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return null;
    }
}
