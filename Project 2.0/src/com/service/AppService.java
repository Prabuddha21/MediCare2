package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.model.Appointment;
import com.model.Doctor;
import com.model.Payment;
import com.util.DBConnection;

public class AppService {
	
	public boolean addApp(Appointment app, Payment payment) {
	
		PayService ps = new PayService();
		
		if(ps.doPayment(payment) == true) {
		
			System.out.println("Here --");
			
			String sql = "insert into appointment(day, time, hosp, pID, dID, selectedDate,tID) values (?,?,?,?,?,?,?)";
			
			int tid = ps.getPaymentDetails(app.getPid());
			
			try {
				
				Connection con = DBConnection.getDBConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				
				con.setAutoCommit(false);
				
				pst.setInt(1, app.getDay());
				pst.setString(2, app.getTime());
				pst.setString(3, app.getHosp());
				pst.setInt(4, app.getPid());
				pst.setInt(5, app.getDid());
				
				System.out.println(app.getDate());
				
				java.sql.Date sd = new java.sql.Date(app.getDate().getTime());
				
				pst.setDate(6, sd);
				pst.setInt(7, tid);
				
				pst.execute();
				con.commit();
				
				return true;
				
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return false;
		
	}
	
	
	public ArrayList <Appointment> getApp(int userID) {
		
		ArrayList <Appointment> al = new ArrayList<>();
		
		try {
			
				String sql = "select ap.appID, ap.day, ap.time, ap.hosp, ap.pID, ap.dID, ap.appDate, ap.selectedDate from appointment ap INNER JOIN patient p on ap.pID = p.userID where p.userID = ?";
			
				Connection con = DBConnection.getDBConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, userID);
				
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					
					Appointment app = new Appointment();
					
					app.setAppID(rs.getInt(1));
					app.setDay(rs.getInt(2));
					app.setTime(rs.getString(3));
					app.setHosp(rs.getString(4));
					app.setPid(rs.getInt(5));
					app.setDid(rs.getInt(6));
					app.setDate(rs.getDate(7));
					app.setSelectedDate(rs.getDate(8));
					
					al.add(app);
				}
				
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return al;
	}
	
	
	public ArrayList <Doctor> getDoc(String name, String spec, String hosp, Date date) {
		
		ArrayList <Doctor> al = new ArrayList<>();
		
		Connection con;
		PreparedStatement pst;
		
		try {
				String sql;
				
				if(date != null) {
					
					Calendar c = Calendar.getInstance();
				    c.setTime(date);
				    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
					
					sql = "select d.name, d.userID, h.day, h.time, h.hospital, d.spec from doctor d INNER JOIN doctor_hospital h on d.userID = h.dID "
							+ "where name LIKE '%" + name + "%' and d.spec LIKE '%" + spec + "%' and h.hospital LIKE '%" + hosp + "%' and h.day = ? ;";
					
					
					
					con = DBConnection.getDBConnection();
					pst = con.prepareStatement(sql);
					
					pst.setInt(1, dayOfWeek);
					System.out.println("Here1");
				}
				else {
					
					
					
					sql = "select d.name, d.userID, h.day, h.time, h.hospital, d.spec from doctor d INNER JOIN doctor_hospital h on d.userID = h.dID "
							+ "where name LIKE '%" + name + "%' and d.spec LIKE '%" + spec + "%' and h.hospital LIKE '%" + hosp + "%' ;";
					
					con = DBConnection.getDBConnection();
					pst = con.prepareStatement(sql);
					System.out.println("Here2");
				}
				
				
				ResultSet rs = pst.executeQuery();
				
				System.out.println("Here"  + name + spec + hosp + "here");
				
				while(rs.next()) {
					
					Doctor d = new Doctor();
					
					d.setName(rs.getString(1));
					d.setUserID(rs.getInt(2));
					d.setDay(rs.getInt(3));
					d.setTime(rs.getString(4));
					d.setHosp(rs.getString(5));
					d.setSpec(rs.getString(6));
					
					al.add(d);
					
					for(int i = 1; i < 6; i++) {
						
						if(i == 3 ) {
							System.out.print(rs.getString(i));
							continue;
						}
						
						System.out.print(rs.getString(i));
					}
					System.out.println();
				}
				
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return al;
	}
	
	public String getDate(int day) {
		
		switch(day) {
			
			case 1: return "Sunday";
			case 2: return "Monday";
			case 3: return "Tuesday";
			case 4: return "Wednesday";
			case 5: return "Thursday";
			case 6: return "Friday";
			case 7: return "Saturday";
			
			default: return null;
				
		}
	
	}
	
	public long getDateDiff(Date dt, String dat) {
		
		String dateStart = "";
		String dateStop = "";
		Date d = new Date();
		
		if(dt != null) {
		
			dateStart = dt.toString();
			dateStop = new SimpleDateFormat("yyyy-MM-dd").format(d);
		}
		else {
			
			dateStart = dat;
			dateStop = new SimpleDateFormat("yyyy-MM-dd").format(d);
		}
	    
		//custom date format
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");  
		
		Date d1 = null;
		Date d2 = null;
		try {
		    d1 = f1.parse(dateStart);
		    d2 = f1.parse(dateStop);
		    
		} catch (ParseException e) {
		    e.printStackTrace();
		}    
		
		long diff = d2.getTime() - d1.getTime();
	    System.out.println ((diff / (1000*60*60*24)));
		
		return diff;
	}
	
	public int getDay(String dt) {
		
		int doW = 0;
		
		//date format
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		Date d = null;
		try {
			if(!dt.isEmpty()) {
			
				d = f.parse(dt);
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance();
	    c.setTime(d);
	    doW = c.get(Calendar.DAY_OF_WEEK);
		   
		
		return doW;
	}
	
	//Dilshan's part
	
	public ArrayList<Appointment> getAppointmentByDate(int ID) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Appointment a = new Appointment();
		ArrayList<Appointment> apparr= new ArrayList<> (); 
		try {
			
			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("select * from app where selectedDate = ? and dID = ?");
			connection.setAutoCommit(false);
			
			Date d = new Date();
			
			String date = new SimpleDateFormat("yyyy-MM-dd").format(d);
			
			preparedStatement.setString(1, date);
			preparedStatement.setInt(2, ID);
			ResultSet result = preparedStatement.executeQuery();
			connection.commit();
			while(result.next()){	
				
				a.setAppID(result.getInt(1));
				a.setDate(result.getDate(2));
				a.setHosp(result.getString(3));
				a.setTime(result.getString(4));
				a.setPid(result.getInt(5));
				a.setDid(result.getInt(6));
				a.setPname(result.getString(7));				
			
				apparr.add(a);
			}
			
		}
		catch(SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return apparr;			
		
	}
}