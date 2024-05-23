package com.mirrorcompany.view;

import com.mirrorcompany.component.Message;
import com.mirrorcompany.component.PanelComponent;
import com.mirrorcompany.component.PanelLoading;
import com.mirrorcompany.component.PanelLoginAndRegister;
import com.mirrorcompany.component.PanelPasswordForgotten;
import com.mirrorcompany.component.PanelVerifyCode;
import com.mirrorcompany.dao.ServiceMailDao;
import com.mirrorcompany.dao.UserDao;
import com.mirrorcompany.model.MessageModel;
import com.mirrorcompany.model.UserModel;
import com.mirrorcompany.pages.Dash;
import com.mirrorcompany.pages.AdministrationPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author ekire
 */
public class Main extends javax.swing.JFrame {
    
    private MigLayout Layout;
    private PanelComponent cover;
    private PanelLoading loading;
    private PanelVerifyCode verifyCode;
    private PanelPasswordForgotten pwdForgotten;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private boolean isLogin;
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private PanelLoginAndRegister loginAndRegister;
    public UserDao userDao;
    
    private Dash userDashBoard;
    private AdministrationPanel adminDashBoard;
    
    public Main() {
        initComponents();
        init();
    }

    private void init(){
        userDao = new UserDao();
        Layout = new MigLayout("fill, insets 0");
        cover = new PanelComponent();
        loading = new PanelLoading();
        verifyCode = new PanelVerifyCode();
        pwdForgotten = new PanelPasswordForgotten();
        
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UserModel user = loginAndRegister.getUser();
                register(user);
            }
        };
        ActionListener eventPasswordForgotten = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePassword();
            }
        };
        
        ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UserModel user = loginAndRegister.getUser();
                login(user);
            }
        };
        loginAndRegister = new PanelLoginAndRegister(eventRegister, eventLogin, eventPasswordForgotten);
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if(fraction <= 0.5f){
                    size += fraction * addSize;
                }else{
                    size += addSize - fraction * addSize;
                }
                if(isLogin){
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if(fraction >= 0.5f){
                        cover.registerRight(fractionCover * 100);
                    }else{
                        cover.loginRight(fractionLogin * 100);
                    }
                }else{
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f){
                        cover.registerLeft(fraction * 100);
                    }else{
                        cover.loginLeft((1f - fraction)* 100);
                    }
                }
                if(fraction >= 0.5f){
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                Layout.setComponentConstraints(cover, "width "+size+"%, pos "+fractionCover+"al 0 n 100%");
                Layout.setComponentConstraints(loginAndRegister, "width "+loginSize+"%, pos "+fractionLogin+"al 0 n 100%");

                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }  
        };
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        bg.setLayout(Layout);
        bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        bg.setLayer(verifyCode, JLayeredPane.POPUP_LAYER);
        bg.setLayer(pwdForgotten, JLayeredPane.POPUP_LAYER);
        bg.add(loading, "pos 0 0 100% 100%");
        bg.add(verifyCode, "pos 0 0 100% 100%");
        bg.add(pwdForgotten, "pos 0 0 100% 100%");
        bg.add(cover, "width "+coverSize+"%, pos 0al 0 n 100%");
        bg.add(loginAndRegister, "width "+loginSize+"%, pos 1al 0 n 100%");
        
        cover.addEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!animator.isRunning()){
                    animator.start();
                }
            }
        });
        verifyCode.addEventButtonOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UserModel user = loginAndRegister.getUser();
                if (userDao.verifyCodeWithUser(user.getUserID(), verifyCode.getInputCode())){
                    userDao.doneVerify(user.getUserID());
                    showMessage(Message.MessageType.SUCCESS, "Registered Successfully!");
                    verifyCode.setVisible(false);
                } else {
                    showMessage(Message.MessageType.ERROR, "Wrong Verification Code");
                }
            }
        });
        
        pwdForgotten.addEventButtonOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        
    }
    
    // Email Valodation
    public boolean isValidEmail(String email) {
        // Regex pattern for a valid email address
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile the regex pattern into a pattern object
        Pattern pattern = Pattern.compile(regex);

        // Match the email address against the pattern
        Matcher matcher = pattern.matcher(email);

    // Return true if the email matches the pattern, false otherwise
    return matcher.matches();
}
    private void register(UserModel user){
        if (user != null) {
            String email = user.getEmail();
            String username = user.getEmail();
            String pwd = user.getPassword();
            String role = user.getRole();
            if(email.equals("") || username.equals("")){
                System.out.println("Email & Username is required");
                showMessage(Message.MessageType.ERROR, "Email & Username is required");
            } else if (!isValidEmail(user.getEmail())) {
                System.out.println("Invalid Email Address");
                showMessage(Message.MessageType.ERROR, "Invalid Email Address");
            } else if(pwd.equals("")) {
                System.out.println("Password is required");
                showMessage(Message.MessageType.ERROR, "Password is required");
            } else if(role.equals("")){
                System.out.println("Role is missing");
                showMessage(Message.MessageType.ERROR, "Role is missing!");
            } else if (userDao.isUserDuplicated(user.getUsername())) {
                System.out.println("User Name Already Exist");
                showMessage(Message.MessageType.ERROR, "User Name Already Exist");
            } else if (userDao.isEmailDuplicated(user.getEmail())) {
                System.out.println("Email Already Exist");
                showMessage(Message.MessageType.ERROR, "Email Already Exist");
            } else if(verifyCode.equals("")){
                showMessage(Message.MessageType.ERROR, "Code was not generaed!");
                System.out.println("Code was not generaed!");
            } else {
                if(userDao.registerUser(user, user.getVerifyCode()))
                    sendMail(user);
            }
        }
    }
    
    public void login(UserModel user){
        if (user != null){
            String email = user.getEmail();
            String pwd = user.getPassword();
            if (email.isEmpty() && pwd.isEmpty()){
                System.out.println("fields can not be empty");
                showMessage(Message.MessageType.ERROR, "Fields cannot be empty");
            } else if(pwd.equals("")){
                System.out.println("password field is empty");
                showMessage(Message.MessageType.ERROR, "The Password Field Is Empty.");
            }else if(email.equals("")) {
                System.out.println("email field is empty");
                showMessage(Message.MessageType.ERROR, "The Email Field Is Empty.");
            
            }else if (isValidEmail(email)) {
                boolean userChecked = userDao.loginCheck(email, pwd);
                if (userChecked){
                    user = userDao.SearchUserByEmail(email);
                    if (user != null)
                        redirecting(user);
                }else {
                    System.out.println("Wrong Email Or Password");
                    showMessage(Message.MessageType.ERROR, "Wrong Email Or Password");
                }
            }else {
                System.out.println("invalid email");
                showMessage(Message.MessageType.ERROR, "Invalid Email Address");
            }
        }
    }
    public void redirecting(UserModel user){
        bg.setLayout(Layout);
        bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        bg.add(loading, "pos 0 0 100% 100%");
        new Thread(new Runnable() {
            @Override
            public void run() {
                loading.setVisible(true);
                try { 
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (user != null){
                    String email = user.getEmail();
                    String role = user.getRole();
                    String status = user.getStatus();
                    int userid = user.getUserID();
                    showMessage(Message.MessageType.SUCCESS, "Success | Redirecting...");
                    if (role.toLowerCase().equals("admin")){
                        adminDashBoard = new AdministrationPanel(email, role, status);
                        adminDashBoard.setVisible(true);
                        loading.setVisible(false);
                        dispose();
                    }else{
                        userDashBoard = new Dash(userid);
                        userDashBoard.setVisible(true);
                        loading.setVisible(false);
                        dispose();
                    }
                    
                } else {
                    loading.setVisible(false);
                    showMessage(Message.MessageType.ERROR, "Failed Redirecting!!!");
                }
            }
    }).start();
}

    public void changePassword(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                loading.setVisible(true);
                try { 
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                loading.setVisible(false);
                
                pwdForgotten.setVisible(true);
                
            }
        }).start();
        
    }
    
    public void sendMail(UserModel user){
        new Thread(new Runnable() {
            @Override
            public void run() {
                loading.setVisible(true);
                String email = user.getEmail();
                String code = user.getVerifyCode();
                System.out.println("Email: "+email+"\nCode: "+code);
                MessageModel msg = new ServiceMailDao().sendMain(user.getEmail(), user.getVerifyCode());
                if (msg.isSuccess()){
                    loading.setVisible(false);
                    verifyCode.setVisible(true);
                } else {
                    loading.setVisible(false);
                    showMessage(Message.MessageType.CONNECTION_ERROR, msg.getMessage());
                }
            }
        }).start();
    }
    
    public void showMessage(Message.MessageType messageType, String message){
        Message msg = new Message();
        msg.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void begin() {
                if (!msg.isShow()){
                    bg.add(msg, "pos 0.5al -30", 0); //0.5al means align horizontally to the center and -30 means offset from the bottom by -30 pixels
                    msg.setVisible(true);
                    bg.repaint();
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
                Layout.setComponentConstraints(msg, "pos 0.5al "+(int)(f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if(msg.isShow()){
                    bg.remove(msg);
                    bg.repaint();
                    bg.revalidate();
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
                   Thread.sleep(4000);
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

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
