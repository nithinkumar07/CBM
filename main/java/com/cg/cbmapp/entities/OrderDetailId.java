package com.cg.cbmapp.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Embeddable
public class OrderDetailId implements Serializable {

	@NotNull(message = "Order number is required")
	@Column(name = "ordernumber")
	private Integer orderNumber;

	
	@Size(max = 20, message = "Product code must be at most 20 characters")
	@Column(name = "productcode", length=50)
	private String productCode;

	public OrderDetailId() {
		super();
		
	}

	public OrderDetailId(Integer orderNumber, String productCode) {
		super();
		this.orderNumber = orderNumber;
		this.productCode = productCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderNumber, productCode);
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailId other = (OrderDetailId) obj;
		return orderNumber == other.orderNumber && Objects.equals(productCode, other.productCode);
	}

}