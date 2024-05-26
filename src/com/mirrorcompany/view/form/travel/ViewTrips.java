package com.mirrorcompany.view.form.travel;

import com.mirrorcompany.view.form.profile.*;
import static com.mchange.v2.c3p0.impl.C3P0Defaults.user;
import com.mirrorcompany.dao.UserDao;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ekire
 */
public class ViewTrips extends javax.swing.JPanel {
    private int userID ;
    private static String userEmail ;
    private static String userName;
    private static String role;
    private static String status;
    private String meterNum;
    private Map<Integer, BigDecimal> BillsCountAndAmountPaid = new HashMap<>();
    private ActionListener profileE;

    public void setSubE(ActionListener subE) {
        this.profileE = subE;
    }

    UserDao userDao = new UserDao();
    private String month = "";
    
    public ViewTrips() {
        initComponents();
        setOpaque(false);
        setFocusCycleRoot(true);
        super.setVisible(true);
        setFocusable(true);
        addMouseListener(new MouseAdapter() {
            
        });
        
        initEnergyTables();
        
//        user = userDao.SearchUserByID(userID);
//
//        this.userID = user.getUserID();
//        this.userEmail = user.getEmail();
//        this.userName = user.getUsername();
//        this.role = user.getRole();
//        this.status = user.getStatus();
    }
    
    public void initEnergyTables(){
        //  Test Data table
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        
        table1.fixTable(jScrollPane2);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new com.mirrorcompany.swing_designs.PanelRound();
        btnDisplayMore = new com.mirrorcompany.swing_designs.Button();
        btnDeleteItinerary = new com.mirrorcompany.swing_designs.Button();
        jLabel3 = new javax.swing.JLabel();
        itineraryTitle = new com.mirrorcompany.swing_designs.MyTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new com.mirrorcompany.swing_designs.Table();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1050, 695));

        panelRound1.setBackground(new java.awt.Color(212, 226, 240));

        btnDisplayMore.setBackground(new java.awt.Color(6, 7, 29));
        btnDisplayMore.setForeground(new java.awt.Color(255, 255, 255));
        btnDisplayMore.setText("DISPLAY MORE ");
        btnDisplayMore.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btnDeleteItinerary.setBackground(new java.awt.Color(204, 0, 0));
        btnDeleteItinerary.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteItinerary.setText("DELETE");
        btnDeleteItinerary.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel3.setText("Itinerary Title :");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Trip ID", "Description", "Start Date", "End Date", "Accomodation", "Transportation", "Activities", "Itinerary ID"
            }
        ));
        jScrollPane2.setViewportView(table1);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btnDisplayMore, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(480, 480, 480)
                        .addComponent(btnDeleteItinerary, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(itineraryTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itineraryTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 400, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDisplayMore, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteItinerary, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound1Layout.createSequentialGroup()
                    .addGap(94, 94, 94)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(100, Short.MAX_VALUE)))
        );

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("VIEWS ALL TRIP SEGMENTS FOR THIS ITINERARY");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
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
    public com.mirrorcompany.swing_designs.Button btnDeleteItinerary;
    public com.mirrorcompany.swing_designs.Button btnDisplayMore;
    private com.mirrorcompany.swing_designs.MyTextField itineraryTitle;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private com.mirrorcompany.swing_designs.PanelRound panelRound1;
    public com.mirrorcompany.swing_designs.Table table1;
    // End of variables declaration//GEN-END:variables
}
