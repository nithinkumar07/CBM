package com.cg.cbmapp.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "payments")
public class Payments {


	
	@Size(max = 50, message = "Check number must be at most 50 characters")
	@Id
	@Column(name = "checknumber", insertable = false, updatable = false, columnDefinition = "varchar(50)", nullable = false)
	private String checkNumber;

	@ManyToOne
	@JoinColumn(name = "customernumber")
	@NotNull(message = "Customer is required")
	private Customers customer;




    @NotNull(message = "Payment date is required")
    @Past(message = "Payment date must be in the past")
	@Column(name = "paymentdate", columnDefinition = "date", nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate paymentDate;

    
    
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be a positive value")
	@Column(name = "amount", columnDefinition = "decimal(10,2)", nullable = false)
	private BigDecimal amount;

	public Payments() {
		super();
	}

	public Payments(Customers customer, String checkNumber, LocalDate paymentDate, BigDecimal amount) {
		super();

		this.customer = customer;
		this.checkNumber = checkNumber;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}


	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payments [customer=" + customer + ", checkNumber=" + checkNumber + ", paymentDate=" + paymentDate
				+ ", amount=" + amount + "]";
	}

}