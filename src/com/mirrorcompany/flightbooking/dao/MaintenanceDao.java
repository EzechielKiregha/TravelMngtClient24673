/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.flightbooking.dao;

import com.mirrorcompany.dao.HibernateUtil;
import com.mirrorcompany.flightbooking.model.AirlineMaintenance;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author drg
 */
public class MaintenanceDao {

    public MaintenanceDao() {
    }
    public AirlineMaintenance add_Maintenance(AirlineMaintenance maintenance){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            ss.save(maintenance);
            ss.beginTransaction().commit();
            return maintenance;
            
        } catch (Exception e) {
            e.printStackTrace();     
        }
        return null;
    }
    public AirlineMaintenance delete_Maintenance(AirlineMaintenance maintenance){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
            ss.delete(maintenance);
            ss.beginTransaction().commit();
            return maintenance;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }   
    
    public AirlineMaintenance update_Maintenance(AirlineMaintenance maintenance){
            try {
                Session ss=HibernateUtil.getSessionFactory().openSession();
                ss.update(maintenance);
                ss.beginTransaction().commit();
                return maintenance;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
    
    public  List<AirlineMaintenance> maintenance(){
        try {
            Session ss= HibernateUtil.getSessionFactory().openSession();
            List <AirlineMaintenance> theMaintenance = ss.createQuery("SELECT theMaintenance from Maintenance theMaintenance").list();
            ss.close();
            return theMaintenance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public AirlineMaintenance search_maintenance(AirlineMaintenance maintenance){
        try {
            Session ss=HibernateUtil.getSessionFactory().openSession();
 //           Maintenance themaintenance = (Maintenance) ss.get(Maintenance.class, maintenance.getTrackingNo());
   List<AirlineMaintenance> m =ss.createQuery("select m from Maintenance m where m.trackingNo'"+maintenance.getTrackingNo()+"'").list();
            ss.close();
            if(m!=null)
            {
            
            return m.get(0);
            }
 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return null;
    }
}
