package com.service;
import com.util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


import com.model.mHistory;

import java.sql.*;

public class MServices {

	private static Connection connection;
	
	private PreparedStatement preparedStatement;
	
	public void addMedhistory(mHistory Mh) {
		
		try {
			
			mHistory M = new mHistory();
			
			M.setpid(Mh.getpName());
			M.setdName(Mh.getdName());
			M.setDesc(Mh.getDesc());
			
			
			connection = DBConnection.getDBConnection();
			
			preparedStatement = connection.prepareStatement("insert into medhistory (descrip,MID,pUName) values(?,?,?)");
			connection.setAutoCommit(false);
			
			
			preparedStatement.setString(1, M.getDesc());
			preparedStatement.setString(2, M.getdName());
			preparedStatement.setString(3, M.getpName());
			
			preparedStatement.execute();
			connection.commit();
		}catch(SQLException | ClassNotFoundException e) {
			
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
	}
	public void deleteMedHistory(mHistory mh){
		
		
		mHistory m = mh;
		
		try {
			
			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("delete from medhistory where pUName = ? and MID = ?");
			connection.setAutoCommit(false);
			
			preparedStatement.setString(1,m.getpName());
			preparedStatement.setString(2, m.getdName());
			
			preparedStatement.execute();
			connection.commit();
			
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
		
		
		
		
	}
	public mHistory viewMedicalHistorybypID(String pID, String mID){
		
		mHistory mh = new mHistory();
		
		try {
			
			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("select * from medhistory where pUName = ? and MID = ?");
			connection.setAutoCommit(false);
			
			preparedStatement.setString(1,pID);
			preparedStatement.setString(2, mID);
			
			ResultSet result = preparedStatement.executeQuery();
			connection.commit();
			if(result.next()) {
		
				mh.setpid(result.getString(1));
				mh.setdName(result.getString(2));
				mh.setDesc(result.getString(3));
				
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
		return mh;
		
	}
	public mHistory viewmedicalhistorybyID(String pID, String mID) {
		
		return actionOnMHistory(pID, mID).get(0);
	}	
	
	public ArrayList<mHistory> actionOnMHistory(String pID, String mID){
		
		ArrayList <mHistory> arrl= new ArrayList<>();
		
		try {
			
			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("select * from medhistory where pUName = ? and MID = ?");
			connection.setAutoCommit(false);
			
			preparedStatement.setString(1,pID);
			preparedStatement.setString(2, mID);
			
			ResultSet result = preparedStatement.executeQuery();
			connection.commit();
			while(result.next()) {
				
				mHistory mh = new mHistory();
				
				mh.setDesc(result.getString(3));
				mh.setpid(result.getString(1));
				mh.setdName(result.getString(2));
				arrl.add(mh);
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
		return arrl;
	}
	
	public void updateMedhistory(mHistory Mh) {
		
		mHistory M = new mHistory();
		
		M.setpid(Mh.getpName());
		
		M.setDesc(Mh.getDesc());
		
		try {
			
			connection = DBConnection.getDBConnection();
			//Add MID for doctor verification
			preparedStatement = connection.prepareStatement("update medhistory set descrip = ? where pUName = ?");
			connection.setAutoCommit(false);
		
		
			preparedStatement.setString(1, M.getDesc());
			preparedStatement.setString(2, M.getpName());
		
			preparedStatement.execute();
			connection.commit();
			
		}catch(SQLException | ClassNotFoundException e) {
		
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
	}
}	