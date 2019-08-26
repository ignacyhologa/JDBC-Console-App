package com.ignacy.jdbc_console_strategy;

import java.util.List;

public interface IUpdateDB {
	/*
	 * Ensures that all employees are added to the List<Employee> object as
	 * existing in the DB
	 */
	List<Employee> updateDB();
}
