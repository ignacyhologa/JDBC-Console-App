package com.ignacy.jdbc_console_strategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PostgreAdd implements IAddEmployee {
	
	public void addEmployee(Employee employee) {
		
		try {
			Class.forName(EmployeeStrategy.postgreDriver);
			Connection con = DriverManager.getConnection(EmployeeStrategy.postgreUrl, 
														EmployeeStrategy.postgreUser, 
														EmployeeStrategy.postgrePass);
			String sql = "INSERT INTO employee VALUES (?,?,?,?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, employee.getId());
			st.setString(2, employee.getName());
			st.setInt(3, employee.getAge());
			st.setInt(4, employee.getSalary());
		
			int count = st.executeUpdate(); 	//DDL, DML, DQL
			System.out.println(count + "row/s affected");
			st.close();
			con.close();
			}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
