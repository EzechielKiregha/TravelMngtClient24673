package com.mirrorcompany.charts;

import com.mirrorcompany.model.LineChart;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

/**
 *
 * @author ekire
 */
public class ChartLine extends javax.swing.JPanel {
    
    private List<LineChart> model;

    public List<LineChart> getModel() {
        return model;
    }

    public void setModel(List<LineChart> model) {
        this.model = model;
        initEnergyData();
    }
 
    public ChartLine() {
        initComponents();
        setOpaque(false);
        setBackground(Color.WHITE);
    }
 
    private void initEnergyData(){
        panelChartLine1.removeAllItem();
        if (model != null){
            for (LineChart data : model){
                panelChartLine1.addItem(data);
                panelData.add(new ItemLineCHart(data));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelData = new javax.swing.JPanel();
        panelChartLine1 = new com.mirrorcompany.charts.PanelChartLine();

        panelData.setOpaque(false);
        panelData.setLayout(new javax.swing.BoxLayout(panelData, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout panelChartLine1Layout = new javax.swing.GroupLayout(panelChartLine1);
        panelChartLine1.setLayout(panelChartLine1Layout);
        panelChartLine1Layout.setHorizontalGroup(
            panelChartLine1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );
        panelChartLine1Layout.setVerticalGroup(
            panelChartLine1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelChartLine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelData, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelData, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
            .addComponent(panelChartLine1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mirrorcompany.charts.PanelChartLine panelChartLine1;
    private javax.swing.JPanel panelData;
    // End of variables declaration//GEN-END:variables
}
