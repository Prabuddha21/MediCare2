package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import com.model.Doctor;
import com.model.Payment;
import com.util.DBConnection;

public class PayService {

	Connection con = null;
	PreparedStatement pst = null;
	
	private final static double tax = 250;
	
	//checking card details 
	public boolean checkCard(String name, String cardnum, String expdate) {
        
        if(!name.isEmpty() && name != null){
            
            expdate = expdate.replace("/", "");
            
            String y = expdate.substring(2);
            String m = expdate.substring(0, 2);
            
            int year = Integer.parseInt(y);
            int month = Integer.parseInt(m);
            
            if ((cardnum.length() >= 12) && (cardnum.length() <= 16)) {   
            
                Date d = new Date();  
                
                SimpleDateFormat f1 = new SimpleDateFormat("MM");
                SimpleDateFormat f2 = new SimpleDateFormat("yy");
                
                String cm = f1.format(d);
                String cy = f2.format(d);
                
                int currentMonth = Integer.parseInt(cm);
                int currentYear = Integer.parseInt(cy);      
                
                if((year >= currentYear) && (month >= currentMonth)){    
                	
                    return true;
                }
                else{    
                    
                    return false;
                }   
            }
            else{
                
                return false;
            }
        }
        else{
            
            return false;    
        }
    }
	
	//to get the latest payment done by the current patient 
	public int getPaymentDetails(int pid) {
		
		String sql = "select transactionID from payment where pID = " + pid + " order by 1 DESC LIMIT 0 , 1";
		
		try {
			
			con = DBConnection.getDBConnection();
			pst = con.prepareStatement(sql);
			
			con.setAutoCommit(false);
			
			ResultSet rs = pst.executeQuery(sql);
			con.commit();
			
			rs.next();
			
			return rs.getInt(1);
					
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return -1;
		
	}
	
	//complete transaction
	public boolean doPayment(Payment payment) {
		
		String sql = "insert into payment(amount, pID) values (?,?)";
		
		System.out.println(payment.getAmount() + payment.getPid());
		
		try {
			
			con = DBConnection.getDBConnection();
			pst = con.prepareStatement(sql);
			
			con.setAutoCommit(false);
			
			pst.setDouble(1, payment.getAmount());
			pst.setInt(2, payment.getPid());
			
			pst.execute();
			con.commit();
			
		}
		catch(SQLException | ClassNotFoundException e){
			
			e.printStackTrace();
			return false;
		} 
		finally {
			
			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	//payment calculation
	public double getAmount(Doctor doctor) {
		
		double amount;
		
		amount = doctor.getRate() + tax;
		
		System.out.println(tax);
		
		return amount;
	}

}
