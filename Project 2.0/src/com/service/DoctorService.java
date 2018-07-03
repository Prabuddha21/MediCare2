package com.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Doctor;
import com.util.DBConnection;


public class DoctorService {
	
	private static Connection connection;
	
	private PreparedStatement preparedStatement;
	
	public Doctor getdoctorbyId(int DID) {
		
		
		Doctor doc = new Doctor();
		
		try {
			
			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("select * from doctor where userID = ?");
			connection.setAutoCommit(false);
			preparedStatement.setInt(1, DID);
			
			
			ResultSet result = preparedStatement.executeQuery();
			connection.commit();
			while(result.next()){	
				
				doc.setUserID(result.getInt(1));
				doc.setName(result.getString(4));
				doc.setNIC(result.getString(5));
				doc.setMID(result.getString(6));
				doc.setSpec(result.getString(7));
				doc.setEmail(result.getString(8));
				doc.setAdd(result.getString(10));
				doc.setAge(result.getInt(9));
				
			
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
		return doc;			
		
	}
	
	public Doctor getDocRate(int dname, String hosp, int date, String time ){
		
		Doctor doc =new Doctor();
		
		try {
			
			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement( "select price from doctor_hospital where dID = ? and hospital = ? and day = ? and time = ?");
			connection.setAutoCommit(false);
			
			preparedStatement.setInt(1, dname);
			preparedStatement.setString(2, hosp);
			preparedStatement.setInt(3, date);
			preparedStatement.setString(4, time);
			
			ResultSet result = preparedStatement.executeQuery();
			connection.commit();
			
			//doc = getdoctorbyId(dname);
			
			result.next();

			doc.setRate(result.getDouble(1));
			
		
		}catch(SQLException |ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		finally {
			
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
		return doc;
	}
	
	//Dilshan's part
	
public Doctor getDoctorByUId(String uname) {
		
		Doctor doc = new Doctor();
		System.out.println(uname);
		
		try {
			
			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("select * from doctor where uname = ?");
			
			connection.setAutoCommit(false);
			preparedStatement.setString(1, uname);
			
			
			ResultSet result = preparedStatement.executeQuery();
			connection.commit();
			while(result.next()){	
				
				doc.setUserID(result.getInt(1));
				doc.setName(result.getString(4));
				doc.setNIC(result.getString(5));
				doc.setMID(result.getString(6));
				doc.setSpec(result.getString(7));
				doc.setEmail(result.getString(8));
				doc.setAdd(result.getString(10));
				doc.setAge(result.getInt(9));
				
			
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
		return doc;			
		
	}
	public Doctor getDoctorById(int DID) {
		
		
		Doctor doc = new Doctor();
		
		try {
			
			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("select * from doctor where userID = ?");
			connection.setAutoCommit(false);
			preparedStatement.setInt(1, DID);
			
			
			ResultSet result = preparedStatement.executeQuery();
			connection.commit();
			while(result.next()){	
				
				doc.setUserID(result.getInt(1));
				doc.setName(result.getString(4));
				doc.setNIC(result.getString(5));
				doc.setMID(result.getString(6));
				doc.setSpec(result.getString(7));
				doc.setEmail(result.getString(8));
				doc.setAdd(result.getString(10));
				doc.setAge(result.getInt(9));
				
			
			}
			int dID = doc.getUserID();
			doc.setHosp1(getHosp(dID));
			ArrayList<String> arrl =doc.getHosp1();
			for(String h: arrl) {
				
				System.out.println(h);
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
		return doc;			
		
	}
	
	public ArrayList<String> getHosp(int dID){
		
		ArrayList<String> hospitals= new ArrayList<>();
		
		try {
			
			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement( "select hospital from doctor_hospital where dID = ?");
			connection.setAutoCommit(false);
			preparedStatement.setInt(1, dID);
			
			
			ResultSet result = preparedStatement.executeQuery();
			connection.commit();
					
			while(result.next()) {
				
				hospitals.add(result.getString(1));
			}
			}catch(SQLException |ClassNotFoundException e) {
			
				e.printStackTrace();
			}
			finally {
			
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
			return hospitals;
	}
	
	public ArrayList<String> getCont(int dID){
		
		ArrayList<String> contacts= new ArrayList<>();
		
		try {
			
			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement( "select phone from doctor_contact where userID = ?");
			connection.setAutoCommit(false);
			preparedStatement.setInt(1, dID);
			
			
			ResultSet result = preparedStatement.executeQuery();
			connection.commit();
					
			while(result.next()) {
				
				contacts.add(result.getString(1));
			}
			}catch(SQLException |ClassNotFoundException e) {
			
				e.printStackTrace();
			}
			finally {
			
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
			return contacts;
	}
	public void updateDoc(Doctor d) {
		
		try {

			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement( "update Doctor set name = ?, address = ?  where UserID = ?");
			connection.setAutoCommit(false);
			preparedStatement.setString(1, d.getName());
			preparedStatement.setString(2, d.getAdd());
			preparedStatement.setInt(3, d.getUserID());
			
			preparedStatement.execute();
			connection.commit();
					
		}catch(SQLException |ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		finally {
		
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
	
	}
}