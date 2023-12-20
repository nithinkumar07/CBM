package com.cg.cbmapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.cbmapp.entities.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

	// -------------------GET(7)----------------------
	// Retrieve all employee details works in specific city office

	@Query("SELECT e FROM Employees e WHERE e.office.officeCode = :officeCode")
	List<Employees> findAllByOfficeCode(@Param("officeCode") String officeCode);

	// -------------------GET(8)----------------------
	// Retrieve all employee details works in specific city office

	@Query("SELECT e FROM Employees e WHERE e.office.city = :city")
	List<Employees> findAllByCity(@Param("city") String city);

	// ---------------------PUT(2)-------------------------
	
	Employees findByEmployeeNumber(Integer employeeNumber);	
	
	
	//----------------------PUT(4)--------------------------------------
	//Assign Office to employee:
	
	
	

}
