package com.mirrorcompany.view.comp;


import com.mirrorcompany.swing_designs.shadow.ShadowRenderer;
import com.mirrorcompany.view.swing.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Header extends javax.swing.JPanel {

    public Header() {
        initComponents();
        setBackground(new Color(6, 7, 29));
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdMenu = new com.mirrorcompany.view.swing.Button();

        setBackground(new java.awt.Color(6, 7, 29));

        cmdMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/menu.png"))); // NOI18N
        cmdMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdMenuMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(cmdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(750, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cmdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdMenuMouseEntered
        cmdMenu.setBackground(new Color(54, 81, 207));
    }//GEN-LAST:event_cmdMenuMouseEntered

    private void cmdMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdMenuMouseExited
        cmdMenu.setBackground(new Color(250, 250, 250));
    }//GEN-LAST:event_cmdMenuMouseExited

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        int width = getWidth();
        int height = getHeight() - 6;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        grphcs.drawImage(new ShadowRenderer(3, 0.3f, Color.GRAY).createShadow(img), -3, 0, null);
        grphcs.drawImage(img, 0, 0, null);
    }

    private int x;
    private int y;

    public void initMoving(JFrame fram) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }

        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - (x + 80), me.getYOnScreen() - y);
            }
        });
    }

    public void addEventMenu(ActionListener event) {
        cmdMenu.addActionListener(event);
    }
    public Button getBtn(){
        return cmdMenu;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mirrorcompany.view.swing.Button cmdMenu;
    // End of variables declaration//GEN-END:variables
}
