package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.DBConnection;

public class LogoutService {

	Connection con;
	PreparedStatement pst;
	
	public void statusUpdate(String mID) {
		
		String sql = "update doctor set status = 0 where MID = ?";
		
		try {
			con = DBConnection.getDBConnection();
			pst = con.prepareStatement(sql);
			
			con.setAutoCommit(false);
			
			pst.setString(1, mID);
			
			pst.executeUpdate();
			
			pst.execute();
			con.commit();
			
		}
		catch(SQLException | ClassNotFoundException e){
			
			e.printStackTrace();
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
	}
	
}
