package com.ignacy.jdbc_console_strategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgreUpdateDB implements IUpdateDB {
	
	/* 
	 * @return Returns a new employeeList object - a copy of the DB
	 */
	public List<Employee> updateDB() {
		List<Employee> newEmployeeList = new ArrayList<Employee>();
		
		try {
			Class.forName(EmployeeStrategy.postgreDriver);
			Connection con = DriverManager.getConnection(EmployeeStrategy.postgreUrl, 
														EmployeeStrategy.postgreUser, 
														EmployeeStrategy.postgrePass);
			String sql = "SELECT * FROM employee";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
				
			//select one row from the sql ResultSet
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				int salary = rs.getInt(4);
				newEmployeeList.add(new Employee(id, name, age, salary));
			}
			st.close();
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
		
		System.out.println("All data from DB imported into program");
		return newEmployeeList;
	}
}
