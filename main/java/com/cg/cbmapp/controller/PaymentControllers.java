package com.cg.cbmapp.controller;
import com.cg.cbmapp.entities.Customers;
import com.cg.cbmapp.entities.Payments;
import com.cg.cbmapp.service.PaymentsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class PaymentControllers {

	 @Autowired
    private PaymentsServices paymentsService;
    
  @PostMapping("/add")
  public ResponseEntity<Payments>addpayments(@RequestBody  Payments payments){
	 Payments addpayment= paymentsService.addPayment( payments);
	 return new ResponseEntity<Payments>(addpayment,HttpStatus.OK);
  }
  @GetMapping("/getall")
  public ResponseEntity<List<Payments>>getallpayments(){
		 List<Payments> addpayment= paymentsService.getall();
		 return new ResponseEntity<List<Payments>>(addpayment,HttpStatus.OK);
		 
  }
  
 @GetMapping("/getByCustomerNumber")
 public ResponseEntity<Payments>findByCustomerNumber(int customerNumber){
	 Payments paymentList=paymentsService.findByCustomerNumber(customerNumber);
	 return new ResponseEntity<Payments>(paymentList,HttpStatus.OK);
 }
 @GetMapping("/getBypaymentDate")
 public ResponseEntity<List<Payments>>findByPaymentDate(LocalDate paymentDate){
	 List<Payments> paymentList=paymentsService.findByPaymentDate(paymentDate);
	 return new ResponseEntity<List<Payments>>(paymentList,HttpStatus.OK);
 }
 @GetMapping("/getByCheckNumber")
 public ResponseEntity<Payments>findByCheckNumber(String checkNumber){
	 Payments paymentList=paymentsService.findByCheckNumber(checkNumber);
	 return new ResponseEntity<Payments>(paymentList,HttpStatus.OK);
 }
 @GetMapping("/getCustomerByCheckNumber")
 public ResponseEntity<Customers>getCustomerByCheckNumber(String checkNumber){
	 Customers newCustomers=paymentsService.getCustomerByCheckNumber(checkNumber);
	 return new ResponseEntity<Customers>(newCustomers,HttpStatus.OK);
 }
}
