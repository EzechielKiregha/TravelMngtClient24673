/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.pages;

import static com.mirrorcompany.pages.Dash.trend;

/**
 *
 * @author ekire
 */
public class Form_Profile extends javax.swing.JPanel {

    public Form_Profile() {
        initComponents();
    }
    
    public void init(String meterNo){
        meterNumberProfile.setText(meterNo);
        PowerPageProfile.setText(trend+"    kWh");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CashPower = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PowerPageProfile = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        meterNumberProfile = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        btnReniew = new com.mirrorcompany.swing_designs.Button();

        setBackground(new java.awt.Color(0, 51, 51));

        CashPower.setBackground(new java.awt.Color(164, 196, 186));
        CashPower.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51), 5));

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CASHPOWER | Estimated kWh");

        PowerPageProfile.setBackground(new java.awt.Color(0, 51, 51));
        PowerPageProfile.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        PowerPageProfile.setForeground(new java.awt.Color(255, 255, 255));
        PowerPageProfile.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PowerPageProfile.setText("---.--   kWh");
        PowerPageProfile.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel3.setText("METER NUMBER :");

        meterNumberProfile.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        meterNumberProfile.setText("-------");

        date.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setText("-----/---/---");

        btnReniew.setBackground(new java.awt.Color(0, 51, 51));
        btnReniew.setForeground(java.awt.Color.white);
        btnReniew.setText("BUY  |  RENEW YOUR POWER");
        btnReniew.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout CashPowerLayout = new javax.swing.GroupLayout(CashPower);
        CashPower.setLayout(CashPowerLayout);
        CashPowerLayout.setHorizontalGroup(
            CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CashPowerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(meterNumberProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PowerPageProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReniew, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addContainerGap())
        );
        CashPowerLayout.setVerticalGroup(
            CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CashPowerLayout.createSequentialGroup()
                .addGroup(CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meterNumberProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReniew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PowerPageProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CashPower, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(CashPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 589, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CashPower;
    public static javax.swing.JLabel PowerPageProfile;
    private com.mirrorcompany.swing_designs.Button btnReniew;
    public javax.swing.JLabel date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel meterNumberProfile;
    // End of variables declaration//GEN-END:variables
}
