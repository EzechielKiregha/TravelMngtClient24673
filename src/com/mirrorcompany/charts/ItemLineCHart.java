 
package com.mirrorcompany.charts;
    
import com.mirrorcompany.model.LineChart;
import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;

/**
 *
 * @author ekire
 */
public class ItemLineCHart extends javax.swing.JPanel {
    
    public ItemLineCHart(LineChart data) {
        initComponents();
        setOpaque(false);
        DecimalFormat df = new DecimalFormat("$ #,##0.##");
        lbName.setText(data.getName());
        lbValue.setText(df.format(data.getValue()));
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        lbValue = new javax.swing.JLabel();

        lbName.setForeground(new java.awt.Color(50, 50, 50));
        lbName.setText("Name");

        lbValue.setForeground(new java.awt.Color(50, 50, 50));
        lbValue.setText("Value");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(lbValue, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbValue, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
            .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(240, 240, 240));
        g.drawLine(0, getHeight() - 1, getWidth() , getHeight() - 1);
        
        super.paintComponent(g);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbValue;
    // End of variables declaration//GEN-END:variables
}
