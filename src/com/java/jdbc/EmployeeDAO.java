package com.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {

	private Connection connection;
	private Statement statement;
	//private int result;
	//private ResultSet rs;
	private static final String USER_NAME="jdbc";
	private static final String PASSWORD="jdbc";
	private static final String DB_URL="jdbc:oracle:thin:@172.22.22.200:1521:orcl";
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	
	public void configure(){
		try{
		Class.forName(DRIVER);
		System.out.println("Connecting to Database...");
		connection=DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		connection.setAutoCommit(false);
		String sql="CREATE TABLE EMPLOYEE("
				+ "ID NUMBER(5) PRIMARY KEY,"
				+"NAME VARCHAR2(50),"
				+"SALARY FLOAT(10))";
		System.out.println(sql);
		statement=connection.createStatement();
		statement.execute(sql);
		connection.commit();
		}
		catch(ClassNotFoundException |SQLException exception){
			exception.printStackTrace();
		}
		finally{
			
		  try{
				if(statement!=null)
					statement.close();
				if(connection!=null)
					connection.close();
			 }catch(SQLException se){
				se.printStackTrace();
				}
		}
	}
}