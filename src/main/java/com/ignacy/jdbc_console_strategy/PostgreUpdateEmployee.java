package com.ignacy.jdbc_console_strategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PostgreUpdateEmployee implements IUpdateEmployee {

	/*
	 * Update the object employee inside the List<Employee>
	 * @param employee the employee object to replace the existing object at index
	 * @param index index of the List<Employee> which specifies the employee to be updated
	 */
	public void updateEmployee(int index, Employee employee) {
		try {
			Class.forName(EmployeeStrategy.postgreDriver);
			Connection con = DriverManager.getConnection(EmployeeStrategy.postgreUrl, 
														EmployeeStrategy.postgreUser, 
														EmployeeStrategy.postgrePass);
			
			String sql = "UPDATE employee SET id = ?, name = ?, age = ?, salary = ? WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			
			int age = employee.getAge();
			String name = employee.getName();
			int salary = employee.getSalary();
			int id = employee.getId();
			int	oldId = index;
			
			st.setInt(1, id);
			st.setString(2, name);
			st.setInt(3, age);
			st.setInt(4, salary);
			st.setInt(5, oldId);
				
			st.executeUpdate();
			System.out.println("DB updated.");
			con.close();
			st.close();	
		} 
		catch (Exception e) {
				System.out.println(e);
		}	
	}
}
