package com.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionPool {
	/*private static DataSource ds;
	static{
		Context initContext;
		try {
			//Below two lines are access the connection pool
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup("jdbc/photoconnpools");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/photo_db";
	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "mysql@1234";
	
	public static Connection getConnFromPool() throws SQLException{
		   Connection conn = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName(JDBC_DRIVER);
		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		     
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   return conn;
	}
}
