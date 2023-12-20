package com.cg.cbmapp.service;

import java.util.List;

import com.cg.cbmapp.entities.Employees;

public interface EmployeeService {

	// --------------GET(5)(Successful)--------------------------
	// Search Employee by employee Number:



	Employees getEmployeeById(Integer employeeNumber);

	// ------------------POST(1)-(Successful)--------------------
	// Add new Employee object in DB:

	Employees addEmployee(Employees employee);

	// ------------------PUT(3)-(Successful)-----------------------
	// Update the role of employee :

	Employees updateEmployeeRole(Integer employeeNumber, String role);


	// ------------------GET(6)----------------------------
	// Retrieve all employee Details

	List<Employees> getAllEmployeeDetails();

	// ------------------GET(7)----------------------------
	// Retrieve All employee details by officeId

	List<Employees> getAllEmployeeDetailsByOfficeCode(String officeCode);

	// -------------------GET(8)------------------------------------
	// Retrieve all employee details works in specific city office

	List<Employees> getAllEmployeeDetailsByCity(String city);

	// --------------------PUT(2)----------------------
	// Update employee’s reporting hierarchy

	boolean updateEmployeeReportsTo(Integer employeeNumber, Integer newManagerNumber);

	
	//----------------------PUT(4)--------------------------------------
	//Assign Office to employee:

	void assignOfficeToEmployee(String officeCode, Integer employeeNumber);
	
	
}
