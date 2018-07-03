package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Patient;
import com.model.User;
import com.util.DBConnection;

public class LoginService {

	private static Connection con;
	private static PreparedStatement pst;
	
	public LoginService() {
		
	}
	
	public boolean checkPatient(String uname, String pass) {
		
		//patients query
		String sql = "Select userID, pass from patient where email = ? or uname = ?";
		
		//checking credentials 
		
		try {
			con = DBConnection.getDBConnection();
			
			pst = con.prepareStatement(sql);
			
			pst.setString(1, uname);
			pst.setString(2, uname);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
				PatientService ps = new PatientService();
				
				byte[] bytepass = pass.getBytes();
				String p = ps.encryptPass(bytepass);
				
				if(rs.getString(2).equals(p)) {
					
					return true; //return all the details needed
				}
				else {
					
					return false;
				}
			}
			else {
				
				return false;
			}
				
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean checkDoctor(String uname, String pass) {
		
		//doctors query
		String sql = "Select userID, pass from doctor where email = ? or uname = ?";
		
		//checking credentials 
		
		try {
			con = DBConnection.getDBConnection();
			
			pst = con.prepareStatement(sql);
			
			pst.setString(1, uname);
			pst.setString(2, uname);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
				//temporary password validation
				if(rs.getString(2).equals(pass)) {
					
					return true; //return all the details needed
				}
				else {
					
					return false;
				}
			}
			else {
				
				return false;
			}
				
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}

	public User getUser(String uname, String type) {
		
		
		
		String sql1 = "Select userID, uname, name, email, NIC  from patient where email = ? or uname = ?";
		String sql2 = "Select userID, uname, name, email, NIC, MID from doctor where email = ? or uname = ?";
		
		try {
			
			if(type.equals("patient")) {
				
				con = DBConnection.getDBConnection();
				
				pst = con.prepareStatement(sql1);
				
				pst.setString(1, uname);
				pst.setString(2, uname);
				
				ResultSet rs = pst.executeQuery();
				rs.next();
					
				Patient user = new Patient();
				
				user.setUserID(rs.getInt(1));
				user.setUname(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setNIC(rs.getString(5));
				
				return user;
			}
			else if(type.equals("doctor")) {
			
				pst = con.prepareStatement(sql2);
			
				pst.setString(1, uname);
				pst.setString(2, uname);
			
				ResultSet rs = pst.executeQuery();
				rs.next();
				
				//doctor
			}
			else {
				
				return null;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
