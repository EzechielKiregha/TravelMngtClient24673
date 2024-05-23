package com.mirrorcompany.model;

/**
 *
 * @author ekire
 */
public class UserModel {
    private int userID;
    private String username;
    private String password;
    private String email;
    private String role;
    private String verifyCode;
    private String status;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public UserModel() {
    }

    public UserModel(int userID, String username, String password, String email, String role, String verifyCode, String status) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.verifyCode = verifyCode;
        this.status = status;
    }

    public UserModel(String username, String password, String email, String role, String verifyCode, String status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.verifyCode = verifyCode;
        this.status = status;
    }

    public UserModel(int userID, String username, String password, String email, String role, String status) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public Object[] toDataTable(){
        return new Object[]{userID, username, password, email, role, status };
    }
}
