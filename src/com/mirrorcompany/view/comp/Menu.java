package com.mirrorcompany.view.comp;

import com.mirrorcompany.events.EventMenuSelected;
import com.mirrorcompany.model.MenuModel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Path2D;
import javax.swing.JFrame;

/**
 *
 * @author ekire
 */
public class Menu extends javax.swing.JPanel {
 
    private EventMenuSelected event;
    
    public void addEventMenuSelected(EventMenuSelected event){
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }
    
    public Menu() {
        initComponents();
        setOpaque(false);
        listMenu1.setOpaque(false);
        init();
    }
    
    private void init(){
        listMenu1.addItem(new MenuModel("1", "Dashboard", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("3", "Energy Usage", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("", " ", MenuModel.MenuType.EMPTY));
        listMenu1.addItem(new MenuModel("2", "Bills", MenuModel.MenuType.MENU));
//        listMenu1.addItem(new MenuModel("", "About System", MenuModel.MenuType.TITLE));
        listMenu1.addItem(new MenuModel("8", "My Profile", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("", " ", MenuModel.MenuType.EMPTY));
        listMenu1.addItem(new MenuModel("5", "Reports", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("6", "Help", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("7", "Logout", MenuModel.MenuType.MENU));
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listMenu1 = new com.mirrorcompany.swing_designs.ListMenu<>();
        profile1 = new com.mirrorcompany.view.comp.Profile();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#2B73E8"), 0, getHeight(), Color.decode("#000000"));
        int height = 140;
        Path2D.Float f = new Path2D.Float();
        f.moveTo(0, 0);
        f.curveTo(0, 0, 0, 70, 100, 70);
        f.curveTo(100, 70, getWidth(), 70, getWidth(), height);
        f.lineTo(getWidth(), getHeight());
        f.lineTo(0, getHeight());
        g2.setColor(new Color(60, 60, 60));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setPaint(g);
        g2.fill(f);
        super.paintChildren(grphcs);
    }

    private int x;
    private int y;
    
    public void initMoving(JFrame fram){
        profile1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }   
        });
        profile1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mirrorcompany.swing_designs.ListMenu<String> listMenu1;
    private com.mirrorcompany.view.comp.Profile profile1;
    // End of variables declaration//GEN-END:variables
}
