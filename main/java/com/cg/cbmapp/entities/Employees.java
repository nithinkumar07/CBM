package com.cg.cbmapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employees {

	@Id
	@NotNull(message = "Employee number is required")
	@Column(name = "employeenumber", nullable = false)
	private Integer employeeNumber;

	@NotBlank(message = "Last name is required")
	@Size(min = 1, max = 15, message = "Last name must be between 3 and 15 characters")
	@Pattern(regexp = "[A-Za-z]{1,30}",message=" lastname should be in exact Pattern")
	@Column(name = "lastname", length=50, nullable = false)
	private String lastName;

	
	@NotBlank(message = "First name is required")
	@Size(min = 1, max = 15, message = "First name must be between 3 and 30 characters")
	@Pattern(regexp = "[A-Za-z]{1,30}",message=" firstname should be in exact Pattern")
	@Column(name = "firstname", length=50, nullable = false)
	private String firstName;
	

	
	@NotBlank(message = "Extension is required")
	@Column(name = "extension", length=50, nullable = false)
	private String extension;

	
	@NotBlank(message = "Emailid is required")
    @Email(message = "Invalid email format")
	@Column(name = "email", length=50, nullable = false)
	private String email;

	@ManyToOne
	@JoinColumn(name = "reportsto")
	private Employees manager;

	
	@NotNull(message = "Office is required")
	@ManyToOne
	@JoinColumn(name = "officecode", referencedColumnName = "officecode", nullable = false)
	private Office office;

	
	@NotBlank(message = "Job title is required")
	@Column(name = "jobtitle", length=50,insertable=false, updatable=false, nullable = false)
	private String jobTitle;
	
//	@NotBlank(message = "Role is required")
//	@Column(name = "jobtitle", length=50, nullable = false)
//	private String Role;

	public Employees() {
		super();
	}

	public Employees(Integer employeeNumber, String lastName, String firstName, String extension, String email,
			Employees manager, Office office, String jobTitle) {
		super();
		this.employeeNumber = employeeNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.extension = extension;
		this.email = email;
		this.manager = manager;
		this.office = office;
		this.jobTitle = jobTitle;
		//this.Role=role;
	}

	public Employees(Integer employeeNumber) {
		super();
		this.employeeNumber = employeeNumber;
	}

	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Employees getManager() {
		return manager;
	}

	public void setManager(Employees manager) {
		this.manager = manager;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

//	public String getRole() {
//		return Role;
//	}
//
//	public void setRole(String role) {
//		Role = role;
//	}

	@Override
	public String toString() {
		return "Employees [employeeNumber=" + employeeNumber + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", extension=" + extension + ", email=" + email + ", manager=" + manager + ", office=" + office
				+ ", jobTitle=" + jobTitle + "]";
	}

	

}
