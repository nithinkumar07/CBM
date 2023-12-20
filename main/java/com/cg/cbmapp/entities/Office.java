package com.cg.cbmapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "offices")
public class Office {

	@Id
	@Column(name = "officecode", length=50, nullable = false)
	@NotBlank(message = "Office code is required")
    @Size(max = 10, message = "Office code must be at most 10 characters")
	private String officeCode;

	

    @NotBlank(message = "City is required")
    @Size(max = 10, message = "City must be at most 10 characters")
	@Column(name = "city", length=50, nullable = false)
	private String city;
    
    

    @NotBlank(message = "Phone is required")
    @Size(max = 10, message = "Phone must be at most 10 characters")
	@Column(name = "phone", length=50, nullable = false)
	private String phone;

    

    @NotBlank(message = "Address line 1 is required")
    @Size(max = 10, message = "Address line 1 must be at most 10 characters")
	@Column(name = "addressline1",length=50, nullable = false)
	private String addressLine1;

    
    @Size(max = 10, message = "Address line 2 must be at most 10 characters")
	@Column(name = "addressline2", length=50)
	private String addressLine2;


    @Size(max = 10, message = "State must be at most 10 characters")
	@Column(name = "state", length=50)
	private String state;


    @NotBlank(message = "Country is required")
    @Size(max = 10, message = "Country must be at most 10 characters")
	@Column(name = "country", length=50, nullable = false)
	private String country;
    
    @NotBlank(message = "Postal code is required")
    @Size(max = 10, message = "Postal code must be at most 10 characters")
	@Column(name = "postalcode", length=50, nullable = false)
	private String postalCode;


    @NotBlank(message = "Territory is required")
    @Size(max = 10, message = "Territory must be at most 10 characters")
	@Column(name = "territory", length=50, nullable = false)
	private String territory;

	public Office(String officeCode, String city, String phone, String addressLine1, String addressLine2, String state,
			String country, String postalCode, String territory) {
		super();
		this.officeCode = officeCode;
		this.city = city;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.territory = territory;
	}

	public Office() {
		super();
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	@Override
	public String toString() {
		return "Offices [officeCode=" + officeCode + ", city=" + city + ", phone=" + phone + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", state=" + state + ", country=" + country
				+ ", postalCode=" + postalCode + ", territory=" + territory + "]";
	}

	// Getters and setters

}