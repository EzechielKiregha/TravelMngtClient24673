package com.mirrorcompany.view.form.travel;

import com.mirrorcompany.model.Itinerary;
import com.mirrorcompany.model.User;
import com.mirrorcompany.service.ItineraryService;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ekire
 */
public class NewItineraryPopUp extends javax.swing.JPanel {
    private static final String RMI_SERVER_ITINERARY = "ItineraryService";

    AddTripSegment tripSegment;
    User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public NewItineraryPopUp() {
        initComponents();
        setOpaque(false);
        setFocusCycleRoot(true);
        super.setVisible(true);
        setFocusable(true);
        addMouseListener(new MouseAdapter() {
            
        });
        addTripToItinerary.setEnabled(false);
        
        try{
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            ItineraryService itineraryService = (ItineraryService) theRegistry.lookup(RMI_SERVER_ITINERARY);
        } catch (Exception e){
            e.printStackTrace();
        }
        
        
        
        
        initTripTsble();
        
    }
    
    public void initTripTsble(){
        //  Test Data table
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        
        table1.fixTable(jScrollPane2);
    }

    public void btnUpdateItineraryEvt(ActionListener evt){
        btnUpdateItinerary.addActionListener(evt);
    }
    public void btnSaveItineraryEvt(ActionListener evt){
        btnSaveItinerary.addActionListener(evt);
    }
    public void btnDeleteItineraryEvt(ActionListener evt){
        btnDeleteItinerary.addActionListener(evt);
    }
    public void addTripToItineraryEvt(ActionListener evt){
        addTripToItinerary.addActionListener(evt);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new com.mirrorcompany.swing_designs.PanelRound();
        btnUpdateItinerary = new com.mirrorcompany.swing_designs.Button();
        addTripToItinerary = new com.mirrorcompany.swing_designs.Button();
        btnDeleteItinerary = new com.mirrorcompany.swing_designs.Button();
        btnSaveItinerary = new com.mirrorcompany.swing_designs.Button();
        jLabel3 = new javax.swing.JLabel();
        itineraryTitle = new com.mirrorcompany.swing_designs.MyTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        startDate = new com.toedter.calendar.JDateChooser();
        noOfTrip = new com.mirrorcompany.swing_designs.MyTextField();
        user_ID = new com.mirrorcompany.swing_designs.MyTextField();
        endDate = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new com.mirrorcompany.swing_designs.Table();
        imageAvatar1 = new com.mirrorcompany.view.swing.ImageAvatar();
        closePopUp = new com.mirrorcompany.swing_designs.Button();
        jLabel9 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1050, 695));

        panelRound1.setBackground(new java.awt.Color(212, 226, 240));

        btnUpdateItinerary.setBackground(new java.awt.Color(6, 7, 29));
        btnUpdateItinerary.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateItinerary.setText("UPDATE");
        btnUpdateItinerary.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        addTripToItinerary.setBackground(new java.awt.Color(35, 126, 126));
        addTripToItinerary.setForeground(new java.awt.Color(255, 255, 255));
        addTripToItinerary.setText("VIEW TRIP SEGMENTS");
        addTripToItinerary.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btnDeleteItinerary.setBackground(new java.awt.Color(204, 0, 0));
        btnDeleteItinerary.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteItinerary.setText("DELETE");
        btnDeleteItinerary.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btnSaveItinerary.setBackground(new java.awt.Color(54, 81, 207));
        btnSaveItinerary.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveItinerary.setText("DRAFT & ADD TRIP TO YOUR ITINERARY");
        btnSaveItinerary.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSaveItinerary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveItineraryActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel3.setText("Title :");

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel4.setText("Start Date :");

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel5.setText("End Date :");

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel6.setText("Description :");

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel7.setText("User ID :");

        jLabel8.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel8.setText("Trip Segment :");

        startDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        endDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        description.setColumns(20);
        description.setRows(5);
        jScrollPane1.setViewportView(description);

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
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setPreferredWidth(5);
            table1.getColumnModel().getColumn(7).setPreferredWidth(5);
        }

        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/photos/TRAV.jpg"))); // NOI18N

        closePopUp.setBackground(new java.awt.Color(204, 0, 0));
        closePopUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/error.png"))); // NOI18N
        closePopUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closePopUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(endDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addComponent(startDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addComponent(user_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noOfTrip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itineraryTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addComponent(addTripToItinerary, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnUpdateItinerary, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnSaveItinerary, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(btnDeleteItinerary, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addContainerGap(999, Short.MAX_VALUE)
                        .addComponent(closePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(itineraryTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(startDate, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(user_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(closePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOfTrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addTripToItinerary, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnSaveItinerary, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateItinerary, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteItinerary, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel9.setBackground(new java.awt.Color(6, 7, 29));
        jLabel9.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("PLAN ALL STEPS OF YOUR JOURNEY     |     CREATE AN ITINERARY      |     IT'S TIME TO TRAVEL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void closePopUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closePopUpActionPerformed
        setVisible(false);
    }//GEN-LAST:event_closePopUpActionPerformed

    private void btnSaveItineraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveItineraryActionPerformed
        try{
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            ItineraryService itineraryService = (ItineraryService) theRegistry.lookup(RMI_SERVER_ITINERARY);
            Itinerary itineraryModel = new Itinerary();
            String title = itineraryTitle.getText().trim();
            Date date = startDate.getDate();
            java.sql.Date startdate = new java.sql.Date(date.getTime());
            Date utilDate = endDate.getDate();
            java.sql.Date enddate = new java.sql.Date(utilDate.getTime());
            String desc = description.getText();
            
            itineraryModel.setTitle(title);
            itineraryModel.setStartDate(startdate);
            itineraryModel.setEndDate(enddate);
            itineraryModel.setDescription(desc);
            itineraryModel.setUser(user);
            
            boolean result = itineraryService.addItinerary(itineraryModel);
            tripSegment = new AddTripSegment();
            if (result && btnSaveItinerary.getText().equals("DRAFT & ADD TRIP TO YOUR ITINERARY")){
                btnSaveItinerary.setText("SAVE ITINERARY");
                addTripToItinerary.setEnabled(true);
            }
            
            
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveItineraryActionPerformed
    
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
    public com.mirrorcompany.swing_designs.Button addTripToItinerary;
    public com.mirrorcompany.swing_designs.Button btnDeleteItinerary;
    public com.mirrorcompany.swing_designs.Button btnSaveItinerary;
    public com.mirrorcompany.swing_designs.Button btnUpdateItinerary;
    private com.mirrorcompany.swing_designs.Button closePopUp;
    public javax.swing.JTextArea description;
    public com.toedter.calendar.JDateChooser endDate;
    private com.mirrorcompany.view.swing.ImageAvatar imageAvatar1;
    public com.mirrorcompany.swing_designs.MyTextField itineraryTitle;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public com.mirrorcompany.swing_designs.MyTextField noOfTrip;
    private com.mirrorcompany.swing_designs.PanelRound panelRound1;
    public com.toedter.calendar.JDateChooser startDate;
    public com.mirrorcompany.swing_designs.Table table1;
    public com.mirrorcompany.swing_designs.MyTextField user_ID;
    // End of variables declaration//GEN-END:variables
}
