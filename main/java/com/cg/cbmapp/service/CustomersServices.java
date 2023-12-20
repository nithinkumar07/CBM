package com.cg.cbmapp.service;




import java.math.BigDecimal;
import java.util.*;



import com.cg.cbmapp.entities.Customers;

import com.cg.cbmapp.entities.Payments;

public interface CustomersServices {
	//------------16-----
    List<Customers> getCustomerByName(String customerName);
    //-----15-------------------
    void addCustomer(Customers customer);
    //---------17 and 20 -----------
    List<Customers> getCustomersByCity(String city);
    
    //-------------------18------------------
    Customers findByCustomerNumber(Integer customerNumber);
    //------------------ 19 ----------------------
    Customers searchCustomersBySalesRepEmployeeNumber(Integer salesRepEmployeeNumber);
    //------------------ 21 -------
    List<Customers> getCustomersByCountry(String city);
    
    List<Customers> searchCustomersByCountry(String country);
    Customers findByPhone(String phone);
    //------------------22-----------------------------------
    List<Customers> searchCustomersByContactFirstName(String contactFirstName);
    //--------------------------24-----------------------------
    List<Customers> searchCustomersByContactLastName(String contactLastName);
//    -----------------26 put -----------------------------------
	 String updateCustomerName(Integer customerNumber, String customerName, Customers customers);
	 //------------------PUT 28------------------------------
	 String updateContactLastName(Integer customerNumber, String contactLastName, Customers customers);
	 //----------PUT 29--------------------------------------------------------
	 String updateContactFirstName(Integer customerNumber, String contactFirstName, Customers customers);
	 //-------------
	 String updateContactAddress(Integer customerNumber, Customers customers);
	 //----------------------------------------29
	 List<Customers> getCustomersByCreditLimit(BigDecimal creditLimit);
	 //------------------------------------30---------------------
	 List<Customers> getCustomersByPostalCode(String postalCode);
	 //--------------------------------31------------------
	 List<Customers> getCustomersByCreditRange(BigDecimal start, BigDecimal end);
	 //---------------------------------------32-------------------
//	 Page<Customers> getCustomersByPage(PageRequest pageRequest);
//	 ----------------------------33-------------------------
	 List<Customers> getCustomersByFirstNameLike(String firstName);
	 //---------------34----------------
	 List<Customers> getCustomersByCreditLimitGreaterThan(BigDecimal creditLimit);
	 //---------------------------------35------------------------------
	 List<Customers> getCustomersByCreditLimitLessThan(BigDecimal creditLimit);
	 //------------------------------------------36------------------------
//	 List<OrderDetails> getOrderProductStaffDetailsByCustomerId(int customerId);
	 
	 //-----------------------
	
	 	List<Payments> findPaymentsByCustomerNumber(int  customerNumber);
		List<Object> showDetails(String customerName);

         
    }
