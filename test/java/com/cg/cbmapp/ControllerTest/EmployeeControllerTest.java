package com.cg.cbmapp.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;


import java.util.ArrayList;
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


import com.cg.cbmapp.controller.EmployeeController;
import com.cg.cbmapp.entities.Employees;
import com.cg.cbmapp.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ----------------------------------GET(1)---------------------------------------
    @Test
    void testGetEmployeeById_ValidEmployeeNumber_ReturnsEmployee() {
        Integer employeeNumber = 123;
        Employees employee = new Employees();
        when(employeeService.getEmployeeById(employeeNumber)).thenReturn(employee);

        ResponseEntity<Employees> response = employeeController.getEmployeeById(employeeNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }

    @Test
    void testGetEmployeeById_EmployeeNotFound_ReturnsNotFoundResponse() {
        Integer employeeNumber = 123;
        when(employeeService.getEmployeeById(employeeNumber)).thenReturn(null);

        ResponseEntity<Employees> response = employeeController.getEmployeeById(employeeNumber);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }


    // ----------------------------------GET(4)---------------------------------------
    @Test
    void testGetAllEmployeeDetails_ReturnsListOfEmployees() {
        List<Employees> employees = new ArrayList<>();
        employees.add(new Employees());
        employees.add(new Employees());
        when(employeeService.getAllEmployeeDetails()).thenReturn(employees);

        List<Employees> response = employeeController.getAllEmployeeDetails();

        assertEquals(employees.size(), response.size());
        assertEquals(employees, response);
    }

    // ----------------------------------GET(5)---------------------------------------
    @Test
    void testGetAllEmployeeDetailsByOfficeCode_ValidOfficeCode_ReturnsListOfEmployees() {
        String officeCode = "123";
        List<Employees> employees = new ArrayList<>();
        employees.add(new Employees());
        employees.add(new Employees());
        when(employeeService.getAllEmployeeDetailsByOfficeCode(officeCode)).thenReturn(employees);

        List<Employees> response = employeeController.getAllEmployeeDetailsByOfficeCode(officeCode);

        assertEquals(employees.size(), response.size());
        assertEquals(employees, response);
    }

    // ----------------------------------GET(6)---------------------------------------
    @Test
    void testGetAllEmployeeDetailsByCity_ValidCity_ReturnsListOfEmployees() {
        String city = "New York";
        List<Employees> employees = new ArrayList<>();
        employees.add(new Employees());
        employees.add(new Employees());
        when(employeeService.getAllEmployeeDetailsByCity(city)).thenReturn(employees);

        List<Employees> response = employeeController.getAllEmployeeDetailsByCity(city);

        assertEquals(employees.size(), response.size());
        assertEquals(employees, response);
    }

    // ----------------------------------PUT(7)---------------------------------------
    @Test
    void testUpdateEmployeeReportsTo_ValidData_ReturnsOkResponse() {
        Integer employeeNumber = 123;
        Integer newManagerNumber = 456;
        when(employeeService.updateEmployeeReportsTo(employeeNumber, newManagerNumber)).thenReturn(true);

        ResponseEntity<String> response =employeeController.updateEmployeeReportsTo(employeeNumber, newManagerNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee details updated successfully.", response.getBody());
    }
//-----------------------------------------------------------------------------------
    @Test
    void testUpdateEmployeeReportsTo_EmployeeNotFound_ReturnsBadRequestResponse() {
        Integer employeeNumber = 123;
        Integer newManagerNumber = 456;
        when(employeeService.updateEmployeeReportsTo(employeeNumber, newManagerNumber)).thenReturn(false);

        ResponseEntity<String> response = employeeController.updateEmployeeReportsTo(employeeNumber, newManagerNumber);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Employee details not found.", response.getBody());
    }

    // ----------------------------------PUT(8)---------------------------------------
    @Test
    void testAssignOfficeToEmployee_ValidData_ReturnsOkResponse() {
        String officeCode = "123";
        Integer employeeNumber = 456;
        employeeService.assignOfficeToEmployee(officeCode, employeeNumber);

        ResponseEntity<String> response = employeeController.assignOfficeToEmployee(officeCode, employeeNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Office assigned to employee successfully.", response.getBody());
    }
}
