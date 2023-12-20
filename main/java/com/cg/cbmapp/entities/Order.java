package com.cg.cbmapp.entities;

import java.time.LocalDate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordernumber")
	private Integer orderNumber;

	
	@NotNull(message = "Order date is required")
	@Past(message = "Order date must be in the past")
	@Column(name = "orderdate", nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate orderDate;


    @NotNull(message = "Required date is required")
    @Past(message = "Required date must be in the past")
	@Column(name = "requireddate", nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate requiredDate;

	@Column(name = "shippeddate")
	 @Past(message = "Shipped date must be in the past")
	@Temporal(TemporalType.DATE)
	private LocalDate shippedDate;

	@Column(name = "status", length=50, nullable = false)
	@NotBlank(message = "Status is required")
    @Size(max = 50, message = "Status must be at most 50 characters")
	private String status;

	
	@Size(max = 50, message = "Comments must be at most 50 characters")
	@Column(name = "comments", length=50)
	private String comments;

	@ManyToOne
	@JoinColumn(name = "customernumber", nullable = false)
	@NotNull(message = "Customer is required")
	private Customers customer;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<OrderDetails> orderDetails;

	public Order(Integer orderNumber, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String status,
			String comments, Customers customer, List<OrderDetails> orderDetails) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.status = status;
		this.comments = comments;
		this.customer = customer;
		this.orderDetails = orderDetails;
	}


	public Order() {

	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(LocalDate requiredDate) {
		this.requiredDate = requiredDate;
	}

	public LocalDate getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "Orders [orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", requiredDate=" + requiredDate
				+ ", shippedDate=" + shippedDate + ", status=" + status + ", comments=" + comments + ", customer="
				+ customer + ", orderDetails=" + orderDetails + "]";
	}

}
