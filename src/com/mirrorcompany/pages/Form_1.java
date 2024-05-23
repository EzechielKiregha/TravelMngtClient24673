package com.mirrorcompany.pages;

import com.mirrorcompany.dao.DeviceDaoForUser;
import com.mirrorcompany.dao.EnergyUsageDao;
import com.mirrorcompany.dao.bill.BillingSystemDao;
import com.mirrorcompany.dao.bill.EnergyBillingDao;
import com.mirrorcompany.model.DeviceModelForUser;
import com.mirrorcompany.model.PolarAreaModel;
import com.mirrorcompany.model.bill.BillingSystemModel;
import com.mirrorcompany.model.bill.EnergyBillingModel;
import static com.mirrorcompany.pages.Dash.trend;

import static com.mirrorcompany.pages.Form_Report.meterNumberReport;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ekire
 */
public class Form_1 extends javax.swing.JPanel {
    private static int userID = Dash.getUserID();
    private BigDecimal remainingkWh = BigDecimal.ZERO;
    // init my need pages here
    private Form_Profile profPage;
    private Form_Report repo;
    private Form_2 usagePage ;
    private Form_3 billPage;
    private int selectedRow;
    private int deviceID;
    private static double TrendEnergy = 0.0;

    public static double getTrendEnergy() {
        return TrendEnergy;
    }
    
    
    private static String email = Dash.getUserEmail();
    private BillingSystemDao billSysDao = new BillingSystemDao();
    
    private static EnergyUsageDao energyDao = new EnergyUsageDao();
    private BillingSystemModel userSubscription = new BillingSystemModel();
    
    private EnergyBillingDao billDao = new EnergyBillingDao();
    private EnergyBillingModel billModel = new EnergyBillingModel();
    // Map of device IDs to watts per hour
    private static Map<Integer, BigDecimal> DEVICES;
    private static Map<Integer, BigDecimal> DEVICESUPDATES;
    private ActionListener listE;
    private ActionListener subE;

    public Form_1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void setListE(ActionListener listE) {
        this.listE = listE;
    }

    public void setSubE(ActionListener subE) {
        this.subE = subE;
    }
    
    private Timer timer;
    
    public Form_1(ActionListener listDeviceEvent, ActionListener subscribe) {
        initComponents();
        polarArea2.start();
        DEVICES = new HashMap<>();
        DEVICESUPDATES = new HashMap<>();
        
        btnReniew.addActionListener(subscribe);
        
        initEnergyTables();
        
        this.listE = listDeviceEvent;
        this.subE = subscribe;
        
        // Inside Form_1 class
        billPage = new Form_3();
        usagePage = new Form_2(listE, subscribe);
        repo = new Form_Report();
        profPage = new Form_Profile();
        // get event recursively
        
        userSubscription = billSysDao.getBillingSystemByUserId(userID);
        if (userSubscription != null){
            System.out.println("User Has Subcribed...");
            String meterNo = userSubscription.getMeterNumber();
            meter.setText(meterNo);
            profPage.meterNumberProfile.setText(meterNo);
            repo.meterNumberReport.setText(meterNo);
            remainingkWh = BigDecimal.valueOf(25000.0);

            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    initEnergyTables();

                    trend = trend.add(energyDao.recordkWhEnergyHistory(userID, DEVICES));
                    remainingkWh = remainingkWh.subtract(trend);
                    deliveredkWh.setText(remainingkWh + "  kWh");
                    Power.setText(trend+"    kWh");
                    usagePage.PowerPage2.setText(trend+"    kWh");
                    billPage.PowerPage3.setText(trend+"    kWh");
                    profPage.PowerPageProfile.setText(trend+"    kWh");
                    repo.PowerPageReport.setText(trend+"    kWh");
                    
                    System.out.println("\nTRENDING ENERGY PER 30 Seconds :"+trend);
                }
            }, 0, 30000); // 60000 milliseconds = 1 minute

            LocalDate date = LocalDate.now();
            billModel = billDao.getCurrentBill(userID, Date.valueOf(date));
            if (billModel != null){
                new Thread(() -> {
                    try {
                        Thread.sleep(4500); // Wait for 3 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    checking.setText("Smart Home Energy System is Checking...");
                }).start();
                new Thread(() -> {
                    try {
                        Thread.sleep(7000); // Wait for 3 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    checking.setText("The Connected Devices Energy Consumption Is Estimated To");
                }).start();
                new Thread(() -> {
                    try {
                        Thread.sleep(9500); // Wait for 3 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Power.setText(trend+"    kWh");
                    billDate.setText(billModel.getMonth()+": "+String.valueOf(billModel.getBillDate()));
                    btnReniew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/sucess.png")));
                    btnReniew.setText("MONTH BILL SECURED | Profile");
                }).start();
                
                
                

            }else {
                btnReniew.setText("CONNECT DEVICES BEFORE YOU GENERATE THE BILL");
                btnReniew.removeActionListener(subscribe);
            } 
        } else {
            btnReniew.setText("SUBSCRIBE TO CASHPOWER");
            btnReniew.addActionListener(subscribe);
            System.out.println("User Has No Subscription Yet.");
            checking.setText("User Has No Subscription Yet.");
        }
        if (btnReniew.getText().equals("SUBSCRIBE TO CASHPOWER"))
        {
            showDeviceListButton.setText("YOU SUBSCRIBE BEFORE CONNECTING");
            showDeviceListButton.setEnabled(false);
        }else{
            showDeviceListButton.setEnabled(true);
            showDeviceListButton.addActionListener(listDeviceEvent);
        }
        
        table1.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = table1.getSelectedRow();
                if (selectedRow != -1) {
                    // Update the text fields with the selected row's data
                    deviceID = Integer.parseInt(table1.getValueAt(selectedRow, 0).toString());
                    deviceToDisconnect.setText(table1.getValueAt(selectedRow, 1).toString());
                    double value = Double.parseDouble(table1.getValueAt(selectedRow, 2).toString());
//                        initialDeviceKWh.setText(value+"  kWh");
//                    double kWh_trend = Double.parseDouble(table1.getValueAt(selectedRow, 4).toString());
//                        deviceConsumption.setText(kWh_trend + "  kWh");
//                    double cost = kWh_trend * 500;
//                        kWhCost.setText(cost+"  Frw");
                    DEVICESUPDATES.put(deviceID, BigDecimal.valueOf(value));
                }
            }
        });
    }
    public static void initEnergyTables(){
        // load the Chart
        DEVICES.clear();
        LegendEnergy();
        //  Test Data table
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        DeviceDaoForUser dao = new DeviceDaoForUser();
        model.setRowCount(0);
        
        List<DeviceModelForUser> listOfDevices = dao.getAllDevicesForUser(userID);
        for (DeviceModelForUser device : listOfDevices){
            model.addRow(new DeviceModelForUser(
                    device.getDeviceID(),
                    device.getUserID(),
                    device.getDeviceName(),
                    device.getDateConnected(),
                    device.getPowerUsage(),
                    device.getStatus(),
                    device.getLocation()
            ).toDataTable());
 
            DEVICES.put(device.getDeviceID(), BigDecimal.valueOf(device.getPowerUsage()));
        }
        
        table1.fixTable(jScrollPane1);
    }
    
//    public Map<Integer, BigDecimal> getDeviceMap(){
//        Power.setText(Dash.trend+"    kWh");
//        return DEVICES;
//    }
    
    private static void LegendEnergy() {
        polarArea2.clear();
        polarArea2.start();
        DeviceDaoForUser dao = new DeviceDaoForUser();

        List<DeviceModelForUser> devices = dao.getHighConsumptionDevicesForUser(userID);
        for (DeviceModelForUser device : devices) {
            String name = device.getDeviceName();
            double powerConsumed = device.getPowerUsage();
            System.out.println("High Usage\nDevice: "+name+"\nPower: "+powerConsumed);
            Color color;
            if (powerConsumed > 5 && powerConsumed < 15) {
                color = new Color(8, 176, 20);
            } else if (powerConsumed <= 5 ) {
                color = new Color(8, 16, 120);
            } else {
                color = new Color(223, 32, 13);
            }
            polarArea2.addItem(new PolarAreaModel(color, name, powerConsumed));
        }
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.mirrorcompany.swing_designs.Table();
        panel = new javax.swing.JPanel();
        red = new com.mirrorcompany.charts.PolarAreaLabel();
        green = new com.mirrorcompany.charts.PolarAreaLabel();
        blue = new com.mirrorcompany.charts.PolarAreaLabel();
        CashPower = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        billDate = new javax.swing.JLabel();
        meter = new javax.swing.JLabel();
        btnReniew = new com.mirrorcompany.swing_designs.Button();
        jPanel2 = new javax.swing.JPanel();
        Power = new javax.swing.JLabel();
        checking = new javax.swing.JLabel();
        deliveredkWh = new javax.swing.JLabel();
        button2 = new com.mirrorcompany.swing_designs.Button();
        btnHistrory = new com.mirrorcompany.swing_designs.Button();
        btnDisconnect = new com.mirrorcompany.swing_designs.Button();
        btnReport = new com.mirrorcompany.swing_designs.Button();
        deviceName = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        showDeviceListButton = new com.mirrorcompany.swing_designs.Button();
        deviceDetectedIcon = new javax.swing.JLabel();
        polarArea2 = new com.mirrorcompany.charts.PolarArea();
        deviceToDisconnect = new javax.swing.JLabel();

        setBackground(new java.awt.Color(164, 196, 186));
        setForeground(java.awt.Color.white);
        setPreferredSize(new java.awt.Dimension(1074, 634));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Device ID", "Device Name", "Power kWh per 5h", "Connection Date", "Location", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setResizable(false);
            table1.getColumnModel().getColumn(0).setPreferredWidth(25);
            table1.getColumnModel().getColumn(2).setPreferredWidth(30);
            table1.getColumnModel().getColumn(4).setResizable(false);
        }

        panel.setBackground(new java.awt.Color(0, 51, 51));

        red.setBackground(new java.awt.Color(255, 0, 0));
        red.setForeground(new java.awt.Color(255, 255, 255));
        red.setText(" > 15 kWh/h");
        red.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        panel.add(red);

        green.setBackground(new java.awt.Color(51, 255, 102));
        green.setForeground(new java.awt.Color(255, 255, 255));
        green.setText("5 - 15 kWh/h");
        green.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        panel.add(green);

        blue.setBackground(new java.awt.Color(0, 0, 255));
        blue.setForeground(new java.awt.Color(255, 255, 255));
        blue.setText("0 - 5 kWh/h");
        blue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        panel.add(blue);

        CashPower.setBackground(new java.awt.Color(164, 196, 186));
        CashPower.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 5));

        jLabel1.setBackground(new java.awt.Color(164, 196, 186));
        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REMAINING DELIVERED  POWER : ");

        jLabel2.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel2.setText("CURRENT MONTH :");

        jLabel3.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel3.setText("METER NUMBER :");

        billDate.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        billDate.setText("-----/---/---");

        meter.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        meter.setText("-----------");

        btnReniew.setBackground(new java.awt.Color(0, 51, 51));
        btnReniew.setForeground(java.awt.Color.white);
        btnReniew.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 5));

        Power.setBackground(new java.awt.Color(0, 51, 51));
        Power.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        Power.setForeground(new java.awt.Color(255, 255, 255));
        Power.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Power.setText("---.--       kWh");
        Power.setOpaque(true);

        checking.setBackground(new java.awt.Color(0, 51, 51));
        checking.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        checking.setForeground(new java.awt.Color(255, 255, 255));
        checking.setText("Loading...");
        checking.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Power, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(checking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Power, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(checking, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        deliveredkWh.setFont(new java.awt.Font("Yu Gothic", 1, 24)); // NOI18N
        deliveredkWh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        deliveredkWh.setText("25000.0  kWh");

        javax.swing.GroupLayout CashPowerLayout = new javax.swing.GroupLayout(CashPower);
        CashPower.setLayout(CashPowerLayout);
        CashPowerLayout.setHorizontalGroup(
            CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(CashPowerLayout.createSequentialGroup()
                .addGroup(CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CashPowerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReniew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CashPowerLayout.createSequentialGroup()
                                .addGroup(CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                    .addGroup(CashPowerLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(meter, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(billDate, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(CashPowerLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deliveredkWh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        CashPowerLayout.setVerticalGroup(
            CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CashPowerLayout.createSequentialGroup()
                .addGroup(CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveredkWh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(meter)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CashPowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(billDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReniew, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        button2.setBackground(new java.awt.Color(2, 158, 78));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("REVIEW");
        button2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        btnHistrory.setBackground(new java.awt.Color(0, 51, 51));
        btnHistrory.setForeground(java.awt.Color.white);
        btnHistrory.setText("HISTORY / WEEK");
        btnHistrory.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHistrory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistroryActionPerformed(evt);
            }
        });

        btnDisconnect.setBackground(new java.awt.Color(169, 0, 0));
        btnDisconnect.setForeground(new java.awt.Color(255, 255, 255));
        btnDisconnect.setText("DISCONNECT");
        btnDisconnect.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisconnectActionPerformed(evt);
            }
        });

        btnReport.setBackground(new java.awt.Color(0, 46, 140));
        btnReport.setForeground(new java.awt.Color(255, 255, 255));
        btnReport.setText("REPORT");
        btnReport.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        deviceName.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        deviceName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deviceName.setText("High Usage [ Maximum 3 Devices ]");
        deviceName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 4));

        jLabel8.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("CONNECTED DEVICES");

        showDeviceListButton.setBackground(new java.awt.Color(0, 51, 51));
        showDeviceListButton.setForeground(java.awt.Color.white);
        showDeviceListButton.setText("CHECK FOR AVAILABLE DEVICE TO CONNECT WITH THE SYSTEM");
        showDeviceListButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        deviceDetectedIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deviceDetectedIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/warning.png"))); // NOI18N

        polarArea2.setBackground(new java.awt.Color(164, 196, 186));
        polarArea2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 4));

        javax.swing.GroupLayout polarArea2Layout = new javax.swing.GroupLayout(polarArea2);
        polarArea2.setLayout(polarArea2Layout);
        polarArea2Layout.setHorizontalGroup(
            polarArea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );
        polarArea2Layout.setVerticalGroup(
            polarArea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        deviceToDisconnect.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        deviceToDisconnect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deviceToDisconnect.setText("Device Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deviceDetectedIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CashPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(button2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReport, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDisconnect, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHistrory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deviceToDisconnect, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(deviceName, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                            .addComponent(polarArea2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(showDeviceListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CashPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(deviceName)
                                .addGap(0, 0, 0)
                                .addComponent(polarArea2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(deviceToDisconnect)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDisconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnHistrory, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deviceDetectedIcon, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(showDeviceListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisconnectActionPerformed
        
        if (deviceToDisconnect.getText().equals("Device Name"))
        {
            btnReniew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/info.png")));
            btnReniew.setText("NO DEVICE IS SELECTED FOR NOW");
            new Thread(() -> {
                try {
                    Thread.sleep(9500); // Wait for 3 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    //            Power.setText(billModel.getUserFullEnergy()+"    kWh");
    //            billDate.setText(billModel.getMonth()+": "+String.valueOf(billModel.getBillDate()));
                btnReniew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/sucess.png")));
                btnReniew.setText("MONTH BILL SECURED | Visit My Profile");
            }).start();
        }else
            desconnected();
    }//GEN-LAST:event_btnDisconnectActionPerformed

    private void btnHistroryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistroryActionPerformed
        Dash.callPage2(listE, subE);
    }//GEN-LAST:event_btnHistroryActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        Dash.goToReport();
    }//GEN-LAST:event_btnReportActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        btnReniew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/info.png")));
        btnReniew.setText("YOU CAN NOT REVIEW THIS DEVICE FOR NOW");
        new Thread(() -> {
            try {
                Thread.sleep(9500); // Wait for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            Power.setText(billModel.getUserFullEnergy()+"    kWh");
//            billDate.setText(billModel.getMonth()+": "+String.valueOf(billModel.getBillDate()));
            btnReniew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/sucess.png")));
            btnReniew.setText("MONTH BILL SECURED | Visit My Profile");
        }).start();
    }//GEN-LAST:event_button2ActionPerformed

    private void desconnected() {
        DeviceDaoForUser userdao = new DeviceDaoForUser();
        if (userdao.isConnected(deviceID, userID))
            if(userdao.deleteDevice(deviceID)){
                System.out.println("Device Disconnected");
                deviceToDisconnect.setText("Device Name");
                EnergyUsageDao.deviceDisconnectedKWHTrend(userID, DEVICESUPDATES);
                DEVICESUPDATES.clear();
                initEnergyTables();
//                DefaultTableModel model = (DefaultTableModel) table1.getModel();
//                model.removeRow(selectedRow);
            } else {
                System.out.println("Device Not Disconnected");
            }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CashPower;
    public static javax.swing.JLabel Power;
    private javax.swing.JLabel billDate;
    private com.mirrorcompany.charts.PolarAreaLabel blue;
    private com.mirrorcompany.swing_designs.Button btnDisconnect;
    private com.mirrorcompany.swing_designs.Button btnHistrory;
    private static com.mirrorcompany.swing_designs.Button btnReniew;
    private com.mirrorcompany.swing_designs.Button btnReport;
    private com.mirrorcompany.swing_designs.Button button2;
    public javax.swing.JLabel checking;
    private javax.swing.JLabel deliveredkWh;
    private javax.swing.JLabel deviceDetectedIcon;
    private javax.swing.JLabel deviceName;
    private javax.swing.JLabel deviceToDisconnect;
    private com.mirrorcompany.charts.PolarAreaLabel green;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private static javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel meter;
    private javax.swing.JPanel panel;
    private static com.mirrorcompany.charts.PolarArea polarArea2;
    private com.mirrorcompany.charts.PolarAreaLabel red;
    public com.mirrorcompany.swing_designs.Button showDeviceListButton;
    private static com.mirrorcompany.swing_designs.Table table1;
    // End of variables declaration//GEN-END:variables

}
