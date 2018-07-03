package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection;
	
	private DBConnection() {
		
	}
	
	public static Connection getDBConnection() throws SQLException, ClassNotFoundException {
		
		String url = "jdbc:mysql://localhost:3306/project";
		String uname = "root";
		String pass = "root";
		
		if((connection == null) || (connection.isClosed())) {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, uname, pass);
			
		}
		
		return connection;
	}
	
}
