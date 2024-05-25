package com.mirrorcompany.view.swing;

import com.mirrorcompany.component.*;
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
import javax.swing.JFrame;

/**
 *
 * @author ekire
 */
public class ThirdMenu extends javax.swing.JPanel {
 
    private EventMenuSelected event;
    
    public void addEventMenuSelected(EventMenuSelected event){
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }
    
    public ThirdMenu() {
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
    
    public void setLogo(String name, String imgName, String extension){
        nameMenu.setText(name);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/photos/"+imgName+"."+extension)));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listMenu1 = new com.mirrorcompany.swing_designs.ListMenu<>();
        panelMoving = new javax.swing.JPanel();
        logo = new com.mirrorcompany.view.swing.ImageAvatar();
        nameMenu = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        listMenu1.setForeground(new java.awt.Color(255, 255, 255));

        panelMoving.setOpaque(false);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/photos/TRAV.jpg"))); // NOI18N

        nameMenu.setBackground(new java.awt.Color(6, 7, 29));
        nameMenu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        nameMenu.setForeground(new java.awt.Color(204, 204, 204));
        nameMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameMenu.setText("Travel Management System");

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/Rwanda_Energy_Group_logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(listMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#34B48D"), 0, getHeight(), Color.decode("#0B5F56"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
    }

    private int x;
    private int y;
    
    public void initMoving(JFrame fram){
        panelMoving.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }   
        });
        panelMoving.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    public com.mirrorcompany.swing_designs.ListMenu<String> listMenu1;
    private com.mirrorcompany.view.swing.ImageAvatar logo;
    private javax.swing.JLabel nameMenu;
    private javax.swing.JPanel panelMoving;
    // End of variables declaration//GEN-END:variables
}
