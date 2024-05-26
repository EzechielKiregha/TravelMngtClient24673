package com.mirrorcompany.view;

import com.mirrorcompany.component.LogOutPopUp;
import com.mirrorcompany.component.Message;
import com.mirrorcompany.events.EventMenuSelected;
import com.mirrorcompany.model.Itinerary;
import com.mirrorcompany.model.TripSegment;
import com.mirrorcompany.model.User;
import com.mirrorcompany.service.ItineraryService;
import com.mirrorcompany.service.UserService;
import com.mirrorcompany.view.comp.MenuLayout;
import com.mirrorcompany.view.form.Form_1;
import com.mirrorcompany.view.form.Form_2;
import com.mirrorcompany.view.form.community.CommunityPlatform;
import com.mirrorcompany.view.form.dash.Home;
import com.mirrorcompany.view.form.flight.FlightBooking;
import com.mirrorcompany.view.form.flight.bookForm_1;
import com.mirrorcompany.view.form.profile.PanelProfile;
import com.mirrorcompany.view.form.profile.UserProfile;
import com.mirrorcompany.view.form.travel.NewItineraryPopUp;
import com.mirrorcompany.view.form.travel.Recommend;
import com.mirrorcompany.view.form.travel.TMS;
import com.mirrorcompany.view.form.travel.ViewItineraries;
import com.mirrorcompany.view.form.travel.TmsExpenses;
import com.mirrorcompany.view.form.travel.AddTripSegment;
import com.mirrorcompany.view.form.travel.tmsForm_1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicButtonUI;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class OverviewFrame extends javax.swing.JFrame {

    private static JLayeredPane body;
    private static LogOutPopUp logout;
    private static PanelProfile myProfile;
    private static MigLayout layout;
    private static Animator animator;
    private static TimingTarget target;
    private static MenuLayout menuLayout;
    
    private static TMS tms;
    private static NewItineraryPopUp itinerary;
    private static ViewItineraries viewItinerary;
    private static TmsExpenses expenses;
    private static AddTripSegment tripSegment;
    private static Recommend recommendation;
    
    private static Home dash;
    private static CommunityPlatform mainC;
    private static UserProfile mainP;
    private static FlightBooking mainB;
    
    private static final String RMI_SERVER = "UserService";
    private static final String RMI_SERVER_ITINERARY = "ItineraryService";
    private static User user;
    
    public OverviewFrame(User user) {
        initComponents();
        initializeButtons();
        System.out.println("Hello World!");
        tms = new TMS();
        mainB = new FlightBooking();
        mainC = new CommunityPlatform();
        mainP = new UserProfile();
        menuLayout = new MenuLayout();
        dash = new Home();
        logout = new LogOutPopUp();
        myProfile = new PanelProfile();
        
        // START CODE REGARDING TMS TAB
            
        itinerary = new NewItineraryPopUp();
        viewItinerary = new ViewItineraries();
        expenses = new TmsExpenses();
        recommendation = new Recommend();
        
        body = new JLayeredPane();
        layout = new MigLayout("fill", "0[fill]0", "0[fill]0");
        body.setLayer(menuLayout, JLayeredPane.POPUP_LAYER);
        body.setLayout(layout);
        // Set up the frame and add components
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(body, BorderLayout.EAST);
        body.setSize(new Dimension(1050, 720));
        body.setPreferredSize(new Dimension(1050, 720));
        try {
            if (user != null){
                this.user = user;
                itinerary.setUser(user);
                toDash(dash);
                dash.userName.setText(user.getUsername());
                showMessage(Message.MessageType.SUCCESS, "Login Successfull! " + user.getUsername());
                System.out.println("Login successful!");
            } else {
                showLogout();
                logout.LogMeIn.setVisible(false);
                logout.QuitTheApp.setVisible(false);
                logout.logoutMessage.setText("SESSION HAS EXPIRED");
                showMessage(Message.MessageType.ERROR, "SESSION HAS EXPIRED, LOGIN PLEASE");
                System.out.println("SESSION HAS EXPIRED LOGIN PLEASE");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       try{
                            Thread.sleep(9000);
                            Main main = new Main();
                            main.setVisible(true);
                            dispose();
                       }catch (InterruptedException e){
                           System.err.println(e);
                       }
                    }
                }).start();
                
            }
        } catch (Exception e){
            e.printStackTrace();
            
        }
        
        
        btnTravelM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeAllComponentIfExist();
                updateMenuList(menuLayout, 1); // this will trigger the menu list for travel management to display in Menu class
                tms.initMoving(OverviewFrame.this);
                showComponent(tms);
            }
        });

        btnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeAllComponentIfExist();
                updateMenuList(menuLayout, 2); // this will trigger the menu list for booking flight to display in Menu class
                mainB.initMoving(OverviewFrame.this);
                showComponent(mainB);
            }
        });

        btnCom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeAllComponentIfExist();
                updateMenuList(menuLayout, 3); // this will trigger the menu list for Community to display in Menu class
                mainC.initMoving(OverviewFrame.this);
                showComponent(mainC);
            }
        });

        btnProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeAllComponentIfExist();
                updateMenuList(menuLayout, 4);
                 // this will trigger the menu list for profile to display in Menu class
                mainP.initMoving(OverviewFrame.this);
                showComponent(mainP);
                if (!myProfile.isVisible())
                    showProfile();
            }
        });
        btnlogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeAllComponentIfExist();
                showLogout();
            }
        });
        logout.quitTheApp(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        dash.newItineraryEvt(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeAllComponentIfExist();
                updateMenuList(menuLayout, 1); // this will trigger the menu list for travel management to display in Menu class
                tms.initMoving(OverviewFrame.this);
                showComponent(tms);
                tms.show(new tmsForm_1());
                tms.showPopUp(itinerary);
            }
        });
        dash.addExpEvt(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeAllComponentIfExist();
                updateMenuList(menuLayout, 1); // this will trigger the menu list for travel management to display in Menu class
                tms.initMoving(OverviewFrame.this);
                showComponent(tms);
                tms.show(new tmsForm_1());
                tms.showPopUp(expenses);
            }
        });
        dash.bookEvt(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeAllComponentIfExist();
                updateMenuList(menuLayout, 2); // this will trigger the menu list for travel management to display in Menu class
                mainB.initMoving(OverviewFrame.this);
                showComponent(mainB);
                mainB.show(new bookForm_1());
//                mainB.showPopUp();
            }
        });
        dash.joinCommunityEvt(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeAllComponentIfExist();
                updateMenuList(menuLayout, 3); // this will trigger the menu list for travel management to display in Menu class
                mainC.initMoving(OverviewFrame.this);
                showComponent(mainB);
                mainC.show(new bookForm_1());
//                mainB.showPopUp();
            }
        });
        
        body.add(menuLayout, "pos -215 0 100% 100%", 0);
        // Timing target to control the animation and visibility of the menu layout
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float x = fraction * 215;
                float alpha;
                if (menuLayout.isShow()) {
                    x = -x;
                    alpha = 0.5f - (fraction / 2);
                } else {
                    x -= 215;
                    alpha = fraction / 2;
                }
                layout.setComponentConstraints(menuLayout, "pos " + (int) x + " 0 100% 100%");
                menuLayout.setAlpha(alpha < 0 ? 0 : alpha);
                body.revalidate();
            }

            @Override
            public void end() {
                menuLayout.setShow(!menuLayout.isShow());
                if (!menuLayout.isShow()) {
                    menuLayout.setVisible(false);
                }
            }
        };
        // Initialize the animator with the timing target
        animator = new Animator(200, target);
        // Add mouse listener to menuLayout to start the animator if it's not running
        menuLayout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me) && !animator.isRunning()) {
                    animator.start();
                }
            }
        });
        addListeners(tms, mainB, mainC, mainP);
    }

    private static void removeComponentIfExists(Component component) {
        if (component.getParent() != null) {
            body.remove(component);
        }
    }
    
    private static void showComponent(Component component) {
        body.add(component);
        body.revalidate();
        body.repaint();
    }
    
    public static void toDash(Home dash){
        removeAllComponentIfExist();
        btnHome.setBackground(new Color(54, 81, 207));
        btnTravelM.setBackground(new Color(6, 7, 29));
        btnBook.setBackground(new Color(6, 7, 29));
        btnCom.setBackground(new Color(6, 7, 29));
        btnProfile.setBackground(new Color(6, 7, 29));
        btnlogout.setBackground(new Color(6, 7, 29));
        showComponent(dash);
    }
    
    public static void removeAllComponentIfExist(){
        removeComponentIfExists(myProfile);
        removeComponentIfExists(itinerary);
        removeComponentIfExists(expenses);
        removeComponentIfExists(recommendation);
        removeComponentIfExists(mainC);
        removeComponentIfExists(dash);
        removeComponentIfExists(tms);
        removeComponentIfExists(mainB);
        removeComponentIfExists(mainP);
        removeComponentIfExists(logout);
    }

    
    public void showLogout() {
        if (logout != null) {
            removeAllComponentIfExist();
            body.setLayout(layout);
            logout.setBounds(235, 35, getWidth() - 230, getHeight() - 25);
            body.setLayer(logout, JLayeredPane.POPUP_LAYER);
            body.add(logout, "pos 0 0 100% 100%");
            body.repaint();
            body.revalidate();
            logout.setVisible(true);
        }
    }
    
    public void showProfile(){
        if (myProfile != null) {
            removeAllComponentIfExist();
            body.setLayout(layout);
            myProfile.setBounds(235, 35, getWidth() - 230, getHeight() - 25);
            body.setLayer(myProfile, JLayeredPane.POPUP_LAYER);
            body.add(myProfile, "pos 0 0 100% 100%");
            body.repaint();
            body.revalidate();
            myProfile.setVisible(true);
        }
    }
    public void addListeners(TMS tms, FlightBooking bf, CommunityPlatform c, UserProfile p){
        // Remove previous action listeners from tms
        for (ActionListener al : tms.getBtn().getActionListeners()) {
            tms.getBtn().removeActionListener(al);
        }

        // Add new action listener to tms
        tms.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
//                    System.out.println("Animator not running");
                    if (!menuLayout.isShow()) {
//                        System.out.println("Menu layout not shown");
                        menuLayout.setVisible(true);
                        animator.start();
                        System.out.println("travel menu clicked...");
                    }
                }
            }
        });
        for (ActionListener al : bf.getBtn().getActionListeners()) {
            bf.getBtn().removeActionListener(al);
        }

        // Add new action listener to tms
        bf.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
//                    System.out.println("Animator not running");
                    if (!menuLayout.isShow()) {
//                        System.out.println("Menu layout not shown");
                        menuLayout.setVisible(true);
                        animator.start();
                        System.out.println("booking menu clicked...");
                    }
                }
            }
        });
        for (ActionListener al : c.getBtn().getActionListeners()) {
            c.getBtn().removeActionListener(al);
        }

        // Add new action listener to tms
        c.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
//                    System.out.println("Animator not running");
                    if (!menuLayout.isShow()) {
//                        System.out.println("Menu layout not shown");
                        menuLayout.setVisible(true);
                        animator.start();
                        System.out.println("community menu clicked...");
                    }
                }
            }
        });
        for (ActionListener al : p.getBtn().getActionListeners()) {
            p.getBtn().removeActionListener(al);
        }

        // Add new action listener to tms
        p.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
//                    System.out.println("Animator not running");
                    if (!menuLayout.isShow()) {
//                        System.out.println("Menu layout not shown");
                        menuLayout.setVisible(true);
                        animator.start();
                        System.out.println("profile menu clicked...");
                    }
                }
            }
        });
    }

    private void initializeButtons() {
        // Initialize buttons and their properties
        btnHome.setBackground(new Color(54, 81, 207));
        btnHome.setUI(new BasicButtonUI());
        JButton[] btns = {btnTravelM, btnBook, btnCom, btnProfile, btnlogout};
        for (JButton btn : btns) {
            btn.setBackground(new Color(6, 7, 29));
            btn.setUI(new BasicButtonUI());
        }
    }
    
    public static void removeTMSPopUpsIfExist(){
        tms.removeComponentIfExists(itinerary);
        tms.removeComponentIfExists(expenses);
        tms.removeComponentIfExists(tripSegment);
        tms.removeComponentIfExists(viewItinerary);
        tms.removeComponentIfExists(recommendation);
    }
    
    private void updateMenuList(MenuLayout menu, int selectedTab) {
        switch (selectedTab) {
            case 1: // Travel Management System (TMS)
//                initTMSMenuItems(menu);
                System.out.println("List Menu Refilled");
                menu.getMenu().setLogo(1,"TRAVEL MANAGEMENT", "TRAV", "jpg");
                menu.getMenu().addEventMenuSelected(new EventMenuSelected() {
                    @Override
                    public void selected(int index) {
                        tms.show(new tmsForm_1());
                        if (index == 0){
                            removeTMSPopUpsIfExist();
                            tms.showPopUp(viewItinerary);
                        } else if (index == 1) {
                            removeTMSPopUpsIfExist();
                            tms.showPopUp(itinerary);
                        } else if (index == 2){
                            removeTMSPopUpsIfExist();
                            tms.showPopUp(expenses);
                        } else if(index == 3){
                            removeTMSPopUpsIfExist();
                            tms.showPopUp(tripSegment);
                        }
                        else if(index == 4){
                            removeTMSPopUpsIfExist();
                            tms.showPopUp(recommendation);
                        }
                    }
                });
                break;
            case 2: // Booking
//                initBookingMenuItems(menu);
                System.out.println("List Menu Refilled");
                menu.getMenu().setLogo(2,"FLIGHT BOOKING", "flight", "jpg");
                menu.getMenu().addEventMenuSelected(new EventMenuSelected() {
                    @Override
                    public void selected(int index) {
                        if (index == 0){
                            mainB.show(new Form_1());
                        } else if (index == 1) {
                            mainB.show(new Form_2());
                        }
                    }
                });
                break;
            case 3: // Community
//                initCommunityMenuItems(menu);
                System.out.println("List Menu Refilled");
                menu.getMenu().setLogo(3,"TMS COMMUNITY", "community", "png");
                menu.getMenu().addEventMenuSelected(new EventMenuSelected() {
                    @Override
                    public void selected(int index) {
                        if (index == 0){
                            mainC.show(new Form_1());
                        } else if (index == 1) {
                            mainC.show(new Form_2());
                        }
                    }
                });
                break;
            case 4: // Profile
//                initProfileMenuItems(menu);
                System.out.println("List Menu Refilled");
                menu.getMenu().setLogo(4,"PROFILES", "me", "jpeg");
                menu.getMenu().addEventMenuSelected(new EventMenuSelected() {
                    @Override
                    public void selected(int index) {
                        if (index == 0){
                            mainP.show(new Form_1());
                        } else if (index == 1) {
                            mainP.show(new Form_2());
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
    
    public void showMessage(Message.MessageType messageType, String message){
        Message msg = new Message();
        msg.showMessage(messageType, message);
        removeComponentIfExists(msg);
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void begin() {
                if (!msg.isShow()){
                    body.add(msg, "pos 0.5al -30", 0); //0.5al means align horizontally to the center and -30 means offset from the bottom by -30 pixels
                    msg.setVisible(true);
                    body.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (msg.isShow()){
                    f = 40 * (1f - fraction);
                }else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(msg, "pos 0.5al "+(int)(f - 30));
                body.repaint();
                body.revalidate();
            }

            @Override
            public void end() {
                if(msg.isShow()){
                    body.remove(msg);
                    body.repaint();
                    body.revalidate();
                }else{
                    msg.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
               try{
                   Thread.sleep(9000);
                   animator.start();
               }catch (InterruptedException e){
                   System.err.println(e);
               }
            }
        }).start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmain = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnTravelM = new javax.swing.JButton();
        btnBook = new javax.swing.JButton();
        btnCom = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnlogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1130, 720));

        cmain.setBackground(new java.awt.Color(6, 7, 29));
        cmain.setPreferredSize(new java.awt.Dimension(80, 0));

        jPanel1.setBackground(new java.awt.Color(6, 7, 29));
        jPanel1.setPreferredSize(new java.awt.Dimension(50, 250));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 60));

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/4.png"))); // NOI18N
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHome.setPreferredSize(new java.awt.Dimension(40, 40));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome);

        cmain.add(jPanel1);

        btnTravelM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/7.png"))); // NOI18N
        btnTravelM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTravelM.setPreferredSize(new java.awt.Dimension(40, 40));
        btnTravelM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTravelMActionPerformed(evt);
            }
        });
        cmain.add(btnTravelM);

        btnBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/8.png"))); // NOI18N
        btnBook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBook.setPreferredSize(new java.awt.Dimension(40, 40));
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });
        cmain.add(btnBook);

        btnCom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/2.png"))); // NOI18N
        btnCom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCom.setPreferredSize(new java.awt.Dimension(40, 40));
        btnCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComActionPerformed(evt);
            }
        });
        cmain.add(btnCom);

        btnProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/3.png"))); // NOI18N
        btnProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProfile.setPreferredSize(new java.awt.Dimension(40, 40));
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });
        cmain.add(btnProfile);

        jPanel2.setBackground(new java.awt.Color(6, 7, 29));
        jPanel2.setPreferredSize(new java.awt.Dimension(50, 200));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 110));

        btnlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/4.png"))); // NOI18N
        btnlogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnlogout.setPreferredSize(new java.awt.Dimension(40, 40));
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });
        jPanel2.add(btnlogout);

        cmain.add(jPanel2);

        getContentPane().add(cmain, java.awt.BorderLayout.WEST);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        btnHome.setBackground(new Color(6, 7, 29));
        btnTravelM.setBackground(new Color(6, 7, 29));
        btnBook.setBackground(new Color(6, 7, 29));
        btnCom.setBackground(new Color(6, 7, 29));
        btnProfile.setBackground(new Color(6, 7, 29));
        btnlogout.setBackground(new Color(54, 81, 207));
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed

        btnHome.setBackground(new Color(6, 7, 29));
        btnTravelM.setBackground(new Color(6, 7, 29));
        btnBook.setBackground(new Color(6, 7, 29));
        btnCom.setBackground(new Color(6, 7, 29));
        btnProfile.setBackground(new Color(54, 81, 207));
        btnlogout.setBackground(new Color(6, 7, 29));
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComActionPerformed

        btnHome.setBackground(new Color(6, 7, 29));
        btnTravelM.setBackground(new Color(6, 7, 29));
        btnBook.setBackground(new Color(6, 7, 29));
        btnCom.setBackground(new Color(54, 81, 207));
        btnProfile.setBackground(new Color(6, 7, 29));
        btnlogout.setBackground(new Color(6, 7, 29));
    }//GEN-LAST:event_btnComActionPerformed

    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookActionPerformed
        
        btnHome.setBackground(new Color(6, 7, 29));
        btnTravelM.setBackground(new Color(6, 7, 29));
        btnBook.setBackground(new Color(54, 81, 207));
        btnCom.setBackground(new Color(6, 7, 29));
        btnProfile.setBackground(new Color(6, 7, 29));
        btnlogout.setBackground(new Color(6, 7, 29));
    }//GEN-LAST:event_btnBookActionPerformed

    private void btnTravelMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTravelMActionPerformed

        btnHome.setBackground(new Color(6, 7, 29));
        btnTravelM.setBackground(new Color(54, 81, 207));
        btnBook.setBackground(new Color(6, 7, 29));
        btnCom.setBackground(new Color(6, 7, 29));
        btnProfile.setBackground(new Color(6, 7, 29));
        btnlogout.setBackground(new Color(6, 7, 29));
    }//GEN-LAST:event_btnTravelMActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        toDash(dash);
    }//GEN-LAST:event_btnHomeActionPerformed

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
            java.util.logging.Logger.getLogger(OverviewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OverviewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OverviewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OverviewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OverviewFrame(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    static javax.swing.JButton btnBook;
    static javax.swing.JButton btnCom;
    static javax.swing.JButton btnHome;
    static javax.swing.JButton btnProfile;
    static javax.swing.JButton btnTravelM;
    static javax.swing.JButton btnlogout;
    private javax.swing.JPanel cmain;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
