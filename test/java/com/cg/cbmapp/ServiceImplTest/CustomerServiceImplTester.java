package com.cg.cbmapp.ServiceImplTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.cbmapp.entities.Customers;

import com.cg.cbmapp.exception.CustomException;
import com.cg.cbmapp.repository.CustomersRepository;
import com.cg.cbmapp.service.CustomersServiceImpl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTester {

	@Mock
	private CustomersRepository customersRepository;

	@InjectMocks
	private CustomersServiceImpl customerService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetCustomerByName_found() {
		String customerName = "John Doe";
		List<Customers> customers = Collections.singletonList(new Customers());

		when(customersRepository.findByCustomerName(customerName)).thenReturn(customers);

		List<Customers> result = customerService.getCustomerByName(customerName);

		assertEquals(customers, result);
	}

	@Test
	void testAddCustomer() {
		Customers customer = new Customers();

		customerService.addCustomer(customer);

		verify(customersRepository, times(1)).save(customer);
	}

	@Test
	void testGetCustomersByCity_found() {
		String city = "New York";
		List<Customers> customers = Collections.singletonList(new Customers());

		when(customersRepository.findByCity(city)).thenReturn(customers);

		List<Customers> result = customerService.getCustomersByCity(city);

		assertEquals(customers, result);
	}


	@Test
	void testFindByCustomerNumber_found() {
		Integer customerNumber = 123;
		Customers customer = new Customers();

		when(customersRepository.findByCustomerNumber(customerNumber)).thenReturn(customer);

		Customers result = customerService.findByCustomerNumber(customerNumber);

		assertEquals(customer, result);
	}


	@Test
	void testGetCustomersByCountry_found() {
		String country = "USA";
		List<Customers> customers = Collections.singletonList(new Customers());

		when(customersRepository.findByCountry(country)).thenReturn(customers);

		List<Customers> result = customerService.getCustomersByCountry(country);

		assertEquals(customers, result);
	}

	@Test
	void testSearchCustomersByCountry_found() {
		String country = "USA";
		List<Customers> customers = Collections.singletonList(new Customers());

		when(customersRepository.findByCountry(country)).thenReturn(customers);

		List<Customers> result = customerService.searchCustomersByCountry(country);

		assertEquals(customers, result);
	}

	@Test
	void testUpdateContactAddress_found() {
		Integer customerNumber = 123;
		Customers customers = new Customers();
		customers.setAddressLine1("Address Line 1");
		customers.setAddressLine2("Address Line 2");

		Optional<Customers> customersOptional = Optional.of(new Customers());

		when(customersRepository.getCustomers(customerNumber)).thenReturn(customersOptional);

		String result = customerService.updateContactAddress(customerNumber, customers);

		assertEquals("ok", result);
		assertEquals(customers.getAddressLine1(), customersOptional.get().getAddressLine1());
		assertEquals(customers.getAddressLine2(), customersOptional.get().getAddressLine2());
		verify(customersRepository, times(1)).save(customersOptional.get());
	}

	@Test
	void testGetCustomersByCreditLimit_found() {
		BigDecimal creditLimit = new BigDecimal("1000.00");
		List<Customers> findByCreditLimit = Collections.singletonList(new Customers());

		when(customersRepository.findByCreditLimit(creditLimit)).thenReturn(findByCreditLimit);

		List<Customers> result = customerService.getCustomersByCreditLimit(creditLimit);

		assertEquals(findByCreditLimit, result);
	}

	@Test
	void testGetCustomersByPostalCode_found() {
		String postalCode = "12345";
		List<Customers> findByPostalCode = Collections.singletonList(new Customers());

		when(customersRepository.findByPostalCode(postalCode)).thenReturn(findByPostalCode);

		List<Customers> result = customerService.getCustomersByPostalCode(postalCode);

		assertEquals(findByPostalCode, result);
	}

	@Test
	void testGetCustomersByCreditRange_found() {
		BigDecimal start = new BigDecimal("1000.00");
		BigDecimal end = new BigDecimal("2000.00");
		List<Customers> findByCreditLimitBetween = Collections.singletonList(new Customers());

		when(customersRepository.findByCreditLimitBetween(start, end)).thenReturn(findByCreditLimitBetween);

		List<Customers> result = customerService.getCustomersByCreditRange(start, end);

		assertEquals(findByCreditLimitBetween, result);
	}

	@Test
	void testGetCustomersByFirstNameLike_found() {
		String firstName = "John";
		List<Customers> findByCustomerNameContainingIgnoreCase = Collections.singletonList(new Customers());

		when(customersRepository.findByCustomerNameContainingIgnoreCase(firstName))
				.thenReturn(findByCustomerNameContainingIgnoreCase);

		List<Customers> result = customerService.getCustomersByFirstNameLike(firstName);

		assertEquals(findByCustomerNameContainingIgnoreCase, result);
	}

	@Test
	void testGetCustomersByCreditLimitGreaterThan_found() {
		BigDecimal creditLimit = new BigDecimal("1000.00");
		List<Customers> findByCreditLimitGreaterThan = Collections.singletonList(new Customers());

		when(customersRepository.findByCreditLimitGreaterThan(creditLimit)).thenReturn(findByCreditLimitGreaterThan);

		List<Customers> result = customerService.getCustomersByCreditLimitGreaterThan(creditLimit);

		assertEquals(findByCreditLimitGreaterThan, result);
	}

	@Test
	void testGetCustomersByCreditLimitGreaterThan_notFound() {
		BigDecimal creditLimit = new BigDecimal("2000.00");

		when(customersRepository.findByCreditLimitGreaterThan(creditLimit)).thenReturn(Collections.emptyList());

		assertThrows(CustomException.class, () -> customerService.getCustomersByCreditLimitGreaterThan(creditLimit));
	}

	@Test
	void testGetCustomersByCreditLimitLessThan_found() {
		BigDecimal creditLimit = new BigDecimal("1000.00");
		List<Customers> findByCreditLimitLessThan = Collections.singletonList(new Customers());

		when(customersRepository.findByCreditLimitLessThan(creditLimit)).thenReturn(findByCreditLimitLessThan);

		List<Customers> result = customerService.getCustomersByCreditLimitLessThan(creditLimit);

		assertEquals(findByCreditLimitLessThan, result);
	}



}
