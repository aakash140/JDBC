package com.java.jdbc.savepoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

public class EmployeeDAO {

	private Connection connection;
	private PreparedStatement statement;
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
		
		Savepoint savepoint=connection.setSavepoint("Save_Point_1");
		String sql1="Delete from Employee where id='1234'";
		statement=connection.prepareStatement(sql1);
		statement.executeUpdate();
		connection.rollback(savepoint);
		
		String sql="Update Employee set salary=? where id=?";
		System.out.println(sql);
		statement=connection.prepareStatement(sql);
		statement.setInt(1, 40000);
		statement.setInt(2, 1234);
		statement.executeUpdate();
		
		
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