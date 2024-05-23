package com.mirrorcompany.pages;

import com.mirrorcompany.component.LogOutPopUp;
import com.mirrorcompany.component.PanelEnergyBill;
import com.mirrorcompany.component.PanelListOfDevicesToConnect;
import com.mirrorcompany.component.PanelLoading;
import com.mirrorcompany.component.PanelVerifyCode;
import com.mirrorcompany.component.ProfileAndSubscribePanel;
import com.mirrorcompany.dao.EnergyUsageDao;
import com.mirrorcompany.dao.ServiceMailDao;
import com.mirrorcompany.dao.UserDao;
import com.mirrorcompany.dao.bill.BillingSystemDao;
import com.mirrorcompany.dao.bill.EnergyBillingDao;
import com.mirrorcompany.model.MessageModel;
import com.mirrorcompany.model.UserModel;
import com.mirrorcompany.model.bill.BillingSystemModel;
import com.mirrorcompany.model.bill.EnergyBillingModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author ekire
 */
public class Dash extends javax.swing.JFrame {
//    Menu1 menu = new Menu1();
    private static String userEmail;
    private static String role;
    private static String status;
    private static int UserID;
    private static String name;
    private PanelLoading loading;
    private PanelListOfDevicesToConnect deviceListPanel;
    private PanelEnergyBill energyBill;
    private static Form_1 userDash;
    private static Form_3 page3;
    private static Form_2 page2;
    private static Form_Profile pageProfile;
    private static Form_Report pageReport;
    private static Form_Help pageHelp;
    private static Form_LogOut pageLogOut;
    private LogOutPopUp logout;
    private PanelVerifyCode verifyme;
    private MigLayout Layout;
    private ProfileAndSubscribePanel myProfile;
    
    // DAOs and MODELs
    private static EnergyUsageDao energyDao = new EnergyUsageDao();
    private final BillingSystemDao billSysDao = new BillingSystemDao();
    private BillingSystemModel userSub = new BillingSystemModel();
    private UserDao userdao;
    private UserModel user;
    private EnergyBillingDao billDao = new EnergyBillingDao();
    private EnergyBillingModel billModel = new EnergyBillingModel();
    
    // Map of device IDs to watts per hour
    private static Map<Integer, BigDecimal> DEVICES = new HashMap<>();
    private static double TrendEnergy = 0.0;

    public static double getTrendEnergy() {
        return TrendEnergy;
    }
    
    private boolean isMaximized = false;
    
    private MigLayout layout;

    public static int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public static String getStatus() {
        return status;
    }

    public static String getRole() {
        return role;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static String getUserName() {
        return name;
    }
    private Timer timer;
    public static BigDecimal trend = BigDecimal.ZERO;
    public Dash(){
        
    }
    
    public Dash(int id) {
        initComponents();
        setBackground(new Color(0, 0, 0));
        Dash.UserID = id;
        energyBill = new PanelEnergyBill();
        verifyme = new PanelVerifyCode();
        userdao = new UserDao();
        loading = new PanelLoading();
        logout = new LogOutPopUp();
        
//        user = userdao.SearchUserByID(UserID);
        if (user != null){
            Dash.userEmail = user.getEmail();
            Dash.role = user.getRole();
            Dash.status = user.getStatus();
            Dash.name = user.getUsername();
        } else {
//            Dash.UserID = 64;
//            Dash.userEmail = "jeremie.ukundwa@gmail.com";
//            Dash.role = "User";
//            Dash.status = "Verified";
//            Dash.name = "Jeremie";
        }
        deviceListPanel = new PanelListOfDevicesToConnect(UserID);
        menu1.initMoving(Dash.this);
        header1.initMoving(Dash.this);
        header1.setEmail(userEmail);

        
        // schedule a method execution to every 1 minute
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(Dash::updateEnergyUsage, 0, 1, TimeUnit.MINUTES);
//        
        // Some alternatives of the above one
        
        
        // list of devices to be connected
        ActionListener eventListDevices = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showDeviceList();
            }
        };
        
        ActionListener eventEnergyBill = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!page3.AlertVerified.getText().equals("Not Verified") || !page3.AlertVerified.getText().equals("-----")){
                    showEnergyBill();
                } else {
                    page3.message.setText(Dash.userEmail+", YOU ARE NOT ILEGIBLE TO GENERATE A NEW BILL");
                    page3.message.setForeground(new Color(191, 25, 25));
                    page3.message.setFont(new java.awt.Font("Tahoma", 1, 14));
                    new Thread(() -> {
                        try {
                            Thread.sleep(4500); // Wait for 3 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        page3.message.setText("");
                    }).start();
                }  
            }
        };
        
        ActionListener reloadEvt = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                userDash.initEnergyTables();
                page2.initEnergyData();
            }
        };
        
        ActionListener eventVerifyCode = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (page3.alertVerificationMessage().equals("Not Verified")){
                    
//                    String code = userdao.getCode();
//                    System.out.println("verification code : "+code+"\nEmail: "+Dash.userEmail+"\nID: "+Dash.UserID);
                    user.setEmail(Dash.userEmail);
                    user.setUserID(Dash.UserID);
//                    user.setVerifyCode(code);
//                    userdao.doneVerify(Dash.UserID);
                    sendMail(user);
                } else {
                    page3.message.setText(Dash.userEmail+", YOU CAN START TO GENERATE NEW BILL");
                    page3.message.setForeground(new Color(12, 163, 39));
                    page3.message.setFont(new java.awt.Font("Tahoma", 1, 14));
                    new Thread(() -> {
                        try {
                            Thread.sleep(3000); // Wait for 3 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        page3.message.setText("");
                    }).start();
                }
            }
        };
        
        ActionListener openSubscriptionPageEvent = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                showLoading(true); // Show loading popup
                new Thread(() -> {
                    try {
                        Thread.sleep(1000); // Wait for 3 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                     // Hide loading popup
                    componentShow(pageProfile);
                    showProfile();
                    showLoading(false);
                }).start();
            }
        };
        // INITIALIZE ALL MY PAGES WITH THEIR RESPECTIVE EVENTS
        
//        userDash = new Form_1(eventListDevices, openSubscriptionPageEvent);
//        page3 = new Form_3(eventEnergyBill, eventVerifyCode);
//        page2 = new Form_2(eventListDevices, openSubscriptionPageEvent);
        pageProfile = new Form_Profile();
        pageReport = new Form_Report();
        pageHelp = new Form_Help();
        pageLogOut = new Form_LogOut();
//        userDash = new Form_1(eventListDevices, openSubscriptionPageEvent);
        
        // Timer Task 
//        timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                DEVICES = userDash.getDeviceMap();
//                updateEnergyUsage();
//            }
//        }, 0, 30000); 
//        
            ActionListener subscribe = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("SUBSCRIBE BUTTON WAS CLICKED");
                if (myProfile.meterNo.getText().equals("--------") || myProfile.stat.getText().equals("--------") || myProfile.stat.getText().equals("Not Verified")){
                    userSub.setUserId(UserID);
                    LocalDate date = LocalDate.now();
                    userSub.setSubscriptionDate(Date.valueOf(date));
                    userSub.setSubscriptionType("Monthly");
                    String meterNo = billSysDao.getMeterNumber();
                    userSub.setMeterNumber(meterNo);
                    userSub.setActive(true);
                    if (!billSysDao.isHeSubscribed(UserID)){
                        if (billSysDao.createBillingSystem(userSub)){
                        
//                            UserModel user = userdao.SearchUserByID(UserID);
//                            user.setStatus("Verified");
//                            user.setVerifyCode("");
//                            userdao.UpdateUser(user);
                            myProfile.message.setText("Sending for confirmation...");
                            myProfile.message.setForeground(new Color(12, 163, 39));
                            myProfile.message.setFont(new java.awt.Font("Tahoma", 1, 14));
                            new Thread(() -> {
                                try {
                                    Thread.sleep(5000); // Wait for 3 seconds
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                myProfile.meterNo.setText(meterNo);
                                myProfile.message.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/sucess.png")));                        
                                myProfile.message.setText("Congratulation, you are now a CashPower User");
                                myProfile.message.setForeground(new Color(12, 163, 39));
                                myProfile.message.setFont(new java.awt.Font("Tahoma", 1, 14));
                            }).start();
                            new Thread(() -> {
                                try {
                                    Thread.sleep(9000); // Wait for 3 seconds
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
        //                        myProfile.message.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/warning.png")));
                                myProfile.message.setText("Make sure you have select all devices before GENERATING A ENERGY BILL.");
                                myProfile.message.setFont(new java.awt.Font("Tahoma", 1, 14));
                            }).start();

                            new Thread(() -> {
                                try {
                                    Thread.sleep(13500); // Wait for 3 seconds
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                myProfile.message.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/sucess.png")));
                                myProfile.message.setText("SUBSCRIBED SUCCESSFULLY | GENERATE BILLS GRANTED");
                                myProfile.SubscribeBtn.setEnabled(false);
                                myProfile.message.setForeground(new Color(12, 163, 39));
                                myProfile.message.setFont(new java.awt.Font("Tahoma", 1, 14));
                            }).start();
                        } else {
                            new Thread(() -> {
                                try {
                                    Thread.sleep(13500); // Wait for 3 seconds
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                myProfile.message.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/error.png")));
                                myProfile.message.setText("FAILED TO SUBSCRIBE | SOMETHING WENT WRONG TRY AGAIN LATER");
                                myProfile.SubscribeBtn.setEnabled(true);
                                myProfile.message.setForeground(new Color(204, 0, 11));
                                myProfile.message.setFont(new java.awt.Font("Tahoma", 1, 14));
                            }).start();
                        }
                    } else {
                        myProfile.message.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/sucess.png")));                                        
                        myProfile.message.setText("YOU ARE ALREADY A USER | NO ACTION IS NEEDED NOW");
                        myProfile.SubscribeBtn.setEnabled(false);
                        myProfile.message.setForeground(new Color(12, 163, 39));
                        myProfile.message.setFont(new java.awt.Font("Tahoma", 1, 14));
                    }
                }
            }
        };
        myProfile = new ProfileAndSubscribePanel(eventListDevices, openSubscriptionPageEvent, subscribe);
        
        componentShow(userDash);
        
        logout.quitTheApp(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showLoading(true); // Show loading popup
                new Thread(() -> {
                    try {
                        Thread.sleep(1000); // Wait for 3 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    showLoading(false); // Hide loading popup
                    logout.setVisible(false);
                    System.exit(0);
                }).start();
            }
        });
        
        // LEFT SIDE MENU
        
        deviceListPanel.listOfDevicesToConnect(reloadEvt);
        page2.nextbill(eventEnergyBill);
        menu1.addEventMenuSelected((int index) -> {
            switch (index){
                case 0:
                    userDash.initEnergyTables();
                    closeAllPopupPanels();
                    showLoading(true); // Show loading popup
                    new Thread(() -> {
                        try {
                            Thread.sleep(1500); // Wait for 1 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
//                        componentShow(new Form_1(eventListDevices, openSubscriptionPageEvent));
                        showLoading(false); // Hide loading popup
                    }).start();
                    break;
                case 1:
                    closeAllPopupPanels();
                    showLoading(true); // Show loading popup
                    new Thread(() -> {
                        try {
                            Thread.sleep(1000); // Wait for 3 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        componentShow(new Form_2(eventEnergyBill, openSubscriptionPageEvent));
                        showLoading(false); // Hide loading popup
                        page2.initEnergyData();
                    }).start();
                    break;
                case 3:
                    closeAllPopupPanels();
                    showLoading(true); // Show loading popup
                    new Thread(() -> {
                        try {
                            Thread.sleep(1000); // Wait for 3 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        componentShow(new Form_3(eventEnergyBill, eventVerifyCode));
                        showLoading(false); // Hide loading popup
                    }).start();
                    break;
                case 4:
                    closeAllPopupPanels();
                    showLoading(true); // Show loading popup
                    new Thread(() -> {
                        try {
                            Thread.sleep(1000); // Wait for 3 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                        showProfile();
                        componentShow(pageProfile);
                        showLoading(false); // Hide loading popup
                    }).start();
                    break;
                case 6:
                    closeAllPopupPanels();
                    showLoading(true); // Show loading popup
                    new Thread(() -> {
                        try {
                            Thread.sleep(1000); // Wait for 3 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                        componentShow(pageReport);
                        showLoading(false); // Hide loading popup
                    }).start();
                    break;
                case 7:
                    closeAllPopupPanels();
                    showLoading(true); // Show loading popup
                    new Thread(() -> {
                        try {
                            Thread.sleep(1000); // Wait for 3 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                        componentShow(pageHelp);
                        showLoading(false); // Hide loading popup
                    }).start();
                    break;
                case 8:
                    closeAllPopupPanels();
                    showLoading(true); // Show loading popup
                    new Thread(() -> {
                        try {
                            Thread.sleep(1000); // Wait for 3 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        componentShow(pageLogOut);
                        showLoading(false); // Hide loading popup
                        showLogout();
                    }).start();
                    break;
                default:
                    userDash.initEnergyTables();
                    closeAllPopupPanels();
                    showLoading(true); // Show loading popup
                    new Thread(() -> {
                        try {
                            Thread.sleep(1000); // Wait for 1 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
//                        componentShow(new Form_1(eventListDevices, openSubscriptionPageEvent));
                        showLoading(false); // Hide loading popup
                    }).start();
                    break;
            }
        });
        header1.close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle close button click
                System.exit(0); // Close the application
            }
        });
        header1.minimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle minimize button click
                setExtendedState(JFrame.ICONIFIED); // Minimize the window
            }
        });
        header1.goToProfile(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeAllPopupPanels();
                showLoading(true); // Show loading popup
                new Thread(() -> {
                    try {
                        Thread.sleep(1000); // Wait for 3 seconds
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    showLoading(false); // Hide loading popup
                    componentShow(pageProfile);
                    showProfile();
                }).start();
            }
        });
        
        energyBill.closeBill(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                energyBill.setVisible(false);
            }
        } );
        
        
        verifyme.addEventButtonOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isCorrectCode(user)){
                    if (!billSysDao.isHeSubscribed(UserID)){
                        userSub = new BillingSystemModel();
                        userSub.setUserId(Dash.UserID);
                        LocalDate currentDate = LocalDate.now();
                        userSub.setSubscriptionDate(Date.valueOf(currentDate));
                        userSub.setSubscriptionType("Monthly");
                        userSub.setActive(true);
                        String meter = billSysDao.getMeterNumber();
                        System.out.println("Meter Number: "+meter);
                        userSub.setMeterNumber(meter);
                        myProfile = new ProfileAndSubscribePanel(eventListDevices, openSubscriptionPageEvent, subscribe);
                        if (billSysDao.createBillingSystem(userSub)){
                            page3.EnableUserInfo(userdao);
                            verifyme.msgFeedBack.setText("Successfully Subcription && Verification");
                            verifyme.msgFeedBack.setForeground(new Color(19, 142, 41));
                            verifyme.msgFeedBack.setFont(new java.awt.Font("Tahoma", 1, 14));
                            showLoading(true);
                            new Thread(() -> {
                                try {
                                    Thread.sleep(3000); // Wait for 3 seconds
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                showLoading(false);
//                                componentShow(new Form_3(eventEnergyBill, eventVerifyCode));
                                verifyme.setVisible(false);
                            }).start();
                        }
                    }
                    else {
                        verifyme.msgFeedBack.setText("IT SEEMS THIS USER IS VERIFIED");
                        verifyme.msgFeedBack.setForeground(new Color(61, 63, 61));
                        verifyme.msgFeedBack.setFont(new java.awt.Font("Tahoma", 1, 14));
                        new Thread(() -> {
                            try {
                                Thread.sleep(3500); // Wait for 3 seconds
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            verifyme.setVisible(false);
                            page3.txtGetVerifiedmsg.setText("");
                        }).start();
                    }
                } else {
                    verifyme.msgFeedBack.setText("Wrong Code, double check your email");
                    verifyme.msgFeedBack.setForeground(new Color(211, 65, 42));
                    verifyme.msgFeedBack.setFont(new java.awt.Font("Tahoma", 1, 13));
                    new Thread(() -> {
                        try {
                            Thread.sleep(3000); // Wait for 3 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        verifyme.msgFeedBack.setText("The code was sent to your mail, check it out");
                        verifyme.msgFeedBack.setForeground(new Color(61, 63, 61));
                    }).start();
                }
            }
        });
        
        
        
        energyBill.confirmBill(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                userSub = billSysDao.getBillingSystemByUserId(Dash.UserID);
                if (userSub != null){
                    int billSysID = userSub.getBillingSystemId();
                    double savedPower = Double.parseDouble(energyBill.savedAmount.getText());
                    BigDecimal bigSavedAmount = BigDecimal.valueOf(savedPower);
                    // GET BILL MONTH AND DATE
                    String month = energyBill.month.getSelectedItem().toString();
                    int userid = Dash.UserID;
                    double totalkWhConusmed = Double.parseDouble(energyBill.powerConsumed.getText().trim()); //energyDao.calculatePowerUsageForAllUserDevice(Dash.UserID);
                    BigDecimal kWhConusmed = BigDecimal.valueOf(totalkWhConusmed);

                    double amountPaid = totalkWhConusmed * 0.05;
                    BigDecimal bigAmountPaid = BigDecimal.valueOf(amountPaid);
                    String paymentStatus = "Not Paid";

                    billModel = new EnergyBillingModel(billSysID, bigSavedAmount, userid, kWhConusmed, month, bigAmountPaid, paymentStatus);
                    try {
                        LocalDate currentDate = LocalDate.now();
                        if (billDao.isCurrentBill(UserID, Date.valueOf(currentDate))){ 
                            billModel = billDao.getCurrentBill(UserID, Date.valueOf(currentDate));
                            String currentmonth = billModel.getMonth();
                            if (currentmonth.equals(energyBill.month.getSelectedItem().toString())){
                                billModel.setPaymentDate(Date.valueOf(currentDate));
                                billModel.setPaymentStatus("Paid");
                                energyBill.confirmAndGenerateBill.setText("Energy Bill of "+currentmonth+" Is Already Generated.");
                            } else {
                                energyBill.confirmAndGenerateBill.setText("Generate Energy Bill for "+energyBill.month.getSelectedItem().toString());
                            }
                        }
                        else if (billDao.addEnergyBilling(billModel, month, "2024")){
                            energyBill.confirmAndGenerateBill.setText("Generating ...");
                            new Thread(() -> {
                                try {
                                    Thread.sleep(4000); // Wait for 3 seconds
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                page3.generatedLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/icons/sucess.png")));
                                page3.generatedLabel.setText("Succefully");
                                page3.billID.setText(String.valueOf(billModel.getBillId()));
                                page3.amountToBePaid.setText(energyBill.savedAmount.getText());
                                page3.estimatedkWh.setText(energyBill.powerConsumed.getText());
//                                 componentShow(new Form_3(eventEnergyBill, eventVerifyCode));
                                energyBill.setVisible(false);
                            }).start();
                            // from date to string syntax:
    //                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    //                         String dateString = formatter.parse("2024-02-01");
                            String dateString = String.valueOf(billModel.getBillDate());
                            page3.billDate.setText(dateString);
                            page3.date.setText(dateString);
                        } else {
                            energyBill.confirmAndGenerateBill.setText("Failed Generate !..");
                            new Thread(() -> {
                                try {
                                    Thread.sleep(5000); // Wait for 3 seconds
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                energyBill.confirmAndGenerateBill.setText("Auto generating at the end of "+month+"  | You Can Generate Now");
                            }).start();
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
//    private static void updateEnergyUsage(){
//        System.out.println("\n\nIt is execute every 1 minute |Scheduler|executor|");
//        trend.add(EnergyUsageDao.recordkWhEnergyHistory(UserID, DEVICES));
//        System.out.println("done executing...");
//    }
    
    private static void componentShow(Component com){
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }
    
    public static void goToHome(){
        componentShow(userDash);
    }
    public static void goToHistory(){
        componentShow(page2);
    }
    public static void goToReport(){
        componentShow(pageReport);
    }
    public static void goToBills(){
        componentShow(page3);
    }
    
    public static void callPage3(ActionListener billE, ActionListener codeE){
//        componentShow(new Form_3(billE, codeE));
    }
    
    public static void callPage2(ActionListener listE, ActionListener subE){
//        componentShow(new Form_2(listE, subE));
    }
    public static void callUserDash(ActionListener listE, ActionListener subE){
//        componentShow(new Form_1(listE, subE));
    }
    
    private void closeAllPopupPanels(){
        deviceListPanel.setVisible(false);
        energyBill.setVisible(false);
        myProfile.setVisible(false);
        logout.setVisible(false);
    }
    
    
    public void showDeviceList() {
        if (deviceListPanel != null) {
            
            bg.setLayout(layout);
            deviceListPanel.setBounds(235, 35, getWidth() - 230, getHeight() - 25);
            bg.setLayer(deviceListPanel, JLayeredPane.POPUP_LAYER);
            bg.add(deviceListPanel, "pos 0 0 100% 100%");
            bg.repaint();
            bg.revalidate();
            deviceListPanel.setVisible(true);
            deviceListPanel.initRowpopulation();
        }
    }
    
    public void showLogout() {
        if (logout != null) {
            bg.setLayout(layout);
            logout.setBounds(235, 35, getWidth() - 230, getHeight() - 25);
            bg.setLayer(logout, JLayeredPane.POPUP_LAYER);
            bg.add(logout, "pos 0 0 100% 100%");
            bg.repaint();
            bg.revalidate();
            logout.setVisible(true);
        }
    }
    public void showProfile(){
        if (myProfile != null) {
            bg.setLayout(layout);
            myProfile.setBounds(235, 35, getWidth() - 230, getHeight() - 25);
            bg.setLayer(myProfile, JLayeredPane.POPUP_LAYER);
            bg.add(myProfile, "pos 0 0 100% 100%");
            bg.repaint();
            bg.revalidate();
            myProfile.setVisible(true);
        }
    }
    public void verificationPopup() {
        if (verifyme != null) {
            bg.setLayout(layout);
            verifyme.setBounds(235, 35, getWidth() - 230, getHeight() - 25);
            bg.setLayer(verifyme, JLayeredPane.POPUP_LAYER);
            bg.add(verifyme, "pos 0 0 100% 100%");
            bg.repaint();
            bg.revalidate();
            verifyme.setVisible(true);
        }
    }
    // Display energy bill on screen, let's paint it the layered pane
    public void showEnergyBill() {
        userSub = billSysDao.getBillingSystemByUserId(Dash.UserID);
        if (userSub != null){
            String meterNo = userSub.getMeterNumber();
            energyBill.meter.setText(meterNo);
            energyBill.email.setText(Dash.userEmail);
            userdao = new UserDao();
//            Integer NoOfDevices = userdao.getNumberOfDevices(Dash.UserID);
//            if (NoOfDevices != null){
//                energyBill.noOfDevices.setText(String.valueOf(NoOfDevices));
//            }
//            double totalkWhConusmed = energyDao.calculatePowerUsageForAllUserDevice(Dash.UserID);

            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    double totalkWhConsumed = Double.parseDouble(String.valueOf(trend));
                    double savedPower = (25000.0 - totalkWhConsumed);
                    double moneySaved = savedPower * 0.05;
                    double toBePaid = (totalkWhConsumed * 0.05);
                    double tva = toBePaid * 20 / 100;
                    double netToBePaid = toBePaid + tva;
                    energyBill.savedkWh.setText("Save : "+savedPower+"kWh ~ $:");
                    energyBill.powerConsumed.setText(String.valueOf(totalkWhConsumed));
                    energyBill.savedAmount.setText(String.valueOf(moneySaved));
                    energyBill.amountToBePaid.setText(String.valueOf(netToBePaid));
                }
            }, 0, 30000); 
            
            
        } else {
            System.out.println("This UserID:"+Dash.UserID+" has no Subcription");
        }
        if (energyBill != null) {
            bg.setLayout(layout);
            energyBill.setBounds(235, 35, getWidth() - 230, getHeight() - 25);
            bg.setLayer(energyBill, JLayeredPane.POPUP_LAYER);
            bg.add(energyBill, "pos 0 0 100% 100%");
            bg.repaint();
            bg.revalidate();
            energyBill.setVisible(true);
        }
    }
    public void showLoading(boolean visible) {
        if (visible == false)
        {
            loading.setVisible(visible);
        }
        else {
            bg.setLayout(layout);
            loading.setBounds(235, 35, getWidth() - 230, getHeight() - 25);
            bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
            bg.add(loading, "pos 0 0 100% 100%");
            bg.repaint();
            bg.revalidate();
            loading.setVisible(true);
        }
    }
    
    public boolean isCorrectCode(UserModel user){
        boolean isCorrect = false;
        String codeSent = user.getVerifyCode();
        String inputCode = verifyme.getInputCode();
        if(codeSent.equals(inputCode))
            isCorrect = true;
        return isCorrect;
    }
    
    public void sendMail(UserModel user){
        new Thread(new Runnable() {
            @Override
            public void run() {
                showLoading(true);
                String email = user.getEmail();
                String code = user.getVerifyCode();
                System.out.println("From SendEmail \nEmail: "+email+"\nCode: "+code);
                MessageModel msg = new ServiceMailDao().sendMain(user.getEmail(), user.getVerifyCode());
                if (msg.isSuccess()){
                    showLoading(false);
                    verificationPopup();
                } else {
                    showLoading(false);
                }
            }
        }).start();
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();
        body = new javax.swing.JPanel();
        menu1 = new com.mirrorcompany.component.Menu1();
        header1 = new com.mirrorcompany.component.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1320, 670));

        bg.setOpaque(true);
        bg.setPreferredSize(new java.awt.Dimension(1320, 670));

        body.setPreferredSize(new java.awt.Dimension(1074, 634));
        body.setLayout(new java.awt.BorderLayout());

        bg.setLayer(body, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(menu1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(header1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    new Dash(UserID).setVisible(true);
                }
        });
    }
    
    public void isHiden(){
        setState(JFrame.ICONIFIED);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLayeredPane bg;
    private static javax.swing.JPanel body;
    private com.mirrorcompany.component.Header header1;
    private com.mirrorcompany.component.Menu1 menu1;
    // End of variables declaration//GEN-END:variables
}
