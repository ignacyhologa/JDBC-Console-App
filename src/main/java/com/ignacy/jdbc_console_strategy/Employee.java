package com.ignacy.jdbc_console_strategy;
//THIS IS THE VALUE OBJECT

public class Employee {
	
	private String name;
	private int id;
	private int age;
	private int salary;

	//Create constructor for the class
	public Employee (int id, String name, int age, int salary) {
		this.age = age;
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	
	@Override
	public boolean equals(Object employee) {
		return (this.age == ((Employee) employee).getAge()
				&& this.id == ((Employee) employee).getId()
				&& this.name.equals(((Employee) employee).getName())
				&& this.salary == ((Employee) employee).getSalary());
	}
	
	//Setter and getter methods for instance variables
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getSalary() {
		return salary;
	}
}
