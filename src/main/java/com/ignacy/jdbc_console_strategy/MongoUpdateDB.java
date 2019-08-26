package com.ignacy.jdbc_console_strategy;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;

public class MongoUpdateDB implements IUpdateDB {

	/* 
	 * 
	 * @return Returns a new employeeList object - a copy of the whole DB
	 */
	public List<Employee> updateDB() {
		
		//create a mongoClient object to connect to a MongoDB instance
		MongoClient mongoClient = MongoClients.create();
		
		//create a mongo database instance (immutable) and use getDatabase() to access database.
		MongoDatabase database = mongoClient.getDatabase(EmployeeStrategy.mongoDatabase);
		
		//access collection
		MongoCollection<Employee> coll = database.getCollection(EmployeeStrategy.mongoCollection);
		
		coll.find().forEach(printBlock);
		
		
		
		
		mongoClient.close();
		return null;
	}
	
	
	Block<Employee> printBlock = new Block<Employee>() {
	       @Override
	       public void apply(final Employee employee) {
	           System.out.println(employee.toJson());
	       }
	};
}
