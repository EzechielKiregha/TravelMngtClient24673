
package com.mirrorcompany.component;

import com.mirrorcompany.dao.UserDao;
import com.mirrorcompany.model.User;
import com.mirrorcompany.swing_designs.Button;
import com.mirrorcompany.swing_designs.MyPasswordField;
import com.mirrorcompany.swing_designs.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author ekire
 */
public class PanelLoginAndRegister extends javax.swing.JLayeredPane {
    
    private User user;
    private PanelPasswordForgotten pwdForgotten;
    private MigLayout Layout;   
    public UserDao userDao;
    

    public User getUser() {
        return user;
    }

    public PanelLoginAndRegister(ActionListener event1, ActionListener event2, ActionListener event3) {
        initComponents();
        initRegister(event1);
        initLogin(event2, event3);
        
        userDao = new UserDao();
        
        Layout = new MigLayout("fill, insets 0");
        Login.setVisible(false);
        Register.setVisible(true);
        
        pwdForgotten = new PanelPasswordForgotten();
    }
    
    private void initRegister(ActionListener eventRegister){
        Register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
        JLabel createAccount = new JLabel("Create Account");
        createAccount.setFont(new Font("sansserif", 1, 30));
        createAccount.setForeground(new Color(4, 79, 229));
        Register.add(createAccount);
        
        MyTextField txtusername = new MyTextField();
        txtusername.setPrefixIcon(new ImageIcon(getClass().getResource("/com/mirrorcompany/icons/user.png")));
        txtusername.setHint("UserName");
        Register.add(txtusername, "w 60%");
        
        MyTextField txtphone = new MyTextField();
        txtphone.setPrefixIcon(new ImageIcon(getClass().getResource("/com/mirrorcompany/icons/user.png")));
        txtphone.setHint("Phone Number");
        Register.add(txtphone, "w 60%");
        
        MyTextField txtemail = new MyTextField();
        txtemail.setPrefixIcon(new ImageIcon(getClass().getResource("/com/mirrorcompany/icons/mail.png")));
        txtemail.setHint("email");
        Register.add(txtemail, "w 60%");
        
        MyPasswordField txtpwd = new MyPasswordField();
        txtpwd.setPrefixIcon(new ImageIcon(getClass().getResource("/com/mirrorcompany/icons/pass.png")));
        txtpwd.setHint("Password");
        Register.add(txtpwd, "w 60%");
        
        Button btn = new Button();
        btn.setBackground(new Color(24, 84, 203));
        btn.setForeground(new Color(250, 250, 250));
        btn.setFont(new Font("sansserif", 1, 14));
        btn.setText("SIGN UP");
        Register.add(btn, "w 40%, h 40"); 
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String username = txtusername.getText().trim();
                String phone = txtusername.getText().trim();
                String email = txtemail.getText().trim();
                String pwd = String.valueOf(txtpwd.getPassword());
                
                user = new User();
                
                user.setUsername(username);
                user.setEmail(email);
                user.setPhone(phone);
                user.setPassword(pwd);
//                String verifyCode = userDao.getCode();
//                
//                user = new UserModel(username, pwd, email, role, verifyCode, "Not Verified");
//                txtusername.setText("");
//                txtemail.setText("");
//                txtpwd.setText("");
//                txtrole.setText("");
                eventRegister.actionPerformed(ae);

            }
        });
//        btn.addActionListener(eventRegister);
    }
    
    private void initLogin(ActionListener eventLogin, ActionListener eventPasswordForgotten){
        Login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
        JLabel login = new JLabel("Sign In");
        login.setFont(new Font("sansserif", 1, 30));
        login.setForeground(new Color(4, 79, 229));
        Login.add(login);
        
        MyTextField txtemailL = new MyTextField();
        txtemailL.setPrefixIcon(new ImageIcon(getClass().getResource("/com/mirrorcompany/icons/mail.png")));
        txtemailL.setHint("email / username");
        Login.add(txtemailL, "w 60%");
        
        MyPasswordField txtpwdL = new MyPasswordField();
        txtpwdL.setPrefixIcon(new ImageIcon(getClass().getResource("/com/mirrorcompany/icons/pass.png")));
        txtpwdL.setHint("Password");
        Login.add(txtpwdL, "w 60%");
        
        JButton pwdForget = new JButton("Forget Your Password ?");
        pwdForget.setForeground(new Color(100,100,100));
        pwdForget.setFont(new Font("sansserif", 1, 12));
        pwdForget.setContentAreaFilled(false);
        pwdForget.addActionListener(eventPasswordForgotten);
        pwdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Login.add(pwdForget);
        Button btnLogin = new Button();
        btnLogin.setBackground(new Color(24, 84, 203));
        btnLogin.setForeground(new Color(250, 250, 250));
        btnLogin.setFont(new Font("sansserif", 1, 14));
        btnLogin.setText("SIGN IN");
        Login.add(btnLogin, "w 40%, h 40");
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                String email = txtemailL.getText().trim();
                String pwd = String.valueOf(txtpwdL.getPassword());
                
                user = new User();
                user.setEmail(email);
                user.setPassword(pwd);
                
                eventLogin.actionPerformed(ae);
            }
        });
//        btnLogin.addActionListener(eventLogin);
        
        pwdForget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
    }
    
    

    public void showRegister(boolean show){
        if(show){
            Register.setVisible(true);
            Login.setVisible(false);
        }else{
            Register.setVisible(false);
            Login.setVisible(true);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Login = new javax.swing.JPanel();
        Register = new javax.swing.JPanel();

        setName("bg1"); // NOI18N
        setLayout(new java.awt.CardLayout());

        Login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login);
        Login.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(Login, "card3");

        Register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout RegisterLayout = new javax.swing.GroupLayout(Register);
        Register.setLayout(RegisterLayout);
        RegisterLayout.setHorizontalGroup(
            RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        RegisterLayout.setVerticalGroup(
            RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(Register, "card2");
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Login;
    private javax.swing.JPanel Register;
    // End of variables declaration//GEN-END:variables
}
