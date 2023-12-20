//package com.cg.cbmapp.service;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.cg.cbmapp.entities.Customers;
//import com.cg.cbmapp.entities.Office;
//import com.cg.cbmapp.entities.Payments;
//import com.cg.cbmapp.repository.CustomersRepository;
//import com.cg.cbmapp.repository.PaymentsRepository;
//
//@Service
//public class PaymentsServiceImpl implements PaymentsServices{
//
//    @Autowired
//	 private  PaymentsRepository paymentsRepository;
//    @Autowired
//    private CustomersRepository customersRepository;
//    
////    @Autowired
////    private  PaymentsRepository paymentDetailsRepository;
//
//	   
//
////	   public List<Payments> getPaymentDetailsByCustomerId(int customerId) {
////	        // Implement the logic to retrieve payment details by customer ID
////	        return paymentDetailsRepository.findByCustomerId(customerId);
////	    }
//
//	@Override
//	public List<Payments> findPaymentsByCustomerNumber(int customerNumber) {
//		return paymentsRepository.findPaymentsByCustomerNumber(customerNumber);
//	} 
//	//---------------------------GEt 50----------------------
////	@Override
////    public List<Office> getOfficesWithMaxPaymentCollection() {
////        return paymentsRepository.findOfficesWithMaxPaymentCollection();
////    }
//	//-------
//	@Override
//    public List<Payments> getPaymentsByCustomerNumber(Integer customerNumber) {
//        return paymentsRepository.findByCustomersBycustomernumber(customerNumber);
//    }
//	
//	//------------------------------------------------------------------------------------
//	
////	@Override
////    public Payments addPaymentDetails(Payments payment) {
////        Customers customer = customersRepository.findById(payment.getCustomer().getId())
////                .orElseThrow(() -> new RuntimeException("Customer not found"));
////        
////        payment.setCustomer(customer);
////        payment.setPaymentDate(LocalDate.now());
////        return paymentsRepository.save(payment);
////    }
//		
//	}
//

 

 

package com.cg.cbmapp.service;

 
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cbmapp.entities.Customers;
import com.cg.cbmapp.entities.Office;
import com.cg.cbmapp.entities.Payments;
import com.cg.cbmapp.repository.CustomersRepository;
import com.cg.cbmapp.repository.PaymentsRepository;

 

@Service
public class PaymentsServiceImpl implements PaymentsServices{

 

    @Autowired
     private  PaymentsRepository paymentsRepository;
    
    @Autowired
    private CustomersRepository customerRepository;
    

	@Override
	public Payments addPayment(Payments payments) {
		
		return paymentsRepository.save(payments);
	}
	@Override
	public List<Payments> getall() {
		List<Payments> paymentsList =paymentsRepository.findAll();
		return paymentsList;
	}


	@Override
	public Payments findByCustomerNumber(int customerNumber) {
		Payments payments =(Payments) paymentsRepository.findPaymentsByCustomerNumber(customerNumber);
		return payments;
	}

	@Override
	public List<Payments> findByPaymentDate(LocalDate paymentDate) {
		return paymentsRepository.findByPaymentDate(paymentDate);
	}

	@Override
	public Payments findByCheckNumber(String checkNumber) {
		
		 Optional<Payments> optionalPayment=paymentsRepository.findByCheckNumber(checkNumber);
		 Payments p =optionalPayment.get();
		 
		 return p;
	}

	@Override
	public Customers getCustomerByCheckNumber(String checkNumber) {
		
		Optional<Payments> op=paymentsRepository.findByCheckNumber(checkNumber);
		return op.get().getCustomer();
	}

}