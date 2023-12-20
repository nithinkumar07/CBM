package com.cg.cbmapp.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.cg.cbmapp.entities.Customers;
import com.cg.cbmapp.entities.Office;
import com.cg.cbmapp.entities.Payments;

public interface PaymentsServices {
	
	public Payments addPayment( Payments payment);
	
	public List<Payments> getall();
	
   // public Payments updateCheckNumberOfCustomer(int customerNumber, String checkNumber, String newCheckNumber);
    
  //  public void updateAmountOfCheckNumber(int customerNumber, String checkNumber, BigDecimal amount);
    
    public Payments findByCustomerNumber(int customerNumber);
    
    List<Payments> findByPaymentDate(LocalDate paymentDate);
    
    public Payments findByCheckNumber(String checkNumber);
    
   //public BigDecimal getTotalAmountByCustomerNumber(int customerNumber);
    
    public Customers getCustomerByCheckNumber(String checkNumber);
    
   // public Customers getCustomerByMaxAmount();
    
    //public List<Payments> getCustomerByPaymentDateRange(LocalDate startpaymentDate, LocalDate endpaymentDate);
    
   // public List<Customers> getCustomerByPaymentDate(Date paymentDate);
    
   // public List<Office> getOfficeDetailsByMaxPaymentCollection();
    
   // public List<Map<String, Object>> findCustomerPaymentByCustomerNumber(int customerNumber);
    
    
    
}
