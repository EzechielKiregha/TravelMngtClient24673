
package com.mirrorcompany.swing_designs;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Icon;

/**
 *
 * @author ekire
 */
public class TableCell_Image1 extends javax.swing.JPanel {

 
    public TableCell_Image1(Icon icon) {
        initComponents();
        setOpaque(false);
        pic.setIcon(icon);
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pic = new com.mirrorcompany.swing_designs.ImageAvatar();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(250, 250, 250));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 6, getWidth(), getHeight() - 12);
        super.paintComponent(g); 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mirrorcompany.swing_designs.ImageAvatar pic;
    // End of variables declaration//GEN-END:variables
}
