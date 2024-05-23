package com.mirrorcompany.dao;

import com.mirrorcompany.model.UserModel;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author ekire
 */
public class UserDaoOk {
    // connection
    
    public UserDaoOk(){
    }

    private Connection DBcon() {
        Connection con = null;
        try{
            String JDBC_URL = "jdbc:postgresql://localhost:5432/home_energy_management_system_db";
            String username = "postgres";
            String pwd = "258545";
            con = DriverManager.getConnection(JDBC_URL, username, pwd);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return con;
    }
    
    public boolean registerUser(UserModel userObj, String code){
        boolean isRegistered = false;
        try{
            Connection con = DBcon();
            
            String insertQuery = "INSERT INTO Users(username, password, email, role, verificationCode, status) VALUES(?, ?, ?, ?, ?, ?);";
            PreparedStatement pst = con.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, userObj.getUsername());
            pst.setString(2, userObj.getPassword());
            pst.setString(3, userObj.getEmail());
            pst.setString(4, userObj.getRole());
            pst.setString(5, code);
            pst.setString(6, userObj.getStatus());
            
            int row = pst.executeUpdate();
            int userID = 0;
            if (row > 0){
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()){
                    userID = rs.getInt(1);
                    isRegistered = true;
                }
                rs.close();
                pst.close();
                    
            }
            con.close();
            userObj.setUserID(userID);
            userObj.setVerifyCode(code);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return isRegistered;
    }
    
    
    public Integer getNumberOfDevices(int userID) {
        Integer number = null;
        try {
            Connection con = DBcon();
            String query = "SELECT COUNT(*) FROM user_devices WHERE userid=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, userID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }
    
    public boolean changerUserForgottenPwd(String email, String password){
        boolean done = false;
        try{
            Connection con = DBcon();
            PreparedStatement p = con.prepareStatement("update Users set password=? where email=?");
            p.setString(1, password);
            p.setString(2, email);
            int r = p.executeUpdate();
            if (r > 0){
                done = true;
            }
            p.close();
        } catch (SQLException e){
            e.printStackTrace();
            
        }
        return done;
    }
    
    public String getCode(){
        DecimalFormat df = new DecimalFormat("000000");
        Random rand = new Random();
        String code = df.format(rand.nextInt(1000000));
        while (isCodeDuplicated(code)){
            code = df.format(rand.nextInt(1000000));
        }
        return code;
    }
    
    public boolean isCodeDuplicated(String code){
        boolean duplicated = false;
        try{
            try (Connection con = DBcon(); PreparedStatement pst = con.prepareStatement("SELECT userID FROM Users WHERE verificationCode=? limit 1")) {
                pst.setString(1, code);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()){
                        duplicated = true;
                    }
                }
            }
           
        }catch (SQLException e){
            e.printStackTrace();
        }
         return duplicated;
    }
    
    public boolean isUserDuplicated(String user) {
        boolean duplicate = false;
        try{
            
            Connection con = DBcon();
            try (PreparedStatement p = con.prepareStatement("select userID from Users where username=? limit 1")) {
                p.setString(1, user);
                try (ResultSet r = p.executeQuery()) {
                    if (r.next()) {
                        duplicate = true;
                    }
                }
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return duplicate;
    }
    
    public boolean isEmailDuplicated(String email) {
        boolean duplicate = false;
        try{
            
            Connection con = DBcon();
            try (PreparedStatement p = con.prepareStatement("select userID from Users where email=? limit 1")) {
                p.setString(1, email);
                try (ResultSet r = p.executeQuery()) {
                    if (r.next()) {
                        duplicate = true;
                    }
                }
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return duplicate;
    }
    
    public boolean checkUserByEmail(String email) {
        boolean duplicate = false;
        try{
            
            Connection con = DBcon();
            try (PreparedStatement p = con.prepareStatement("select userID from Users where email=? limit 1")) {
                p.setString(1, email);
                try (ResultSet r = p.executeQuery()) {
                    if (r.next()) {
                        duplicate = true;
                    }
                }
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return duplicate;
    }
    
    public void doneVerify(int userID) {
        try{
            Connection con = DBcon();
            PreparedStatement p = con.prepareStatement("update Users set verificationCode='', status='Verified' where userID=?");
            p.setInt(1, userID);
            p.executeUpdate();
            p.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean verifyCodeWithUser(int userID, String code) {
        boolean verify = false;
        try {
            Connection con = DBcon();
            PreparedStatement p = con.prepareStatement("select userID from Users where userID=? and verificationCode=? limit 1");
            p.setInt(1, userID);
            p.setString(2, code);
            ResultSet r = p.executeQuery();
            if (r.next()){
                verify = true;
            }
            r.close();
            p.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return verify;
    }
    public String getUserName(int id){
        try {
            Connection con = DBcon();
            String findQuery = "SELECT * FROM Users WHERE userID=?;";
            PreparedStatement pst = con.prepareStatement(findQuery);
        
            pst.setInt(1, id);
            ResultSet rowAff = pst.executeQuery();
            
            if (rowAff.next()){
                con.close();
                return rowAff.getString("username");
            }
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
        return "No User Found";
    }
    public boolean FindUserByID(int id){
        try {
            Connection con = DBcon();
            String findQuery = "SELECT * FROM Users WHERE userID=?;";
            PreparedStatement pst = con.prepareStatement(findQuery);
        
            pst.setInt(1, id);
            ResultSet rowAff = pst.executeQuery();
            
            if (rowAff.next()){
                con.close();
                return true;
            }
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
        return false;    
    }
    
    public boolean loginCheck(String email, String pwd){
        boolean userChecked = false;
        try {
            Connection con = DBcon();
            String findQuery = "SELECT * FROM Users WHERE email=? and password=?;";
            PreparedStatement pst = con.prepareStatement(findQuery);
        
            pst.setString(1, email);
            pst.setString(2, pwd);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()){
                userChecked = true;
                con.close();
            }
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
        return userChecked;    
    }
    
    public UserModel SearchUserByID(int id){
        UserModel userObj = new UserModel();
        try {
            Connection con = DBcon();
            String findQuery = "SELECT * FROM Users WHERE userID=?;";
            PreparedStatement pst = con.prepareStatement(findQuery);

            pst.setInt(1, id);
            ResultSet result = pst.executeQuery();

            if(result.next()){
                userObj.setUserID(result.getInt("userID"));
                userObj.setUsername(result.getString("username"));
                userObj.setEmail(result.getString("email"));
                userObj.setPassword(result.getString("password"));
                userObj.setRole(result.getString("role"));
                userObj.setStatus(result.getString("status"));
                
                return userObj;
            } 
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public UserModel SearchUserByEmail(String email){
        UserModel userObj = new UserModel();
        try {
            Connection con = DBcon();
            String findQuery = "SELECT * FROM Users WHERE email=? limit 1;";
            PreparedStatement pst = con.prepareStatement(findQuery);

            pst.setString(1, email);
            ResultSet result = pst.executeQuery();

            if(result.next()){
                userObj.setUserID(result.getInt("userID"));
                userObj.setUsername(result.getString("username"));
                userObj.setEmail(result.getString("email"));
                userObj.setPassword(result.getString("password"));
                userObj.setRole(result.getString("role"));
                userObj.setStatus(result.getString("status"));
            } else {
                return null;
            }
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userObj;
    }
    
    public boolean DeleteUserByID(int id){
        boolean userDeleted = false;
        try{
            Connection con = DBcon();

            PreparedStatement pst = con.prepareCall("DELETE FROM Users WHERE userID=?");

            pst.setInt(1, id);
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                userDeleted = true;
            }
            con.close();
        }catch (SQLException e){

            e.printStackTrace();
        }
        return userDeleted;
    }
    
    public boolean UpdateUser(UserModel userObj){
        boolean userUpdated = false;
        
        if (FindUserByID(userObj.getUserID())){
            try{
                Connection con = DBcon();
                String updateQuery = "UPDATE Users SET username=?, password=?, email=?, role=?, status=? WHERE userID=?";
                PreparedStatement pst = con.prepareCall(updateQuery);
                pst.setString(1, userObj.getUsername());
                pst.setString(2, userObj.getPassword());
                pst.setString(3, userObj.getEmail());
                pst.setString(4, userObj.getRole());
                pst.setString(5, userObj.getStatus());
                pst.setInt(6, userObj.getUserID());
                
                int rowAff = pst.executeUpdate();
                if (rowAff > 0) {
                    userUpdated = true;
                }
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return userUpdated;
    }
    
    
    public List<UserModel> displayAllUsers(){
        List<UserModel> usersList = new ArrayList<>();
        try{
            Connection con = DBcon();
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM Users");
            while (result.next()){
                UserModel user = new UserModel();
                user.setUserID(result.getInt("userID"));
                user.setUsername(result.getString("username"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setRole(result.getString("role"));
                user.setStatus(result.getString("status"));

                usersList.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return usersList;
    }
}
