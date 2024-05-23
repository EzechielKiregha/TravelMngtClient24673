package com.mirrorcompany.pages;

/**
 *
 * @author ekire
 */
public class Form_Report extends javax.swing.JPanel {

    public Form_Report() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CashPower = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PowerPageReport = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        meterNumberReport = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        btnReniew = new com.mirrorcompany.swing_designs.Button();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 51, 51));

        CashPower.setBackground(new java.awt.Color(164, 196, 186));
        CashPower.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51), 5));

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CASHPOWER | Estimated kWh");

        PowerPageReport.setBackground(new java.awt.Color(0, 51, 51));
        PowerPageReport.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        PowerPageReport.setForeground(new java.awt.Color(255, 255, 255));
        PowerPageReport.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PowerPageReport.setText("---.--   kWh");
        PowerPageReport.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel3.setText("METER NUMBER :");

        meterNumberReport.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        meterNumberReport.setText("-------");

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
                .addComponent(meterNumberReport, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PowerPageReport, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReniew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        CashPowerLayout.setVerticalGroup(
            CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CashPowerLayout.createSequentialGroup()
                .addGroup(CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meterNumberReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReniew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PowerPageReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("REPORT PAGE NOT AVAILABLE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CashPower, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(CashPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 182, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CashPower;
    public static javax.swing.JLabel PowerPageReport;
    private com.mirrorcompany.swing_designs.Button btnReniew;
    public javax.swing.JLabel date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel meterNumberReport;
    // End of variables declaration//GEN-END:variables
}
