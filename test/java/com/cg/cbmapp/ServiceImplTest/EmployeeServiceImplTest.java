package com.cg.cbmapp.ServiceImplTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.cbmapp.entities.Employees;
import com.cg.cbmapp.repository.EmployeeRepository;
import com.cg.cbmapp.repository.OfficeRepository;
import com.cg.cbmapp.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private OfficeRepository officeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEmployeeById_found() {
        Integer employeeNumber = 123;
        Employees employee = new Employees();
        employee.setEmployeeNumber(employeeNumber);

        when(employeeRepository.findById(employeeNumber)).thenReturn(Optional.of(employee));

        Employees result = employeeService.getEmployeeById(employeeNumber);

        assertEquals(employee, result);
    }
//---------------------------------------------------------------------------------------------------------------------------
    @Test
    void testAddEmployee_success() {
        Employees employee = new Employees();

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employees result = employeeService.addEmployee(employee);

        assertEquals(employee, result);
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    void testUpdateEmployeeRole_found() {
        Integer employeeNumber = 123;
        String jobTitle = "Manager";
        Employees employee = new Employees();
        employee.setEmployeeNumber(employeeNumber);

        when(employeeRepository.findById(employeeNumber)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employees result = employeeService.updateEmployeeRole(employeeNumber, jobTitle);

        assertEquals(jobTitle, result.getJobTitle());
        verify(employeeRepository, times(1)).save(employee);
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    void testGetAllEmployeeDetails_found() {
        List<Employees> findAll = new ArrayList<>();
        findAll.add(new Employees());

        when(employeeRepository.findAll()).thenReturn(findAll);

        List<Employees> result = employeeService.getAllEmployeeDetails();

        assertEquals(findAll, result);
    }
//------------------------------------------------------------------------------------------------------------------------
    @Test
    void testGetAllEmployeeDetailsByOfficeCode_found() {
        String officeCode = "123";
        List<Employees> findAllByOfficeCode = new ArrayList<>();
        findAllByOfficeCode.add(new Employees());

        when(employeeRepository.findAllByOfficeCode(officeCode)).thenReturn(findAllByOfficeCode);

        List<Employees> result = employeeService.getAllEmployeeDetailsByOfficeCode(officeCode);

        assertEquals(findAllByOfficeCode, result);
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    void testGetAllEmployeeDetailsByCity_found() {
        String city = "New York";
        List<Employees> findAllByCity = new ArrayList<>();
        findAllByCity.add(new Employees());

        when(employeeRepository.findAllByCity(city)).thenReturn(findAllByCity);

        List<Employees> result = employeeService.getAllEmployeeDetailsByCity(city);

        assertEquals(findAllByCity, result);
    }
}
