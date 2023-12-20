
package com.cg.cbmapp.service;

import java.math.BigDecimal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.cg.cbmapp.entities.Customers;
import com.cg.cbmapp.entities.Employees;
import com.cg.cbmapp.entities.OrderDetails;
import com.cg.cbmapp.entities.Payments;
import com.cg.cbmapp.exception.CustomException;
import com.cg.cbmapp.repository.CustomersRepository;
import com.cg.cbmapp.repository.EmployeeRepository;
import com.cg.cbmapp.repository.OrderDetailsRepository;
import com.cg.cbmapp.repository.PaymentsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomersServiceImpl implements CustomersServices {

	@Autowired
	private CustomersRepository customersRepository;

	@Autowired
	private PaymentsRepository paymentsRepository;

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	@Autowired
	private EmployeeRepository employeesRepository;

	public CustomersServiceImpl(CustomersRepository customersRepository) {
		this.customersRepository = customersRepository;
	}

	@Override
	public List<Customers> getCustomerByName(String customerName) {
		List<Customers> list = customersRepository.findByCustomerName(customerName);
		if (list.size() > 0) {
			return list;
		} else {
			throw new CustomException("Customer details not found");
		}
	}

	@Override
	public void addCustomer(Customers customer) {
		System.out.println(" in service.........." + customer);
		customersRepository.save(customer);
	}

	@Override
	public List<Customers> getCustomersByCity(String city) {
		if (city == null) {
			throw new CustomException("Customer details not found");
		}
		List<Customers> findByCity = customersRepository.findByCity(city);
		return findByCity;
	}

	
	@Override
	public Customers findByCustomerNumber(Integer customerNumber) {
		Customers cust = customersRepository.findByCustomerNumber(customerNumber);
		if (cust != null) {
			return cust;
		} else {
			throw new CustomException("Customer details not found");
		}
	}

	@Override

	public Customers searchCustomersBySalesRepEmployeeNumber(Integer salesRepEmployeeNumber) {
		Employees emp = new Employees();
		emp.setEmployeeNumber(salesRepEmployeeNumber);
		Customers cust = customersRepository.findBySalesRepEmployee(emp);
		if (cust != null) {
			return cust;
		} else {
			throw new CustomException("Customer details not found");
		}

	}
	

	@Override
	public List<Customers> getCustomersByCountry(String country) {
		List<Customers> findByCountry = customersRepository.findByCountry(country);
		if (findByCountry.size() > 0) {
			return findByCountry;
		} else {
			throw new CustomException("Customer details not found");
		}
	}

	@Override
	public List<Customers> searchCustomersByCountry(String country) {
		List<Customers> findByCountry = customersRepository.findByCountry(country);
		if (findByCountry.size() > 0) {
			return findByCountry;
		} else {
			throw new CustomException("Customer details not found");
		}
	}

	@Override
	public Customers findByPhone(String phone) {
		Customers findByPhone = customersRepository.findByPhone(phone);
		if (findByPhone != null) {
			return findByPhone;
		} else {
			throw new CustomException("Customer details not found");
		}
	}

	
	@Override
	public List<Customers> searchCustomersByContactFirstName(String contactFirstName) {
		List<Customers> findByContactFirstName = customersRepository.findByContactFirstName(contactFirstName);
		if (findByContactFirstName.size() > 0) {
			return findByContactFirstName;
		} else {
			throw new CustomException("Customer details not found");
		}
	}

	
	@Override
	public List<Customers> searchCustomersByContactLastName(String contactLastName) {
		List<Customers> findByContactLastName = customersRepository.findByContactLastName(contactLastName);
		if (findByContactLastName.size() > 0) {
			return findByContactLastName;
		} else {
			throw new CustomException("Customer details not found");
		}
	}
	

	public String updateCustomerName(Integer customerNumber, String customerName, Customers customers) {
		Optional<Customers> customersOptional = customersRepository
				.getCustomersByCustomerNumberAndCustomerName(customerNumber, customerName);
		if (customersOptional.isPresent()) {
			Customers customers1 = customersOptional.get();

			customers1.setCustomerName(customers.getCustomerName());
			customersRepository.save(customers1);
			return "Name updated successfully";
		}
		throw new CustomException("Customer details not found");
	}

	
	public String updateContactLastName(Integer customerNumber, String contactLastName, Customers customers) {
		Optional<Customers> customersOptional = customersRepository
				.getCustomersByCustomerNumberAndContactLastName(customerNumber, contactLastName);
		if (customersOptional.isPresent()) {
			Customers customers1 = customersOptional.get();

			customers1.setContactLastName(customers.getContactLastName());
			customersRepository.save(customers1);
			return "Updated Customer Last Name";
		}
		throw new CustomException("Customer details not found");
	}

	
	public String updateContactFirstName(Integer customerNumber, String contactFirstName, Customers customers) {
		Optional<Customers> customersOptional = customersRepository
				.getCustomersByCustomerNumberAndContactFirsttName(customerNumber, contactFirstName);
		if (customersOptional.isPresent()) {
			Customers customers1 = customersOptional.get();

			customers1.setContactFirstName(customers.getContactFirstName());
			customersRepository.save(customers1);
			return "Updated Customer First Name";
		}
		throw new CustomException("Customer details not found");
	}

	// ----------------------------------------PUT28---------------------------------------------------
	public String updateContactAddress(Integer customerNumber, Customers customers) {
		Optional<Customers> customersOptional = customersRepository.getCustomers(customerNumber);
		if (customersOptional.isPresent()) {
			Customers customers1 = customersOptional.get();

			customers1.setAddressLine2(customers.getAddressLine2());
			customers1.setAddressLine1(customers.getAddressLine1());
			customersRepository.save(customers1);
			return "Updated Customer Contact Address";
		}
		throw new CustomException("Customer details not found");
	}

	// ---------------------------------------29------------------
	@Override
	public List<Customers> getCustomersByCreditLimit(BigDecimal creditLimit) {
		List<Customers> findByCreditLimit = customersRepository.findByCreditLimit(creditLimit);
		if (findByCreditLimit.size() > 0) {
			return findByCreditLimit;
		} else {
			throw new CustomException("Customer did not found by credit limit");
		}
	}

	// -----------------------------30-------------------------------
	@Override
	public List<Customers> getCustomersByPostalCode(String postalCode) {
		List<Customers> findByPostalCode = customersRepository.findByPostalCode(postalCode);

		if (findByPostalCode.size() > 0) {
			return findByPostalCode;
		} else {
			throw new CustomException("Customer of postal code did not found");
		}
	}

	// -------------------------31---------------------------------
	@Override
	public List<Customers> getCustomersByCreditRange(BigDecimal start, BigDecimal end) {
		List<Customers> findByCreditLimitBetween = customersRepository.findByCreditLimitBetween(start, end);
		if (findByCreditLimitBetween.size() > 0) {
			return findByCreditLimitBetween;
		} else {
			throw new CustomException("customers did not found between the given range of credit");
		}
	}

	// ---------------------------32---------------
//    @Override
//    public Page<Customers> getCustomersByPage(Pageable pageable) {
//        return customersRepository.findAll(pageable);
//    }
	// ---------------------------33-------------------------------------
	@Override
	public List<Customers> getCustomersByFirstNameLike(String firstName) {
		List<Customers> findByCustomerNameContainingIgnoreCase = customersRepository
				.findByCustomerNameContainingIgnoreCase(firstName);
		if (findByCustomerNameContainingIgnoreCase.size() > 0) {
			return findByCustomerNameContainingIgnoreCase;
		} else {
			throw new CustomException("customers by firstname did not found");
		}
	}

	// ------------------------34--------------------------
	@Override
	public List<Customers> getCustomersByCreditLimitGreaterThan(BigDecimal creditLimit) {
		List<Customers> findByCreditLimitGreaterThan = customersRepository.findByCreditLimitGreaterThan(creditLimit);
		if (findByCreditLimitGreaterThan.size() > 0) {
			return findByCreditLimitGreaterThan;
		} else {
			throw new CustomException("customers where credit limit is greater than {creditlimit} did not found");
		}
	}

	// ------------------------------------------35----------------------------
	@Override
	public List<Customers> getCustomersByCreditLimitLessThan(BigDecimal creditLimit) {
		List<Customers> findByCreditLimitLessThan = customersRepository.findByCreditLimitLessThan(creditLimit);
		if (findByCreditLimitLessThan.size() > 0) {
			return findByCreditLimitLessThan;
		} else {
			throw new CustomException("customers where credit limit is lower than {credit limit} did not found");
		}
	}

//    @Override
//    public List<Payments> findPaymentsByCustomerNumber(int customerNumber) {
//        return paymentsRepository.findByCustomerId(customerNumber);
//    }
	@Override
	public List<Payments> findPaymentsByCustomerNumber(int customerNumber) {

		List<Payments> findPaymentsByCustomerNumber = paymentsRepository.findPaymentsByCustomerNumber(customerNumber);
		if (findPaymentsByCustomerNumber.size() > 0) {
			return findPaymentsByCustomerNumber;
		} else {
			throw new CustomException("Payment by customer not found");
		}
	}

	// ------------------------------------------------------------------------------

	@Override
	public List<Object> showDetails(String customerName) {
		List<Payments> p = paymentsRepository.findPaymentsByCustomerName(customerName);
		List<OrderDetails> o = orderDetailsRepository.orderDetailsByCustomerName(customerName);

		List<Object> objectList = new ArrayList<>();
		objectList.add(p);
		objectList.add(o);

		return objectList;
	}

}