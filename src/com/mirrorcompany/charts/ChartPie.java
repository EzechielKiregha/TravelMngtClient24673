package com.mirrorcompany.charts;

import com.mirrorcompany.model.PieChart;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

/**
 *
 * @author ekire
 */
public class ChartPie extends javax.swing.JPanel {
    
    private List<PieChart> model;

    public List<PieChart> getModel() {
        return model;
    }
    
//    private PanelPieChart panelPieChart;

    public void setModel(List<PieChart> model) {
        this.model = model;
        initEnergyData();
    }
 
    public ChartPie() {
        initComponents();
        setOpaque(false);
        setBackground(Color.WHITE);
    }
 
    private void initEnergyData(){
       panelPieChart1 = new PanelPieChart();
        
        panelPieChart1.removeAllItem();
        if (model != null){
            for (PieChart data : model){
                panelPieChart1.addItem(data);
                EnergyConsumptionData.add(new ItemPieChart(data));
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

        EnergyConsumptionData = new javax.swing.JPanel();
        panelPieChart1 = new com.mirrorcompany.charts.PanelPieChart();

        EnergyConsumptionData.setOpaque(false);
        EnergyConsumptionData.setLayout(new javax.swing.BoxLayout(EnergyConsumptionData, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout panelPieChart1Layout = new javax.swing.GroupLayout(panelPieChart1);
        panelPieChart1.setLayout(panelPieChart1Layout);
        panelPieChart1Layout.setHorizontalGroup(
            panelPieChart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 169, Short.MAX_VALUE)
        );
        panelPieChart1Layout.setVerticalGroup(
            panelPieChart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EnergyConsumptionData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EnergyConsumptionData, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
            .addComponent(panelPieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EnergyConsumptionData;
    private com.mirrorcompany.charts.PanelPieChart panelPieChart1;
    // End of variables declaration//GEN-END:variables
}
