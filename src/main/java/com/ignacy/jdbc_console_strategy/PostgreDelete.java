package com.ignacy.jdbc_console_strategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreDelete implements IDeleteEmployee {

	public void deleteEmployee(int id) {
		
		try {
			Class.forName(EmployeeStrategy.postgreDriver);
			Connection con = DriverManager.getConnection(EmployeeStrategy.postgreUrl, 
														EmployeeStrategy.postgreUser, 
														EmployeeStrategy.postgrePass);
			String sql = "DELETE FROM employee WHERE id = " + id;
			Statement st = con.createStatement();
			
			int count = st.executeUpdate(sql); 	//DDL, DML, DQL
			System.out.println(count + "row/s affected");
			con.close();
			st.close();	
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
