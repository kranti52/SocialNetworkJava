package com.myapp.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class DatabaseConnection {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost:3306/socialapp";
	private static final String USER = "root";
	private static final String PASS = "";   
	private static Connection conn = null;
	public Connection connect() {
		try{
			TimeZone timeZone = TimeZone.getTimeZone("Japan");
			TimeZone.setDefault(timeZone);
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;
		}catch(SQLException e){
			e.printStackTrace();
			return (Connection)e;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return (Connection)e;
		}

	}

}
