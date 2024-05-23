package com.mirrorcompany.dao.bill;

/**
 *
 * @author ekire
 */
import com.mirrorcompany.model.bill.EnergyBillingModel;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnergyBillingDao {
    private Connection con = DBcon();
    
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

    // Create operation
    public boolean addEnergyBilling(EnergyBillingModel billing, String month, String year) throws ParseException{
        String query = "INSERT INTO energy_billing (billing_system_id, total_saved_amount, bill_date, " +
                "user_id, user_full_energy, month, amount_paid, payment_date, payment_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            if (con.isClosed()){
                con = DBcon();
            }
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, billing.getBillingSystemId());
            pst.setBigDecimal(2, billing.getSavedAmount());
            String dateString = getMonthForBill(month, year);
            System.out.println("returned date: "+dateString);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date billdate  = new Date(formatter.parse(dateString).getTime());
            pst.setDate(3,billdate);
            billing.setBillDate(billdate);
            pst.setInt(4, billing.getUserId());
            pst.setBigDecimal(5, billing.getUserFullEnergy());
            pst.setString(6, billing.getMonth());
            pst.setBigDecimal(7, billing.getAmountPaid());
            pst.setDate(8, billing.getPaymentDate());
            pst.setString(9, billing.getPaymentStatus());
            int row = pst.executeUpdate();
            if (row > 0){
                return true;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(EnergyBillingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Read operation
    public List<EnergyBillingModel> getAllEnergyBilling(){
        List<EnergyBillingModel> billingList = new ArrayList<>();
        String query = "SELECT * FROM energy_billing";
        try {
            if (con.isClosed()){
                con = DBcon();
            }
            PreparedStatement pst = con.prepareStatement(query); 
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                EnergyBillingModel billing = new EnergyBillingModel();
                billing.setBillId(rs.getInt("bill_id"));
                billing.setBillingSystemId(rs.getInt("billing_system_id"));
                billing.setSavedAmount(rs.getBigDecimal("total_saved_amount"));
                billing.setBillDate(rs.getDate("bill_date"));
                billing.setUserId(rs.getInt("user_id"));
                billing.setUserFullEnergy(rs.getBigDecimal("user_full_energy"));
                billing.setMonth(rs.getString("month"));
                billing.setAmountPaid(rs.getBigDecimal("amount_paid"));
                billing.setPaymentDate(rs.getDate("payment_date"));
                billing.setPaymentStatus(rs.getString("payment_status"));
                billingList.add(billing);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnergyBillingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return billingList;
    }
    public List<EnergyBillingModel> getAllEnergyBillingByUserID(int userId){
        List<EnergyBillingModel> billingList = new ArrayList<>();
        String query = "SELECT * FROM energy_billing WHERE user_id=?";
        try {
            if (con.isClosed()){
                con = DBcon();
            }
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                EnergyBillingModel billing = new EnergyBillingModel();
                billing.setBillId(rs.getInt("bill_id"));
                billing.setBillingSystemId(rs.getInt("billing_system_id"));
                billing.setSavedAmount(rs.getBigDecimal("total_saved_amount"));
                billing.setBillDate(rs.getDate("bill_date"));
                billing.setUserId(rs.getInt("user_id"));
                billing.setUserFullEnergy(rs.getBigDecimal("user_full_energy"));
                billing.setMonth(rs.getString("month"));
                billing.setAmountPaid(rs.getBigDecimal("amount_paid"));
                billing.setPaymentDate(rs.getDate("payment_date"));
                billing.setPaymentStatus(rs.getString("payment_status"));
                billingList.add(billing);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnergyBillingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return billingList;
    }

    // Update operation
    public boolean updateEnergyBilling(EnergyBillingModel billing) {
        boolean billRecorded = false;
        String query = "UPDATE energy_billing SET billing_system_id=?, total_saved_amount=?, bill_date=?, " +
                "user_id=?, user_full_energy=?, month=?, amount_paid=?, payment_date=?, payment_status=? " +
                "WHERE bill_id=?";
        try {
            if (con.isClosed()){
                con = DBcon();
            }
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, billing.getBillingSystemId());
            pst.setBigDecimal(2, billing.getSavedAmount());
            pst.setDate(3, new java.sql.Date(billing.getBillDate().getTime()));
            pst.setInt(4, billing.getUserId());
            pst.setBigDecimal(5, billing.getUserFullEnergy());
            pst.setString(6, billing.getMonth());
            pst.setBigDecimal(7, billing.getAmountPaid());
            pst.setDate(8, billing.getPaymentDate());
            pst.setString(9, billing.getPaymentStatus());
            pst.setInt(10, billing.getBillId());
            int row = pst.executeUpdate();
            if (row > 0){
                billRecorded = true;
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return billRecorded;
    }
    
     public boolean updateEnergyBillPaymentStatus(EnergyBillingModel billing) {
        boolean billRecorded = false;
        String query = "UPDATE energy_billing SET payment_date=?, payment_status=? WHERE user_id=?";
        try {
            if (con.isClosed()){
                con = DBcon();
            }
            PreparedStatement pst = con.prepareStatement(query);
            pst.setDate(1, billing.getPaymentDate());
            pst.setString(2, billing.getPaymentStatus());
            pst.setInt(3, billing.getUserId());
            int row = pst.executeUpdate();
            if (row > 0){
                billRecorded = true;
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return billRecorded;
    }

    // Delete operation
    public boolean deleteEnergyBilling(int billId) {
        String query = "DELETE FROM energy_billing WHERE bill_id=?";
        try {
            if (con.isClosed()){
                con = DBcon();
            }
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, billId);
            int row = pst.executeUpdate();
            if (row > 0){
                return true;
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public String getMonthForBill(String month, String year){
        switch (month){
            case "January":
                return year+"-01-31";
            case "Febrary":
                return year+"-02-29";
            case "March":
                return year+"-03-31";
            case "April":
                return year+"-04-30";
            case "May":
                return year+"-05-31";
            case "June":
                return year+"-06-30";
            case "Jully":
                return year+"-07-31";
            case "August":
                return year+"-08-31";
            case "Septmber":
                return year+"-09-30";
            case "October":
                return year+"-10-31";
            case "November":
                return year+"-11-30";
            case "December":
                return year+"-12-31";
        }
        return null;
    }
    
    //search for bill by user ID
    public boolean isCurrentBill(int userId, Date currentDate) {
        String query = "SELECT * FROM energy_billing WHERE user_id=? AND bill_date < ?";
        try {
            if (con.isClosed()){
                con = DBcon();
            }
                PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, userId);
            pst.setDate(2, currentDate);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return true;
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public EnergyBillingModel getCurrentBill(int userId, Date currentDate) {
        String query = "SELECT * FROM energy_billing WHERE user_id=? AND bill_date < ?";
        try {
            if (con.isClosed()){
                con = DBcon();
            }
                PreparedStatement pst = con.prepareStatement(query);
            
            pst.setInt(1, userId);
            pst.setDate(2, currentDate);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                EnergyBillingModel billing = new EnergyBillingModel();
                billing.setBillId(rs.getInt("bill_id"));
                billing.setBillingSystemId(rs.getInt("billing_system_id"));
                billing.setSavedAmount(rs.getBigDecimal("total_saved_amount"));
                billing.setBillDate(rs.getDate("bill_date"));
                billing.setUserId(rs.getInt("user_id"));
                billing.setUserFullEnergy(rs.getBigDecimal("user_full_energy"));
                billing.setMonth(rs.getString("month"));
                billing.setAmountPaid(rs.getBigDecimal("amount_paid"));
                billing.setPaymentDate(rs.getDate("payment_date"));
                billing.setPaymentStatus(rs.getString("payment_status"));
                return billing;
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public EnergyBillingModel getSingleBillForUser(int userId, Date currentDate) {
        String query = "SELECT * FROM energy_billing WHERE user_id = ? AND bill_date = ?";
        try {
            if (con.isClosed()){
                con = DBcon();
            }
                PreparedStatement pst = con.prepareStatement(query);
            
            pst.setInt(1, userId);
            pst.setDate(2, currentDate);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                EnergyBillingModel billing = new EnergyBillingModel();
                billing.setBillId(rs.getInt("bill_id"));
                billing.setBillingSystemId(rs.getInt("billing_system_id"));
                billing.setSavedAmount(rs.getBigDecimal("total_saved_amount"));
                billing.setBillDate(rs.getDate("bill_date"));
                billing.setUserId(rs.getInt("user_id"));
                billing.setUserFullEnergy(rs.getBigDecimal("user_full_energy"));
                billing.setMonth(rs.getString("month"));
                billing.setAmountPaid(rs.getBigDecimal("amount_paid"));
                billing.setPaymentDate(rs.getDate("payment_date"));
                billing.setPaymentStatus(rs.getString("payment_status"));
                return billing;
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Map<Integer, BigDecimal> getNoOfBillsByUserSubID(int userId, int userSubId) {
        String query = "SELECT COUNT(*) FROM energy_billing WHERE user_id=? AND billing_system_id = ?";
        Integer Count = 0;
        try {
            if (con.isClosed()){
                con = DBcon();
            }
            PreparedStatement pst = con.prepareStatement(query);
            
            pst.setInt(1, userId);
            pst.setInt(2, userSubId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                Count = rs.getInt(1);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String query2 = "SELECT * FROM energy_billing WHERE user_id=? AND billing_system_id = ?";
        BigDecimal Sum = BigDecimal.ZERO ;
        try{
            if (con.isClosed()){
                con = DBcon();
            }
            PreparedStatement pst = con.prepareStatement(query2);
            
            pst.setInt(1, userId);
            pst.setInt(2, userSubId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                BigDecimal value = rs.getBigDecimal("amount_paid");
                Sum.add(value);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Map<Integer, BigDecimal> result = new HashMap<>();
        
        result.put(Count, Sum);
        
        return result;
    }
    
    public Map<Integer, BigDecimal> getNoOfBills() {
        String query = "SELECT COUNT(*) FROM energy_billing";
        Integer Count = 0;
        try {
            if (con.isClosed()){
                con = DBcon();
            }
            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                Count = rs.getInt(1);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String query2 = "SELECT * FROM energy_billing";
        BigDecimal Sum = BigDecimal.ZERO ;
        try{
            if (con.isClosed()){
                con = DBcon();
            }
            PreparedStatement pst = con.prepareStatement(query2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                BigDecimal value = rs.getBigDecimal("amount_paid");
                Sum = Sum.add(value);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Map<Integer, BigDecimal> result = new HashMap<>();
        System.out.println("Sum from  Dao: "+Sum);
        result.put(Count, Sum);
        
        return result;
    }
    
    
    public BigDecimal getSavedAmountByAllUsers() {
        
        String query2 = "SELECT count(total_saved_amount) FROM energy_billing";
        BigDecimal Sum = BigDecimal.ZERO ;
        try{
            if (con.isClosed()){
                con = DBcon();
            }
            PreparedStatement pst = con.prepareStatement(query2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                BigDecimal value = rs.getBigDecimal(1);
                Sum.add(value);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Sum;
    }
}

