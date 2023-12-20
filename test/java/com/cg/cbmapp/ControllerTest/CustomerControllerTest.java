package com.cg.cbmapp.ControllerTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.cbmapp.controller.CustomerControllers;
import com.cg.cbmapp.entities.Customers;


import com.cg.cbmapp.service.CustomersServices;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomersServices customersService;

    @InjectMocks
    private CustomerControllers customersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    // -----------------------------------16-----------------------------------------
    @Test
    void testGetCustomerByName_ValidCustomerName_ReturnsCustomer() {
        String customerName = "John Doe";
        List<Customers> customers = new ArrayList<>(Arrays.asList(new Customers(), new Customers()));
        when(customersService.getCustomerByName(customerName)).thenReturn(customers);

        ResponseEntity<?> response = customersController.getCustomerByName(customerName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }


    // -----------------------------------17 and 20-----------------------------------------
    @Test
    void testGetCustomersByCity_ValidCity_ReturnsCustomers() {
        String city = "New York";
        List<Customers> customers = new ArrayList<>(Arrays.asList(new Customers(), new Customers()));
        when(customersService.getCustomersByCity(city)).thenReturn(customers);

        ResponseEntity<?> response = customersController.getCustomersByCity(city);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }



    // -----------------------------------18-----------------------------------------
    @Test
    void testGetCustomerByNumber_ValidCustomerNumber_ReturnsCustomer() {
        Integer customerNumber = 123;
        Customers customer = new Customers();
        when(customersService.findByCustomerNumber(customerNumber)).thenReturn(customer);

        ResponseEntity<?> response = customersController.getCustomerByNumber(customerNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }



    // -----------------------------------19-----------------------------------------
    @Test
    void testSearchCustomersBySalesRepEmployeeNumber_ValidData_ReturnsCustomer() {
        Integer salesRepEmployeeNumber = 456;
        Customers customer = new Customers();
        when(customersService.searchCustomersBySalesRepEmployeeNumber(salesRepEmployeeNumber)).thenReturn(customer);

        ResponseEntity<?> response = customersController.searchCustomersBySalesRepEmployeeNumber(salesRepEmployeeNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }



    // -----------------------------------29-----------------------------------------
    @Test
    void testGetCustomersByCreditLimit_ValidCreditLimit_ReturnsCustomers() {
        BigDecimal creditLimit = new BigDecimal("1000.00");
        List<Customers> customers = new ArrayList<>(Arrays.asList(new Customers(), new Customers()));
        when(customersService.getCustomersByCreditLimit(creditLimit)).thenReturn(customers);

        ResponseEntity<List<Customers>> response = customersController.getCustomersByCreditLimit(creditLimit);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    // -----------------------------------30-----------------------------------------
    @Test
    void testGetCustomersByPostalCode_ValidPostalCode_ReturnsCustomers() {
        String postalCode = "12345";
        List<Customers> customers = new ArrayList<>(Arrays.asList(new Customers(), new Customers()));
        when(customersService.getCustomersByPostalCode(postalCode)).thenReturn(customers);

        ResponseEntity<List<Customers>> response = customersController.getCustomersByPostalCode(postalCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    // -----------------------------------31-----------------------------------------
    @Test
    void testGetCustomersByCreditRange_ValidRange_ReturnsCustomers() {
        BigDecimal start = new BigDecimal("1000.00");
        BigDecimal end = new BigDecimal("2000.00");
        List<Customers> customers = new ArrayList<>(Arrays.asList(new Customers(), new Customers()));
        when(customersService.getCustomersByCreditRange(start, end)).thenReturn(customers);

        ResponseEntity<List<Customers>> response = customersController.getCustomersByCreditRange(start, end);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    // -----------------------------------34-----------------------------------------
    @Test
    void testGetCustomersByCreditLimitGreaterThan_ValidCreditLimit_ReturnsCustomers() {
        BigDecimal creditLimit = new BigDecimal("1000.00");
        List<Customers> customers = new ArrayList<>(Arrays.asList(new Customers(), new Customers()));
        when(customersService.getCustomersByCreditLimitGreaterThan(creditLimit)).thenReturn(customers);

        ResponseEntity<List<Customers>> response = customersController.getCustomersByCreditLimitGreaterThan(creditLimit);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

}