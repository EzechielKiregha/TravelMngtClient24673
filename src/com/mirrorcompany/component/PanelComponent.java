
package com.mirrorcompany.component;

import com.mirrorcompany.swing_designs.ButtonOutLine;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author ekire
 */
 public class PanelComponent extends javax.swing.JPanel {
    
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private ActionListener event;
    private MigLayout layout;
    private JLabel logo;
    private JLabel title;
    private JLabel description;
    private JLabel description1;
    private ButtonOutLine button;
    private boolean isLogin;

    public PanelComponent() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]5[]10[]10[]push");
        setLayout(layout);
        init();
    }
    
    private void init(){
        logo = new JLabel();
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/LogoSmart.png")));
        add(logo);
        
        title = new JLabel("Smart Home Energy");
        title.setFont(new Font("sansserif", 1, 30));
        title.setForeground(new Color(245, 245, 245));
        title.setFont(new Font("sansserif", 1, 32));
        add(title);
        
        description = new JLabel("To Have A Better Experience With Us Please");
        description.setForeground(new Color(245, 245, 245));
        description.setFont(new Font("sansserif", 1, 13));
        add(description);
        
        description1 = new JLabel("Login And Start Managing All Your Home Smart Devices");
        description1.setForeground(new Color(245, 245, 245));
        description1.setFont(new Font("sansserif", 1, 13));
        add(description1);
        
        button = new ButtonOutLine();
        button.setBackground(new Color(255,255,255));
        button.setForeground(new Color(255,255,255));
        button.setText("SIGN IN");
        button.setFont(new Font("sansserif", 1, 14));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.actionPerformed(ae);}
        });
        add(button, "w 60%, h 40");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra = new GradientPaint(0,0, new Color(52, 180, 141), 0, getHeight(), new Color(11, 95, 86));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }
    
    public void addEvent(ActionListener event){
        this.event = event;
    }
    
    public void registerLeft(double v){
        v= Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }
    
    public void registerRight(double v){
        v= Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -"+ v +"% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }
    
    public void loginLeft(double v){
        v= Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 "+ v +"% 0 "+ v +"%");
        layout.setComponentConstraints(description, "pad 0 "+ v +"% 0 "+ v +"%");
        layout.setComponentConstraints(description1, "pad 0 "+ v +"% 0 "+ v +"%");
    }
    
    public void loginRight(double v){
        v= Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 "+ v +"% 0 "+ v +"%");
        layout.setComponentConstraints(description, "pad 0 "+ v +"% 0 "+ v +"%");
        layout.setComponentConstraints(description1, "pad 0 "+ v +"% 0 "+ v +"%");
    }

    private void login(boolean login){
        if (this.isLogin != login) {
            if (login) {
                title.setText("hello, Friend!");
                description.setText("Create An Account In Just A Second");
                description1.setText("And Start Journey With Us");
                button.setText("SIGN UP");
            } else {
                title.setText("Smart Home Energy");
                description.setText("To Have A Better Experience With Us Please");
                description1.setText("login With Your Personal Info");
                button.setText("SIGN IN");
            }
            this.isLogin = login;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
