package com.cg.cbmapp.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cbmapp.entities.Customers;
import com.cg.cbmapp.entities.Payments;
import com.cg.cbmapp.payload.ErrorResp;
import com.cg.cbmapp.repository.CustomersRepository;
import com.cg.cbmapp.service.CustomersServices;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerControllers {
	@Autowired
	private CustomersServices customersService;

	@Autowired
	private CustomersRepository customersRepository;

	// ------------------------14 post-----------------------------
	@PostMapping("/save")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customers customer) {
		System.out.println(" in post.......................");
		customersService.addCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
	}

	// --------------------------------16Get--------------------------------------------
	@GetMapping("admin/customer_name/{customerName}")
	public ResponseEntity<?> getCustomerByName(@PathVariable("customerName") String customerName) {
		List<Customers> customers = customersService.getCustomerByName(customerName);
		if (!customers.isEmpty()) {
			return ResponseEntity.ok(customers);
		} else {
			ErrorResp errorResp = new ErrorResp("Customer details not found");
			errorResp.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));

			// Create the error response JSON object
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("timeStamp", errorResp.getTimeStamp());
			errorResponse.put("message", errorResp.getMessage());
			errorResponse.put("StatusCode", HttpStatus.NOT_FOUND.value());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}

	// -----------------------------------Get 17 and 20 -------------------

	@GetMapping("admin/city/{city}")
	public ResponseEntity<?> getCustomersByCity(@PathVariable("city") String city) {
		List<Customers> customer = customersService.getCustomersByCity(city);
		if (!customer.isEmpty()) {
			return ResponseEntity.ok(customer);
		} else {
			ErrorResp errorResp = new ErrorResp("Coustmer details not found");
			errorResp.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));

			// Create the error response JSON object
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("timeStamp", errorResp.getTimeStamp());
			errorResponse.put("message", errorResp.getMessage());
			errorResponse.put("StatusCode", HttpStatus.NOT_FOUND.value());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}

	// -----------------------------Ge18-----------------------------------------------------
	@GetMapping("customers/customer_number/{customerNumber}")
	public ResponseEntity<?> getCustomerByNumber(@PathVariable("customerNumber") Integer customerNumber) {
		Customers customer = customersService.findByCustomerNumber(customerNumber);
		if (customer != null) {
			return ResponseEntity.ok(customer);
		} else {
			ErrorResp errorResp = new ErrorResp("Customer Details not found");
			errorResp.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));

			// Create the error response JSON object
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("timeStamp", errorResp.getTimeStamp());
			errorResponse.put("message", errorResp.getMessage());
			errorResponse.put("StatusCode", HttpStatus.NOT_FOUND.value());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}

	// ------------------------Get 19-------------------------------------------#
	@GetMapping("admin/sales_rep_employee_number/{salesRepEmployeeNumber}")
	public ResponseEntity<?> searchCustomersBySalesRepEmployeeNumber(
			@PathVariable("salesRepEmployeeNumber") Integer salesRepEmployeeNumber) {
		Customers customers = customersService.searchCustomersBySalesRepEmployeeNumber(salesRepEmployeeNumber);
		System.out.println(customers);
		if (customers == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Details not found");
		} else {
			// Return the customer found
			return ResponseEntity.ok(customers);
		}

	}
	// ------------------------------------it's

	@GetMapping("/country/{country}")
	public ResponseEntity<?> searchCustomersByCountry(@PathVariable String country) {
		List<Customers> customers = customersService.getCustomersByCountry(country);

		if (customers != null && !customers.isEmpty()) {
			return ResponseEntity.ok(customers);
		} else {
			ErrorResp errorResp = new ErrorResp("Customer Details not found");
			errorResp.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
			// Create the error response JSON object
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("timeStamp", errorResp.getTimeStamp());
			errorResponse.put("message", errorResp.getMessage());
			errorResponse.put("StatusCode", HttpStatus.NOT_FOUND.value());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}

	// ----------------------------Get 21-------------------
	@GetMapping("/phone/{phone}")
	public ResponseEntity<?> getCustomerByPhone(@PathVariable String phone) {
		Customers customer = customersService.findByPhone(phone);

		if (customer != null) {
			return ResponseEntity.ok(customer);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Customer not found for the phone number: " + phone);
		}
	}

	// ------------------------------------------Get 23
	@GetMapping("customers/contact_firstname/{contactFirstName}")
	public ResponseEntity<?> searchCustomersByContactFirstName(@PathVariable String contactFirstName) {
		List<Customers> customers = customersService.searchCustomersByContactFirstName(contactFirstName);

		if (customers != null) {
			return ResponseEntity.ok(customers);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Customer details not found for contact first name: " + contactFirstName);
		}
	}

	// ----------Get 24
	@GetMapping("customers/contact_lastname/{contactLastName}")
	public ResponseEntity<?> searchCustomersByContactLastName(@PathVariable String contactLastName) {
		List<Customers> customers = customersService.searchCustomersByContactLastName(contactLastName);

		if (customers != null) {
			return ResponseEntity.ok(customers);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Customer details not found for contact last name: " + contactLastName);
		}
	}
	// -------------------put 25----------------------------------

	@PutMapping("customers/customer_number/{customer_number}/customer_name/{customer_name}")
	public String updateCustomerName(@Valid @PathVariable("customer_number") Integer customerNumber,
			@PathVariable("customer_name") String customerName, @RequestBody Customers customers) {

		return customersService.updateCustomerName(customerNumber, customerName, customers);

	}
	// ---------------------Put 26---------------------------------

	@PutMapping("customers/customer_number/{customer_number}/contact_lastname/{contact_lastname}")
	public String updateContactLastName(@Valid @PathVariable("customer_number") Integer customerNumber,
			@PathVariable("contact_lastname") String contactLastName, @RequestBody Customers customers) {

		return customersService.updateContactLastName(customerNumber, contactLastName, customers);

	}
	// -----------------------PUT------------------------------------

	@PutMapping("customers/customerNumber/{customerNumber}/contactFirstName/{contactFirstName}")
	public String updateContactFirstName(@Valid @PathVariable("customerNumber") Integer customerNumber,
			@PathVariable("contactFirstName") String contactFirstName, @RequestBody Customers customers) {

		return customersService.updateContactFirstName(customerNumber, contactFirstName, customers);

	}

	// ----------------------------------PUT----------------------------------
	@PutMapping("customers/customerAddress/{customerNumber}")
	public String updateContactAddress(@Valid @PathVariable("customerNumber") Integer customerNumber,
			@RequestBody Customers customers) {

		return customersService.updateContactAddress(customerNumber, customers);

	}

	// ------------------------------------------------------------------------------29--------------
	@GetMapping("customers/credit_limit/{creditLimit}")
	public ResponseEntity<List<Customers>> getCustomersByCreditLimit(
			@PathVariable("creditLimit") BigDecimal creditLimit) {
		List<Customers> customers = customersService.getCustomersByCreditLimit(creditLimit);
		return ResponseEntity.ok(customers);
	}

	// ---------------------30------------------------------
	@GetMapping("customers/postal_code/{postalCode}")
	public ResponseEntity<List<Customers>> getCustomersByPostalCode(@PathVariable("postalCode") String postalCode) {
		List<Customers> customers = customersService.getCustomersByPostalCode(postalCode);
		return ResponseEntity.ok(customers);
	}

	// ----------------------------------------------------31------------
	@GetMapping("customers/credit_range/{start}/{end}")
	public ResponseEntity<List<Customers>> getCustomersByCreditRange(@PathVariable("start") BigDecimal start,
			@PathVariable("end") BigDecimal end) {
		List<Customers> customers = customersService.getCustomersByCreditRange(start, end);
		return ResponseEntity.ok(customers);
	}

	// --------------------------------33----------------------------
	@GetMapping("customers/getbyCustomernamelike/{fn}")
	public ResponseEntity<List<Customers>> getCustomersByFirstNameLike(@PathVariable("fn") String firstName) {
		List<Customers> customers = customersService.getCustomersByFirstNameLike(firstName);
		return ResponseEntity.ok(customers);
	}

	// --------------------34------------------------------------
	@GetMapping("customers/gt/{creditLimit}")
	public ResponseEntity<List<Customers>> getCustomersByCreditLimitGreaterThan(
			@PathVariable("creditLimit") BigDecimal creditLimit) {
		List<Customers> customers = customersService.getCustomersByCreditLimitGreaterThan(creditLimit);
		if (!customers.isEmpty()) {
			return ResponseEntity.ok(customers);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// -----------------------------------35------------------------------
	@GetMapping("/lessthan/{credit_limit}")
	public ResponseEntity<List<Customers>> getCustomersByCreditLimitLessThan(
			@PathVariable("credit_limit") BigDecimal creditLimit) {
		List<Customers> customers = customersService.getCustomersByCreditLimitLessThan(creditLimit);
		if (customers.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(customers);
	}

	// -------------------------------
	@GetMapping("/{customerNumber}/paymentdetails")
	public List<Payments> getPaymentDetailsForCustomer(@PathVariable int customerNumber) {
		return customersService.findPaymentsByCustomerNumber(customerNumber);
	}
	// ----------------------------------------------------------

	@GetMapping("/{customer_name}/order_payment_details")
	public List<Object> getDetails(@PathVariable("customer_name") String customerName) {
		return customersService.showDetails(customerName);

	}

	@GetMapping("/getAll")

	public List<Customers> getALL() {
		return customersRepository.findAll();
	}

}