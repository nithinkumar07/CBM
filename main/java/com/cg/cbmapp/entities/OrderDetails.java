package com.cg.cbmapp.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {

	@EmbeddedId
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private OrderDetailId id;
	
	
//	@Column(name = "order_id", nullable = false)
//    private Integer orderId;

	@ManyToOne
	@JsonIgnore
	@MapsId("ordernumber")
	@JoinColumn(name = "ordernumber", nullable = false)
	private Order order;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "productcode",insertable=false, updatable=false,  nullable = false, columnDefinition = "varchar(15)")
	private Product product;

	
	@NotNull(message = "Quantity ordered is required")
    @Min(value = 1, message = "Quantity ordered must be at least 1")
	@Column(name = "quantityordered", nullable = false)
	private Integer quantityOrdered;

	
	@NotNull(message = "Price each is required")
	@Column(name = "priceeach", nullable = false, columnDefinition = "decimal(10,2)")
	private BigDecimal priceEach;

	
	@NotNull(message = "Order line number is required")
	@Column(name = "orderlinenumber", nullable = false)
	private short orderLineNumber;

	public OrderDetails() {
		super();
	}

	public OrderDetails(Order order, Product product, Integer quantityOrdered, BigDecimal priceEach,
			short orderLineNumber) {
		super();
		this.order = order;
		this.product = product;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}

	public OrderDetailId getId() {
		return id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(BigDecimal priceEach) {
		this.priceEach = priceEach;
	}

	public short getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(short orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}

	@Override
	public String toString() {
		return "OrderDetails [ order=" + order + ", product=" + product + ", quantityOrdered=" + quantityOrdered
				+ ", priceEach=" + priceEach + ", orderLineNumber=" + orderLineNumber + "]";
	}

}