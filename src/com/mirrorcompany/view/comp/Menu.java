package com.mirrorcompany.view.comp;

import com.mirrorcompany.events.EventMenuSelected;
import com.mirrorcompany.model.MenuModel;
import com.mirrorcompany.swing_designs.ListMenu;
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
    private ListMenu listMenu1;
    private ListMenu listMenu2;
    private ListMenu listMenu3;
    private ListMenu listMenu4;
    
    public void addEventMenuSelected(EventMenuSelected event){
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }
    
    public Menu() {
        initComponents();
        setOpaque(false);
        listMenu1 = new ListMenu();
        listMenu2 = new ListMenu();
        listMenu3 = new ListMenu();
        listMenu4 = new ListMenu();
        listMenu1.setOpaque(false);
        listMenu2.setOpaque(false);
        listMenu3.setOpaque(false);
        listMenu4.setOpaque(false);
        
        initTMSMenuItems();
        initBookingMenuItems();
        initCommunityMenuItems();
        initProfileMenuItems();
        
    }
    
    
    public void displayMenu(ListMenu list){
        menuContainer.removeAll();
        menuContainer.add(list);
        menuContainer.repaint();
        menuContainer.revalidate();
    }
    
    
    private void initTMSMenuItems() {
        listMenu1.addItem(new MenuModel("1", "Dashboard", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("2", "UI Elements", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("3", "Comonents", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("4", "Forms Stuff", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("5", "Date Table", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("6", "Icons", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("7", "Sample Page", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("8", "Extra", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("9", "More", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("10", "Logout", MenuModel.MenuType.MENU));
        listMenu1.addItem(new MenuModel("", "", MenuModel.MenuType.EMPTY));
    }
    private void initBookingMenuItems() {
        listMenu2.addItem(new MenuModel("4", "", MenuModel.MenuType.TITLE));
        listMenu2.addItem(new MenuModel("1", "Book A Flight", MenuModel.MenuType.MENU));
        listMenu2.addItem(new MenuModel("", " ", MenuModel.MenuType.EMPTY));
        listMenu2.addItem(new MenuModel("2", "View All", MenuModel.MenuType.MENU));
        listMenu2.addItem(new MenuModel("8", "Awaits", MenuModel.MenuType.MENU));
        listMenu2.addItem(new MenuModel("", " ", MenuModel.MenuType.EMPTY));
        listMenu2.addItem(new MenuModel("5", "Airline News", MenuModel.MenuType.MENU));
    }
    private void initCommunityMenuItems() {
        listMenu3.addItem(new MenuModel("4", "", MenuModel.MenuType.TITLE));
        listMenu3.addItem(new MenuModel("1", "myFeed", MenuModel.MenuType.MENU));
        listMenu3.addItem(new MenuModel("", " ", MenuModel.MenuType.EMPTY));
        listMenu3.addItem(new MenuModel("2", "myChats", MenuModel.MenuType.MENU));
        listMenu3.addItem(new MenuModel("8", "myGroups", MenuModel.MenuType.MENU));
        listMenu3.addItem(new MenuModel("", " ", MenuModel.MenuType.EMPTY));
        listMenu3.addItem(new MenuModel("5", "myComments", MenuModel.MenuType.MENU));
    }
    private void initProfileMenuItems() {
        listMenu4.addItem(new MenuModel("4", "", MenuModel.MenuType.TITLE));
        listMenu4.addItem(new MenuModel("1", "myProfile", MenuModel.MenuType.MENU));
        listMenu4.addItem(new MenuModel("", " ", MenuModel.MenuType.EMPTY));
        listMenu4.addItem(new MenuModel("2", "Customize", MenuModel.MenuType.MENU));
        listMenu4.addItem(new MenuModel("8", "Setting", MenuModel.MenuType.MENU));
        listMenu4.addItem(new MenuModel("", " ", MenuModel.MenuType.EMPTY));
        listMenu4.addItem(new MenuModel("5", "Seek Support", MenuModel.MenuType.MENU));
    }
    public void setLogo(int menuNumber,String name, String imgName, String extension){
        
        if(menuNumber == 1){
            displayMenu(listMenu1);
        } else if (menuNumber == 2){
            displayMenu(listMenu2);
        } else if (menuNumber == 3){
            displayMenu(listMenu3);
        } else if (menuNumber == 4){
            displayMenu(listMenu4);
        }
        
        nameMenu.setText(name);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/photos/"+imgName+"."+extension)));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logo = new com.mirrorcompany.view.swing.ImageAvatar();
        nameMenu = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        menuContainer = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(270, 720));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/photos/TRAV.jpg"))); // NOI18N

        nameMenu.setBackground(new java.awt.Color(6, 7, 29));
        nameMenu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        nameMenu.setForeground(new java.awt.Color(204, 204, 204));
        nameMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameMenu.setText("Travel Management System");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/photos/sponsor .png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("powered by");

        menuContainer.setOpaque(false);
        menuContainer.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 8, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addComponent(logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(menuContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#2B73E8"), 0, getHeight(), new Color(6,7,29));
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
        logo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }   
        });
        logo.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.mirrorcompany.view.swing.ImageAvatar logo;
    private javax.swing.JPanel menuContainer;
    private javax.swing.JLabel nameMenu;
    // End of variables declaration//GEN-END:variables
}
