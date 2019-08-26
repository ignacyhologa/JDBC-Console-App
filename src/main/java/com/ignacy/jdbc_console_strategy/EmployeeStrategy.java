package com.ignacy.jdbc_console_strategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeStrategy {
	
	//Database Information
	final static String postgreUrl = "jdbc:postgresql:mydb";
	final static String postgreUser = "postgres";
	final static String postgrePass = "3106";
	final static String postgreDriver = "org.postgresql.Driver";
	
	final static String mongoDatabase = "test";
	final static String mongoCollection = "employe es";
	final static String mongoPass = "";
	final static String mongoDriver = "";

	
	//instantiate the various strategy interfaces
	private IAddEmployee iAddEmployee;
	private IDeleteEmployee iDeleteEmployee;
	private IUpdateEmployee iUpdateEmployee;
	private IUpdateDB iUpdateDB;
	
	//initialize the List<Employee> object, which holds the table
	public List<Employee> employeeList;

	EmployeeStrategy(IAddEmployee iAddEmployee, 
					IDeleteEmployee iDeleteEmployee, 
					IUpdateEmployee iUpdateEmployee,
					IUpdateDB iUpdateDB) {
		
		employeeList = new ArrayList<Employee>();
		this.iAddEmployee = iAddEmployee;
		this.iDeleteEmployee = iDeleteEmployee;
		this.iUpdateEmployee = iUpdateEmployee;
		this.iUpdateDB = iUpdateDB;
		executeUpdateDB();
	}
	
	void executeAddEmployee(Employee employee) {
		employeeList.add(employee);
		this.iAddEmployee.addEmployee(employee);
	}
	
	void executeDeleteEmployee(int id) {
		Iterator<Employee> iter = employeeList.iterator();
		while (iter.hasNext()) {
			Employee employee = iter.next();
			if (employee.getId() == id) {
				iter.remove();
			}
		}
		System.out.println("Employee/s with ID: " + id + " , deleted from the Employee List.");
		this.iDeleteEmployee.deleteEmployee(id);
	}
	
	void executeUpdateEmployee(int index, Employee employee) {
		int	oldId = getEmployee(index).getId();
		
		//update the object employee
		employeeList.set(index, employee);
		System.out.println("Employee at index: " + index + ", updated in the List<Employee> object.");
		
		// Update the DB to reflect the changes
		this.iUpdateEmployee.updateEmployee(oldId, employee);
	}
	
	void executeUpdateDB() {
		List<Employee> downloadedDB = this.iUpdateDB.updateDB();
		Iterator<Employee> iter = downloadedDB.iterator();
		
		//for each row in downloadedDB check if there is a row in DAO object.
		while (iter.hasNext()) {
			Employee temp = iter.next();
			if (employeeList.contains(temp)) {
				//if list contains the object already continue.
				continue;
			}
			else {
				//if no then add the row from DB to the DAO object
				employeeList.add(temp);
			}
		}
	}
	
	/**
	 * Print all rows (employees) of the table.
	 * @return the List<Employee> of employee objects.
	 */
	List<Employee> getAllEmployees() {

		if (employeeList.size() == 0) {
			System.out.println("Table is empty");
		}
		else {
			for (Employee employee : employeeList) {
				this.printEmployee(employee);
			}	
		}
		return employeeList;
	}
	
	void printEmployee(Employee employee) {
		System.out.println("ID: " + employee.getId() + " Name: " + employee.getName() + 
				" Age: " + employee.getAge() + " Salary: " + employee.getSalary());
	}

	//Return specific Employee from the list<Employee> object
	Employee getEmployee(int index) {
		return employeeList.get(index);
	}
}
