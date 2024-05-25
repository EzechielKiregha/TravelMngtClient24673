 
package com.mirrorcompany.swing_designs;

import com.mirrorcompany.model.MenuModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author ekire
 */
public class MenuItem extends javax.swing.JPanel {

    private boolean selected;
    private boolean over;
    
    public MenuItem(MenuModel data) {
        initComponents();
        setOpaque(false);
        if (null == data.getType()){
            lbName.setText(" ");
        }else switch (data.getType()) {
            case MENU:
                lbIcon.setIcon(data.toIcon());
                lbName.setText(data.getName());
                break;
            case TITLE:
                lbIcon.setText(data.getName());
                lbIcon.setFont(new Font("sansserif", 1, 12));
                lbName.setVisible(false);
                break;
            default:
                lbName.setText(" ");
                break;
        }
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }
    
    public void setOver(boolean over){
        this.over = over;
        repaint();
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcon = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();

        lbIcon.setForeground(new java.awt.Color(255, 255, 255));

        lbName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbName.setText("Menu Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 35, Short.MAX_VALUE)
                .addComponent(lbIcon)
                .addGap(18, 18, 18)
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (selected || over){
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (selected){
                g2.setColor(new Color(255,255,255, 80));
            } else {
                g2.setColor(new Color(255,255,255, 80));
            }
            g2.fillRoundRect(10, 0, getWidth() - 20, getHeight(), 5, 5);
        }
        super.paintComponent(grphcs);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}
