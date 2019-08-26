package com.ignacy.jdbc_console_strategy;

import java.util.Scanner;

public class App2 {
	
    public static void main( String[] args ) {
    	Scanner sc = new Scanner(System.in);
		System.out.println("JDBC Console");
    	
		//Database Selector in the beginning of the app.
		System.out.println("\n Choose the Database: \n 1. PostgreSQL \n 2. MongoDB");
		int databaseSelector = sc.nextInt();
		int operation = 0;
		EmployeeStrategy currentStrategy = null;
		
		do {
			switch(databaseSelector) {
				case 1:
					//Initialize the PostgreSQL EmployeeStrategy
					currentStrategy = new EmployeeStrategy(
															new PostgreAdd(), 
															new PostgreDelete(), 
															new PostgreUpdateEmployee(), 
															new PostgreUpdateDB());
					break;
				case 2:
					//Initialize the MongoDB EmployeeStrategy
					currentStrategy = new EmployeeStrategy(
															new MongoAdd(), 
															new MongoDelete(), 
															new MongoUpdateEmployee(), 
															new MongoUpdateDB());	
					break;
				default:
					System.out.println("Invalid Database");
			}		
			do {System.out.println("\n Choose operation:\n 0. Quit \n 1. Show Table \n "
					+ "2. Insert Row \n 3. Delete Row \n 4. Select a Table Row \n "
					+ "5. Update a row \n 6. Download data from DB \n 7.Change Database");
				operation = sc.nextInt();
			
				switch(operation) {
					case 0:
						break;
					case 1:
						currentStrategy.getAllEmployees();
						break;
					case 2: {
						//Inserts the row into Employee Object and DB
						System.out.println("Please enter the ID of the employee: ");
						int id = sc.nextInt();
						System.out.println("Please enter the name of the employee: ");
						String name = sc.next();
						System.out.println("Please enter the age of the employee: ");
						int age = sc.nextInt();
						System.out.println("Please enter the salary of the employee: ");
						int salary = sc.nextInt();
						try {
							currentStrategy.executeAddEmployee(new Employee(id, name, age, salary));
							System.out.println("The Data has been stored in the table.");
						}
						catch (Exception e) {
							System.out.println("Unknown Exception");
						}
						break;
					}
					case 3: {
						//Deletes the Employee Object and deletes row in DB
						System.out.println("Please enter the id of the employee to be deleted: ");
						int id = sc.nextInt();
						currentStrategy.executeDeleteEmployee(id);
						break;
					}
					case 4: {
						// Return the row from the List<Employee> object
						System.out.println("Please enter the row index to be returned: ");
						int index = sc.nextInt();
						currentStrategy.printEmployee(currentStrategy.getEmployee(index));
						break;
					}					
					case 5: {
						//update the employee object and updated the row in DB
						System.out.println("Please enter the row index to be updated: ");
						int index = sc.nextInt();					
						System.out.println("Please enter the id of the employee: ");
						int id = sc.nextInt();
						System.out.println("Please enter the name of the employee: ");
						String name = sc.next();
						System.out.println("Please enter the age of the employee: ");
						int age = sc.nextInt();
						System.out.println("Please enter the salary of the employee: ");
						int salary = sc.nextInt();
						currentStrategy.executeUpdateEmployee(index, new Employee(id, name, age, salary));
						break;
					}						
					case 6:
						//Download data from DB
						currentStrategy.executeUpdateDB();
						break;
					case 7:
						System.out.println("\n Choose the Database: \n 1. PostgreSQL \n 2. MongoDB");
						databaseSelector = sc.nextInt();
						break;
					default: 
						System.out.println("Operation Invalid");
				}
			} while (operation != 0);
		} while (operation != 0);
		sc.close();	
    }
}