package com.mirrorcompany.view.form.travel;

import com.mirrorcompany.model.User;
import com.mirrorcompany.view.swing.Button;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;

public class TMS extends javax.swing.JPanel {
    private static MigLayout layout;
    private static NewItineraryPopUp itinerary;
    private static ViewItineraries viewItinerary;
    private static TmsExpenses expenses;
    private static AddTripSegment tripSegment;
    private static Recommend recommendation;
    
    private static final String RMI_SERVER_ITINERARY = "ItineraryService";
    
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public TMS() {
        initComponents();
        
        layout = new MigLayout("fill", "0[fill]0", "0[fill]0");
        
        // START CODE REGARDING TMS TAB
        
        itinerary = new NewItineraryPopUp();
        viewItinerary = new ViewItineraries();
        expenses = new TmsExpenses();
        tripSegment = new AddTripSegment();
        recommendation = new Recommend();
        // ENS CODE REGARDING TMS TAB
        
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header1 = new com.mirrorcompany.view.comp.Header();
        body = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(250, 250, 250));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1050, 720));

        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 685, Short.MAX_VALUE)
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
 
    public void addEventMenu(ActionListener event) {
        header1.addEventMenu(event);
    }
    public Button getBtn(){
        return header1.getBtn();
    }

    public void initMoving(JFrame fram) {
        header1.initMoving(fram);
        show(new tmsForm_1());
    }

    public static void showPopUp(Component com) {
        if (com != null) {
            body.setLayout(layout);
            body.setLayer(com, JLayeredPane.POPUP_LAYER);
            body.add(com, "pos 0 0 100% 100%");
            body.repaint();
            body.revalidate();
        }
    }
    public static void removeComponentIfExists(Component component) {
        if (component.getParent() != null) {
            body.remove(component);
        }
    }
    
    public void show(Component com){
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLayeredPane body;
    private com.mirrorcompany.view.comp.Header header1;
    // End of variables declaration//GEN-END:variables
}
