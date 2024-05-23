/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.dao.bill;

/**
 *
 * @author ekire
 */
import java.sql.Connection;
import com.mirrorcompany.model.bill.BillingSystemModel;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BillingSystemDao {

    private final Connection connection = DBcon();
    
    private Connection DBcon(){
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
    
    public boolean FindSubscriptionByUserID(int id){
        try {
            Connection con = DBcon();
            String findQuery = "SELECT * FROM billing_system WHERE user_id=?;";
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

    public boolean createBillingSystem(BillingSystemModel billingSystem) {
        System.out.println("Subscription Billing CALLED....");
        int userID = billingSystem.getUserId();
        System.out.println("userID :" +userID);
        if (FindSubscriptionByUserID(userID) == false){
            String query = "INSERT INTO billing_system (user_id, subscription_date, subscription_type, active, meter_number) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userID);
                preparedStatement.setDate(2, billingSystem.getSubscriptionDate());
                preparedStatement.setString(3, billingSystem.getSubscriptionType());
                preparedStatement.setBoolean(4, billingSystem.isActive());
                preparedStatement.setString(5, billingSystem.getMeterNumber());
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    

    public boolean updateBillingSystem(BillingSystemModel billingSystem) {
        String query = "UPDATE billing_system SET user_id=?, subscription_date=?, subscription_type=?, active=? WHERE billing_system_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, billingSystem.getUserId());
            preparedStatement.setDate(2, new java.sql.Date(billingSystem.getSubscriptionDate().getTime()));
            preparedStatement.setString(3, billingSystem.getSubscriptionType());
            preparedStatement.setBoolean(4, billingSystem.isActive());
            preparedStatement.setInt(5, billingSystem.getBillingSystemId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isHeSubscribed(int userID) {
        String query = "SELECT * FROM billing_system WHERE user_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet rowsAffected = preparedStatement.executeQuery();
            if (rowsAffected.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBillingSystem(int billingSystemId) {
        String query = "DELETE FROM billing_system WHERE billing_system_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, billingSystemId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public BillingSystemModel getBillingSystemByUserId(int userId) {
        String query = "SELECT * FROM billing_system WHERE user_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return extractBillingSystemFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BillingSystemModel getBillingSystemById(int billingSystemId) {
        String query = "SELECT * FROM billing_system WHERE billing_system_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, billingSystemId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return extractBillingSystemFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BillingSystemModel> getAllBillingSystems() {
        List<BillingSystemModel> billingSystems = new ArrayList<>();
        String query = "SELECT * FROM billing_system";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                BillingSystemModel billingSystem = extractBillingSystemFromResultSet(resultSet);
                billingSystems.add(billingSystem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billingSystems;
    }

    private BillingSystemModel extractBillingSystemFromResultSet(ResultSet resultSet) throws SQLException {
        BillingSystemModel billingSystem = new BillingSystemModel();
        billingSystem.setBillingSystemId(resultSet.getInt("billing_system_id"));
        billingSystem.setUserId(resultSet.getInt("user_id"));
        billingSystem.setSubscriptionDate(resultSet.getDate("subscription_date"));
        billingSystem.setSubscriptionType(resultSet.getString("subscription_type"));
        billingSystem.setActive(resultSet.getBoolean("active"));
        billingSystem.setMeterNumber(resultSet.getString("meter_number"));
        return billingSystem;
    }
    
    public String getMeterNumber(){
        DecimalFormat df = new DecimalFormat("000000000");
        Random rand = new Random();
        String meterNumber = df.format(rand.nextInt(1000000000));
        while (isCodeDuplicated(meterNumber)){
            meterNumber = df.format(rand.nextInt(1000000000));
        }
        return meterNumber;
    }
    
    public boolean isCodeDuplicated(String number){
        boolean duplicated = false;
        try{
            try (Connection con = DBcon(); PreparedStatement pst = con.prepareStatement("SELECT user_id FROM billing_system WHERE meter_number=? limit 1")) {
                pst.setString(1, number);
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
}

