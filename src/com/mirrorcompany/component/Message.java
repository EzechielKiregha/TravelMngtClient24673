package com.mirrorcompany.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

/**
 *
 * @author ekire
 */
public class Message extends javax.swing.JPanel {
    
    private MessageType messageType = MessageType.SUCCESS;
    private boolean show;
    
    public boolean isShow(){
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
    
    public Message() {
        initComponents();
        setOpaque(false);
        setVisible(false);
    }
    
    public void showMessage(MessageType messageType, String message){
        this.messageType = messageType;
        txtMessage.setText(message);
        if(messageType == MessageType.SUCCESS){
            txtMessage.setIcon(new ImageIcon(getClass().getResource("/com/mirrorcompany/icons/success.png")));
        }else if (messageType == MessageType.CONNECTION_ERROR){
           txtMessage.setIcon(new ImageIcon(getClass().getResource("/com/mirrorcompany/icons/warning.png")));
        }else {
            txtMessage.setIcon(new ImageIcon(getClass().getResource("/com/mirrorcompany/icons/error.png")));
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMessage = new javax.swing.JLabel();

        txtMessage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMessage.setForeground(new java.awt.Color(255, 255, 255));
        txtMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMessage.setText("Message");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    public static enum MessageType{
        SUCCESS, ERROR, CONNECTION_ERROR
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        if (messageType == MessageType.SUCCESS){
            g2.setColor(new Color(15, 174, 37));
        }else if (messageType == MessageType.CONNECTION_ERROR){
            g2.setColor(new Color(16, 91, 214));
        }else{
            g2.setColor(new Color(240, 52, 53));
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2.fillRect(0,0, getWidth(), getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        g2.setColor(new Color(245, 245,245));
        g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel txtMessage;
    // End of variables declaration//GEN-END:variables
}
