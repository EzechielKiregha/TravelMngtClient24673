package com.mirrorcompany.view.form.travel;

import com.mirrorcompany.view.swing.Button;
import com.mirrorcompany.view.form.*;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class TMS extends javax.swing.JPanel {

    public TMS() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header1 = new com.mirrorcompany.view.comp.Header();
        body = new javax.swing.JPanel();

        setBackground(new java.awt.Color(250, 250, 250));
        setPreferredSize(new java.awt.Dimension(1050, 720));

        body.setOpaque(false);
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addEventMenu(ActionListener event) {
        header1.addEventMenu(event);
    }
    public Button getBtn(){
        return header1.getBtn();
    }

    public void initMoving(JFrame fram) {
        header1.initMoving(fram);
        show(new tmsForm_1());
    }

    
    public void show(Component com){
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private com.mirrorcompany.view.comp.Header header1;
    // End of variables declaration//GEN-END:variables
}
