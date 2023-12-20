package com.cg.cbmapp.service;

import java.util.List;



import com.cg.cbmapp.entities.Office;

public interface OfficeServices {
	 //-----------9--------
	String addOffice(Office office);
    List<Office> getAllOffices();
    
    //-------10----------
//    String addOffice(Offices office);
	Office getOfficeByCode(String officeCode);
//	List<Offices> getAllOffices();
	
	//-------14----------------
//	Offices getOfficeByCode(String officeCode);
	List<Office> getOfficesByCities(List<String> cities);

//--------------11--------------
	
	String updateOfficePhoneNumber(String officeCode, String phone);
	void updateOffice(Office office);
//------------------------
	
//	
//    List<Customers> getAllCustomersByOfficeCode(String officeCode);
	
	
}
