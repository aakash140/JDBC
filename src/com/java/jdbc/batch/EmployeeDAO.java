package com.java.jdbc.batch;

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
		statement=connection.createStatement();
		
		String sql="Delete from Employee where id=1235";
		System.out.println(sql);
		statement.addBatch(sql);
		
		String sql1="Update Employee set salary=50000 where id=1234";
		System.out.println(sql1);
		statement.addBatch(sql1);
		
		String sql2="Update Employee set name='Sameer' where id='1238'";
		System.out.println(sql2);
		statement.addBatch(sql2);
		
		statement.executeBatch();
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