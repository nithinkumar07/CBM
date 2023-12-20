
package com.cg.cbmapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cbmapp.entities.Employees;
import com.cg.cbmapp.entities.Office;
import com.cg.cbmapp.exception.CustomException;
import com.cg.cbmapp.repository.EmployeeRepository;
import com.cg.cbmapp.repository.OfficeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private final EmployeeRepository employeeRepository;

	@Autowired
	private final OfficeRepository officeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, OfficeRepository officeRepository) {
		this.employeeRepository = employeeRepository;
		this.officeRepository = officeRepository;
	}

	// ---------------GET(5)(Successful)-----------------Search Employee by employee
	// Number:

	@Override
	public Employees getEmployeeById(Integer employeeNumber) {
		Employees emp = employeeRepository.findById(employeeNumber).orElse(null);
		if (emp == null) {
			throw new CustomException("Employee details not found");
		} else {
			return emp;
		}
	}

	// ----------------POST(1)-----------------------
	// Add new Employee object in DB

	@Override
	public Employees addEmployee(Employees employee) {
		Employees save =  employeeRepository.save(employee);
		if (save != null) {
			return save;
		} else {
			throw new CustomException("Employee details not found");
		}
	}

	// --------------PUT (3)(Successful)---------------
	// Update the role of employee

	@Override
	public Employees updateEmployeeRole(Integer employeeNumber, String jobTitle) {
		Employees employee = employeeRepository.findById(employeeNumber).orElse(null);
		if (employee != null) {
			employee.setJobTitle(jobTitle);
			return employeeRepository.save(employee);
		} else {
			throw new CustomException("Employee details not found");
		}
	}

	// --------------GET(6)----------------------------------
	// Retrieve all employee Details

	@Override
	public List<Employees> getAllEmployeeDetails() {
		List<Employees> findAll = employeeRepository.findAll();
		if (findAll.size() > 0) {
			return findAll;
		} else {
			throw new CustomException("Employee details not found");
		}
	}

	// ---------------GET(7)---------------------------------
	// Retrieve All employee details by officeId

	@Override
	public List<Employees> getAllEmployeeDetailsByOfficeCode(String officeCode) {
		List<Employees> list = employeeRepository.findAllByOfficeCode(officeCode);
		if (list.size() > 0) {
			return list;
		} else {
			throw new CustomException("Office details not found");
		}
	}

	// -------------------GET(8)------------------------------------
	// Retrieve all employee details works in specific city office

	@Override
	public List<Employees> getAllEmployeeDetailsByCity(String city) {
		List<Employees> list = employeeRepository.findAllByCity(city);
		if (list.size() > 0) {
			return list;
		} else {
			throw new CustomException("Office details not found");
		}
	}

	// --------------------PUT(2)----------------------
	// Update employee’s reporting hierarchy

	@Override
	@Transactional
	public boolean updateEmployeeReportsTo(Integer employeeNumber, Integer newManagerNumber) {
		Employees employee = employeeRepository.findByEmployeeNumber(employeeNumber);
		Employees newManager = employeeRepository.findByEmployeeNumber(newManagerNumber);

		if (employee != null && newManager != null) {
			employee.setManager(newManager);
			employeeRepository.save(employee);
			return true;
		}
		throw new CustomException("Employee details not found");
	}

	// ----------------------PUT(4)--------------------------------------
	// Assign Office to employee:

	@Override
	public void assignOfficeToEmployee(String officeCode, Integer employeeNumber) {
		Employees employee = employeeRepository.findById(employeeNumber).orElse(null);
		if (employee != null) {
			Office office = this.officeRepository.findByOfficeCode(officeCode);

			if (office == null) {
				throw new CustomException("Invalid Office Code Entered");
			}
			employee.setOffice(office);
			employeeRepository.save(employee);
		} else {
			throw new CustomException("Employee details not found");
		}
	}

}
