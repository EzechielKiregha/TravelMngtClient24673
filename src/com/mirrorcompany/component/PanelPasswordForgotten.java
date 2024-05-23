package com.mirrorcompany.component;

import com.mirrorcompany.dao.UserDao;
import com.mirrorcompany.view.Main;
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
public class PanelPasswordForgotten extends javax.swing.JPanel {
    
    UserDao userdao = new UserDao();
    private String email = "no email";
    private String pwd = "no password";
    private String pwd_conf = "not exits";

    public String getPwd_conf() {
        return pwd_conf;
    }

    public void setPwd_conf(String pwd_conf) {
        this.pwd_conf = pwd_conf;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public PanelPasswordForgotten() {
        initComponents();
        setOpaque(false);
        setFocusCycleRoot(true);
        super.setVisible(false);
        addMouseListener(new MouseAdapter() {
        });
        txtPasswordConfirm.setVisible(false);
        btnSubmit.setVisible(false);
    }

    @Override
    public void setVisible(boolean bln) {
        super.setVisible(bln);
        if (bln){
            txtPasswordOrEmail.grabFocus();
            txtPasswordOrEmail.setText("");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new com.mirrorcompany.swing_designs.PanelRound();
        txtPasswordOrEmail = new com.mirrorcompany.swing_designs.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        text = new javax.swing.JLabel();
        btnCancel = new com.mirrorcompany.swing_designs.ButtonOutLine();
        btnOK = new com.mirrorcompany.swing_designs.ButtonOutLine();
        txtPasswordConfirm = new com.mirrorcompany.swing_designs.MyTextField();
        btnSubmit = new com.mirrorcompany.swing_designs.ButtonOutLine();

        txtPasswordOrEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPasswordOrEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordOrEmailActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Change Your Password");

        text.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        text.setForeground(new java.awt.Color(63, 63, 63));
        text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text.setText("Enter You Email ");

        btnCancel.setBackground(new java.awt.Color(203, 23, 29));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnOK.setBackground(new java.awt.Color(28, 68, 189));
        btnOK.setText("Check email");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        txtPasswordConfirm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPasswordConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordConfirmActionPerformed(evt);
            }
        });

        btnSubmit.setBackground(new java.awt.Color(28, 68, 189));
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPasswordOrEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound1Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addContainerGap(100, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPasswordOrEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordOrEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordOrEmailActionPerformed
        
    }//GEN-LAST:event_txtPasswordOrEmailActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtPasswordConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordConfirmActionPerformed
        
    }//GEN-LAST:event_txtPasswordConfirmActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        setPwd(txtPasswordOrEmail.getText().trim());
        setPwd_conf(txtPasswordConfirm.getText().trim());
        if (pwd.equals(pwd_conf)){
            text.setForeground(new Color(59, 184, 1));
            text.setText("Password Much Perfectly");
            Object[] newPwd = getInputPassword();
            String email = (String) newPwd[0];
            String password = (String) newPwd[1];
            System.out.println("email: "+email+"\npwd: "+password);
//            if(userdao.changerUserForgottenPwd(email, password)){
//                setVisible(false);
//            }
         } else {
            text.setForeground(new Color(205, 8, 8));
            text.setText("Password Do Not Much");
         }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (setThemBackandGetNewPassword()){
            
        }
         
    }//GEN-LAST:event_btnOKActionPerformed
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setColor(new Color(50, 50, 50));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        
        super.paintComponent(grphcs);
    }
    
    public boolean setThemBackandGetNewPassword(){
//        if (!userdao.checkUserByEmail(txtPasswordOrEmail.getText().trim())){
//            text.setForeground(new Color(205, 8, 8));
//            text.setText("User With This Email doesn't Exist");
//            btnCancel.setVisible(true); // display the Cancel buttom if the btnOk buttom is clicked
//            btnOK.setVisible(true); // display the check email buttun if the email exit
//            return false;
//        }
        text.setForeground(new Color(59, 184, 1));
        text.setText("Password & Confirm Password");
        btnCancel.setVisible(false); // Remove the Cancel buttom if the btnOk buttom is clicked
        btnOK.setVisible(false); // remove itself too
        txtPasswordConfirm.setVisible(true);
        txtPasswordConfirm.grabFocus();
        txtPasswordConfirm.setText("");
        setEmail(txtPasswordOrEmail.getText().trim());
        txtPasswordOrEmail.grabFocus();
        txtPasswordOrEmail.setText("");
        btnSubmit.setVisible(true); // show the submit button
        return true;
    }
    
    public Object[] getInputPassword(){
        return new Object[]{getEmail(), getPwd()};
    }
    
     public void addEventButtonOK(ActionListener event){
         btnSubmit.addActionListener(event);
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mirrorcompany.swing_designs.ButtonOutLine btnCancel;
    private com.mirrorcompany.swing_designs.ButtonOutLine btnOK;
    private com.mirrorcompany.swing_designs.ButtonOutLine btnSubmit;
    private javax.swing.JLabel jLabel1;
    private com.mirrorcompany.swing_designs.PanelRound panelRound1;
    private javax.swing.JLabel text;
    private com.mirrorcompany.swing_designs.MyTextField txtPasswordConfirm;
    private com.mirrorcompany.swing_designs.MyTextField txtPasswordOrEmail;
    // End of variables declaration//GEN-END:variables
}
