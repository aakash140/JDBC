package com.java.jdbc.savepoint;

public class EmployeeRecordApp {

	public static void main(String... args){

		EmployeeDAO empDAO=new EmployeeDAO();
		empDAO.configure();
	}
}