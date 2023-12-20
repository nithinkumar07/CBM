package com.cg.cbmapp.entities;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customernumber")
	private int customerNumber;

	
	@Pattern(regexp = "[A-Za-z]{3,30}",message=" customername should be in exact Pattern")
	@Column(name = "customername", length=50, nullable = false)
	private String customerName;

	
	@Pattern(regexp = "[A-Za-z]{3,30}",message=" LastName should be in exact Pattern")
	@Column(name = "contactlastname", length=50, nullable = false)
	private String contactLastName;


	
	@Pattern(regexp = "[A-Za-z]{3,30}",message=" firstName should be in exact Pattern")
	@Column(name = "contactfirstname", length=50, nullable = false)
	private String contactFirstName;

	
	@NotBlank(message = "Phone number is required")
	@Column(name = "phone", length=50, nullable = false)
	private String phone;
	
	@NotBlank(message = "Address line 1 is required")
	@Column(name = "addressline1", length=50, nullable = false)
	private String addressLine1;
	
	@NotBlank(message = "Address line 2 is required")
	@Column(name = "addressline2", length=50)
	private String addressLine2;
	
	@NotBlank(message = "City is required")
	@Column(name = "city", length=50, nullable = false)
	private String city;

	
	@NotBlank(message = "state is required")
	@Size(max = 10, message = "State must be at most 10 characters")
	@Column(name = "state", length=50)
	private String state;
	
	@NotBlank(message = "postalcode is required")
	@Size(max = 6, message = "Postal code must not be more than 6 charecters")
	@Column(name = "postalcode", length=50)
	private String postalCode;

	@NotBlank(message = "Country is required")
	@Size(max = 10, message = "Country must be at most 10 characters")
	@Column(name = "country",length=50, nullable = false)
	private String country;

	@ManyToOne
	//@JsonIgnore
	@JoinColumn(name = "salesrepemployeenumber", referencedColumnName = "employeeNumber")
	private Employees salesRepEmployee;

	// sm
//	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
//	private List<Order> orders;

	@DecimalMax(value = "9999999.99", inclusive = true, message = "Credit limit cannot exceed 9,999,999.99")
	@Column(name = "creditlimit", columnDefinition = "decimal(10,2)")
	private BigDecimal creditLimit;

	public Customers(Integer customerNumber, String customerName, String contactLastName, String contactFirstName,
			String phone, String addressLine1, String addressLine2, String city, String state, String postalCode,
			String country, Employees salesRepEmployee, BigDecimal creditLimit) {
		super();
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.contactLastName = contactLastName;
		this.contactFirstName = contactFirstName;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.salesRepEmployee = salesRepEmployee;
		this.creditLimit = creditLimit;
	}

	public Customers() {
		// Default constructor
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Employees getSalesRepEmployee() {
		return salesRepEmployee;
	}

	public void setSalesRepEmployee(Employees salesRepEmployee) {
		this.salesRepEmployee = salesRepEmployee;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public String toString() {
		return "Customers [customerNumber=" + customerNumber + ", customerName=" + customerName + ", contactLastName="
				+ contactLastName + ", contactFirstName=" + contactFirstName + ", phone=" + phone + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + ", country=" + country + ", salesRepEmployee=" + salesRepEmployee
				+ ", creditLimit=" + creditLimit + "]";
	}

}