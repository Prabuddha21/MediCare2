package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Patient;
import com.model.User;
import com.util.DBConnection;

public class PatientService {
	
	public void updatePass(String email,String pass) {
		
		String sql = "update patient set pass = ? where email = ?";
		
		byte[] bpass = pass.getBytes(); 
		pass = encryptPass(bpass);
		
		try {
			
			Connection con = DBConnection.getDBConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			
			//patient pass update
			pst.setString(1, pass);
			pst.setString(2, email);
			
			pst.execute();
			
			pst.close();
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	protected String encryptPass(byte[] pass) {
		
        StringBuffer buf = new StringBuffer();
        
        for (int i = 0; i < pass.length; i++) {
        	
            int byte1 = (pass[i] >>> 4) & 0x0F;
            int half = 0;
            
            do {
            	
                if ((0 <= byte1) && (byte1 <= 9)) {
                	
                	buf.append((char) ('0' + byte1));
                }
                else {
                	
                    buf.append((char) ('a' + (byte1 - 10)));
                }
                
                byte1 = pass[i] & 0x0F;
                
            } while(half++ < 1);
        } 
        
        return buf.toString();
    }
	
	
	public Patient getPatient(int userID) {
				
		Patient p = new Patient();
		
		String sql = "select userID, uname, name, email, address, age, NIC from patient where userID = ?;";
		
		String sql1 = "select * from patient_contact where userID = ?;";
		
		try {
			
			Connection con = DBConnection.getDBConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			//PreparedStatement pst1 = con.prepareStatement(sql1);
			
			pst.setInt(1, userID);
			//pst1.setInt(1, userID);
			
			ResultSet rs = pst.executeQuery();
			//ResultSet rs1 = pst.executeQuery();
			
			rs.next();
			
			//sql
			p.setUserID(rs.getInt(1));
			p.setUname(rs.getString(2));
			p.setName(rs.getString(3));
			p.setEmail(rs.getString(4));
			p.setAdd(rs.getString(5));
			p.setAge(rs.getInt(6));
			p.setNIC(rs.getString(7));
			
			pst.close();
			con.close();
			
			con = DBConnection.getDBConnection();
			pst = con.prepareStatement(sql1);
			
			pst.setInt(1, userID);
			
			rs = pst.executeQuery();
			//sql1
			rs.next();
			p.setCont1(rs.getString(2));

			if(rs.next())
				p.setCont2(rs.getString(2));
			else 
				p.setCont2("");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return p;
		
	}

	public String[] getEventDetails() {
		
		String[] event = new String[10];
		int i = 0;
		
		String sql = "select * from event where status = 'patient'";
		
		try {
			
			Connection con = DBConnection.getDBConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				
				event[i] = rs.getString(2) + " " + rs.getString(3) + " | " + rs.getString(4);
				
				i++;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return event;
	}
	
	public boolean updatePatient(Patient patient) {
		
		String sql = "update patient set name = ?, age = ?, address = ? where userID = ?";
		
		String sql1 = "update patient_contact set phone = ? where userID = ? and phone = ?";
		
		try {
			
			Connection con = DBConnection.getDBConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			
			//patient update
			pst.setString(1, patient.getName());
			pst.setInt(2, patient.getAge());
			pst.setString(3, patient.getAdd());
			pst.setInt(4, patient.getUserID());
			
			pst.execute();
			
			pst.close();
			con.close();
			
			//Patient_contact1 update
			if(patient.getCont1() != null) {
				
				System.out.println(patient.getCont1());
				
				con = DBConnection.getDBConnection();
				pst = con.prepareStatement(sql1);
				
				pst.setString(1, patient.getCont1());
				pst.setInt(2, patient.getUserID());
				pst.setString(3, patient.getCont1o());
				
				pst.execute();
				
				pst.close();
				con.close();
			}	
			
			if(patient.getCont2() != null) {
			
				System.out.println(patient.getCont2());
				
				con = DBConnection.getDBConnection();
				pst = con.prepareStatement(sql1);
				
				pst.setString(1, patient.getCont2());
				pst.setInt(2, patient.getUserID());
				pst.setString(3, patient.getCont2o());
				
				pst.execute();
				
				pst.close();
				con.close();
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	
	//Supushpitha's part

	public int check(Patient patient) {
		
		Connection con;
		PreparedStatement pts;
		
		String sq1 = "select * from patient where uname = '"+patient.getUname()+"'or email = '"+patient.getEmail()+"'";
		
		
		try {
				con = DBConnection.getDBConnection();
				pts  = con.prepareStatement(sq1);
				
				
				ResultSet rs = pts.executeQuery(sq1);
			
				if(rs.next()) {
					
					System.out.println("fail"); 
					return 1;
			
				}
				else {
					
					System.out.println("Success");
					return 0;
				}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		return -1;
				
		
	}
		
	public void addPatient(Patient patient)
	{
			
		Connection con;
		PreparedStatement pts;
			
		String sql = "insert into patient(uname,pass,name,NIC,email,age,address) values(?,?,?,?,?,?,?)";
		
		String sql1="SELECT userID FROM patient ORDER BY userID DESC LIMIT 1";
		
		String sql2="insert into patient_contact values(?,?)";
		String sql3="insert into patient_contact values(?,?)";
		
			
		try {
			con = DBConnection.getDBConnection();
			pts = con.prepareStatement(sql);
			
			//pass encryption
			byte[] p = patient.getpass().getBytes();
			String pass = encryptPass(p);
			
			pts.setString(1, patient.getUname());
			pts.setString(2, pass);
			pts.setString(3, patient.getName());
			pts.setString(4, patient.getNIC());
			pts.setString(5, patient.getEmail());
			pts.setInt(6, patient.getAge());
			pts.setString(7, patient.getAdd());
			
			
			pts.execute();
			pts = con.prepareStatement(sql1);
			
			ResultSet rs =pts.executeQuery();
			String uid;
			String phone;
							
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User u = new LoginService().getUser(patient.getUname(), "patient");
		
		try {
			con = DBConnection.getDBConnection();
			pts = con.prepareStatement(sql2);
			
			
			pts.setInt(1, u.getUserID());
			pts.setString(2, patient.getCont1());
			
			
			pts.execute();
							
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DBConnection.getDBConnection();
			pts = con.prepareStatement(sql3);
			
			
			pts.setInt(1, u.getUserID());
			pts.setString(2, patient.getCont2());
			
			
			pts.execute();
							
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}