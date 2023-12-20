package com.cg.cbmapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.cbmapp.entities.Customers;
import com.cg.cbmapp.entities.Employees;
import com.cg.cbmapp.entities.OrderDetails;
import com.cg.cbmapp.entities.Payments;

import java.math.BigDecimal;
import java.util.*;
@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer> {

	//----------------------16---------------
	List<Customers> findByCustomerName(String customerName);
	
	//-----------17 get and 20 get -------------
	List<Customers> findByCity(String city);
	//--------------18 get by number----------
	Customers findByCustomerNumber(Integer customerNumber);
	//-----------------19----------------
	Customers findFirstBySalesRepEmployee_EmployeeNumber(Integer salesRepEmployeeNumber);
//	 Customers findByCountry(String country);
	//--------------- 21 ------------
	 List<Customers> findByCountry(String country);
	 //------------22--------------
	 Customers findByPhone(String phone);
	 //--------------------23-----------
	 List<Customers> findByContactFirstName(String contactFirstName);
	 //----------------------24---------------------------------
	 List<Customers> findByContactLastName(String contactLastName);
	 //----------------------- put 26 --------------
	 
	  @Query("select c FROM Customers c WHERE c.customerNumber = :cn AND c.customerName =:cname")
	Optional<Customers> getCustomersByCustomerNumberAndCustomerName(@Param("cn") Integer customerNumber, @Param("cname") String customerName );
	  
	  //---------------Put 27-----------------------------------------------
	  
	  @Query("select c FROM Customers c WHERE c.customerNumber = :cn AND c.contactLastName =:clname")
		Optional<Customers> getCustomersByCustomerNumberAndContactLastName(@Param("cn") Integer customerNumber, @Param("clname") String contactLastName );
	  //---------------Put ------------------
	  @Query("select c FROM Customers c WHERE c.customerNumber = :cn AND c.contactFirstName =:cfname")
		Optional<Customers> getCustomersByCustomerNumberAndContactFirsttName(@Param("cn") Integer customerNumber, @Param("cfname") String contactFirsttName );
	  //------------------------------PUT --------------------
	  @Query("select c FROM Customers c WHERE c.customerNumber = :cn")
	  Optional<Customers> getCustomers(@Param("cn") Integer customerNumber);

	  //-----------------------------------GET 29-----------------------------
	  List<Customers> findByCreditLimit(BigDecimal creditLimit);
	  //---------------------------GET 30------------------------------
	  List<Customers> findByPostalCode(String postalCode);
	  //--------------------Get 31--------------------------
	  List<Customers> findByCreditLimitBetween(BigDecimal start, BigDecimal end);
	  //---------------------33--------------------------
	  List<Customers> findByCustomerNameContainingIgnoreCase(String firstName);
	  //-------------------34--------------------------
	  List<Customers> findByCreditLimitGreaterThan(BigDecimal creditLimit);
	  //--------------------35------------------------------
	  List<Customers> findByCreditLimitLessThan(BigDecimal creditLimit);
	  //------------------------36---------------------------------
	  List<Customers> getCustomersByCreditLimitLessThan(BigDecimal creditLimit);
	  //-----------------37------------------------------------------

	  
	Customers findBySalesRepEmployee(Employees emp);
	    
	    //-------------------
//	  @Query("select c FROM customers c WHERE c.customerNumber = :cn")
//	  Customers getEmployees(@Param("cn") Integer customerNumber);
	  
	  //---------Trying-----------------------------
	 
	  
}
