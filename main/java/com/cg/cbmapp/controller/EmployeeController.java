package com.cg.cbmapp.controller;

import java.net.URI;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cg.cbmapp.entities.Employees;
import com.cg.cbmapp.service.EmployeeService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// ---------------GET(5)(Successful)-----------------
	// Search Employee by employee Number:

	@GetMapping("/employee/{employeeNumber}")
	public ResponseEntity<Employees> getEmployeeById(@PathVariable Integer employeeNumber) {
		Employees employee = employeeService.getEmployeeById(employeeNumber);
		if (employee != null) {
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// ----------------POST(1)--(Successful)---------------------------------
	// Add new Employee object in DB:

	@PostMapping("/employee")
	public ResponseEntity<String> addEmployee(@Valid @RequestBody Employees employee) {
		Employees newEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<String>("Employee added",HttpStatus.OK);
	}

	// ------------------PUT(3) (Successful)-------------------------------------
	// Update the role of employee :

//	@PutMapping("/admin/employee/{role}")
//	public ResponseEntity<String> updateEmployeeRole(@Valid @PathVariable String role, @RequestBody Employees employee) {
//		Integer employeeNumber = employee.getEmployeeNumber();
//		Employees updatedEmployee = employeeService.updateEmployeeRole(employeeNumber, role);
//		if (updatedEmployee != null) {
//			return ResponseEntity.ok("Employee details successfully updated with updated role");
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee details not found");
//		}
//	}
	//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{employeeNumber}")
	//.buildAndExpand(newEmployee.getEmployeeNumber()).toUri();
//return ResponseEntity.created(location).body("Employee details added successfully");
	
    @PutMapping("/employee/{role}")
    public ResponseEntity<Employees> updateEmployeeRole(
            @Valid
            @PathVariable("role") String newRole,
            @RequestBody Employees employee
    ) {
        Employees updatedEmployee = employeeService.updateEmployeeRole(employee.getEmployeeNumber(), newRole);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
	
	
	// ----------------------GET(6)(Successful)-----------------
	// Retrieve all employee Details

	@GetMapping("/all_employee_details")
	public List<Employees> getAllEmployeeDetails() {
		return employeeService.getAllEmployeeDetails();
	}

	// ----------------------GET(7)-------------------------------
	// Retrieve All employee details by officeId

	@GetMapping("/all_employee_details/officecode/{office_code}")
	public List<Employees> getAllEmployeeDetailsByOfficeCode(@PathVariable("office_code") String officeCode) {
		return employeeService.getAllEmployeeDetailsByOfficeCode(officeCode);
	}

	// -------------------GET(8)------------------------------------
	// Retrieve all employee details works in specific city office

	@GetMapping("/all_employee_details/city/{city}")
	public List<Employees> getAllEmployeeDetailsByCity(@PathVariable("city") String city) {
		return employeeService.getAllEmployeeDetailsByCity(city);
	}

	// --------------------PUT(2)----------------------
	// Update employee’s reporting hierarchy

	@PutMapping("/{employee_no}/reports_to/{new_employee_no}")
	public ResponseEntity<String> updateEmployeeReportsTo(@Valid @PathVariable("employee_no") Integer employeeNumber,
			@PathVariable("new_employee_no") Integer newManagerNumber) {

		boolean isUpdated = employeeService.updateEmployeeReportsTo(employeeNumber, newManagerNumber);
		if (isUpdated) {
			return ResponseEntity.ok("Employee details updated successfully.");
		} else {
			return ResponseEntity.badRequest().body("Employee details not found.");
		}
	}

	// ----------------------PUT(4)--------------------------------------
	// Assign Office to employee:

	@PutMapping("/mapToOffice/{office_code}")
	public ResponseEntity<String> assignOfficeToEmployee(@Valid @PathVariable("office_code") String officeCode,
			@RequestParam("employeeNumber") Integer employeeNumber) {
		employeeService.assignOfficeToEmployee(officeCode, employeeNumber);
		return ResponseEntity.ok("Office assigned to employee successfully.");

	}

}
