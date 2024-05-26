package com.mirrorcompany.component;


import com.mirrorcompany.view.OverviewFrame;
import com.mirrorcompany.view.form.dash.Home;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 *
 * @author ekire
 */
public class LogOutPopUp extends javax.swing.JPanel {
    
    public LogOutPopUp() {
        initComponents();
        setOpaque(false);
        setFocusCycleRoot(true);
        super.setVisible(false);
        addMouseListener(new MouseAdapter() {
});
    }

    @Override
    public void setVisible(boolean bln) {
        super.setVisible(bln);
        if (bln){
//            txtCode.grabFocus();
//            txtCode.setText("");
        }
    }
    
    public void quitTheApp(ActionListener quit){
        QuitTheApp.addActionListener(quit);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new com.mirrorcompany.swing_designs.PanelRound();
        logoutMessage = new javax.swing.JLabel();
        QuitTheApp = new com.mirrorcompany.swing_designs.ButtonOutLine();
        LogMeIn = new com.mirrorcompany.swing_designs.ButtonOutLine();

        logoutMessage.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        logoutMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoutMessage.setText("You Are Logged Out");

        QuitTheApp.setBackground(new java.awt.Color(203, 23, 29));
        QuitTheApp.setText("QUIT");

        LogMeIn.setBackground(new java.awt.Color(28, 68, 189));
        LogMeIn.setText("LOG ME BACK IN");
        LogMeIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogMeInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(LogMeIn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(QuitTheApp, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(logoutMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(logoutMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LogMeIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QuitTheApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void LogMeInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogMeInActionPerformed
        setVisible(false);
        Home dash = new Home();
        OverviewFrame.toDash(dash);
    }//GEN-LAST:event_LogMeInActionPerformed
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setColor(new Color(50, 50, 50));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        
        super.paintComponent(grphcs);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.mirrorcompany.swing_designs.ButtonOutLine LogMeIn;
    public com.mirrorcompany.swing_designs.ButtonOutLine QuitTheApp;
    public javax.swing.JLabel logoutMessage;
    private com.mirrorcompany.swing_designs.PanelRound panelRound1;
    // End of variables declaration//GEN-END:variables
}
