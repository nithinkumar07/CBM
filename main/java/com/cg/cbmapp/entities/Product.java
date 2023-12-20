package com.cg.cbmapp.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@Column(name = "productcode", length=50)
    @NotBlank(message = "Product code is required")
    @Size(max = 10, message = "Product code must be at most 10 characters")
	private String productCode;

	
	@NotBlank(message = "Product name is required")
	@Size(max = 50, message = "Product name must be at most 50 characters")
	@Column(name = "productname", length=50)
	private String productName;

	@ManyToOne
	@NotNull(message = "Product line is required")
	@JoinColumn(name = "productline", columnDefinition = "varchar(50)", nullable = false)
	private ProductsLine productLine;

	
	@NotBlank(message = "Product scale is required")
	@Column(name = "productscale", length=50, nullable = false)
	private String productScale;

	@NotBlank(message = "Product vendor is required")
	@Size(max = 50, message = "Product vendor must be at most 50 characters")
	@Column(name = "productvendor", length=50, nullable = false)
	private String productVendor;

	
	@NotBlank(message = "Product description is required")
	@Column(name = "productdescription", length=50, nullable = false)
	private String productDescription;

	
	@Positive(message = "Quantity in stock must be a positive value")
	@Column(name = "quantityinstock", nullable = false)
	private short quantityInStock;

	
    @NotNull(message = "Buy price is required")
	@Positive(message = "Buy price must be a positive value")
	@Column(name = "buyprice", columnDefinition = "decimal(10,2)", nullable = false)
	private BigDecimal buyPrice;

    
    @NotNull(message = "MSRP is required")
    @Positive(message = "MSRP must be a positive value")
	@Column(name = "MSRP", columnDefinition = "decimal(10,2)", nullable = false)
	private BigDecimal MSRP;

	public Product() {
		super();
	}

	public Product(String productCode, String productName, ProductsLine productLine, String productScale,
			String productVendor, String productDescription, short quantityInStock, BigDecimal buyPrice,
			BigDecimal mSRP) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		MSRP = mSRP;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductsLine getProductLine() {
		return productLine;
	}

	public void setProductLine(ProductsLine productLine) {
		this.productLine = productLine;
	}

	public String getProductScale() {
		return productScale;
	}

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public short getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(short quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public BigDecimal getMSRP() {
		return MSRP;
	}

	public void setMSRP(BigDecimal mSRP) {
		MSRP = mSRP;
	}

	@Override
	public String toString() {
		return "Products [productCode=" + productCode + ", productName=" + productName + ", productLine=" + productLine
				+ ", productScale=" + productScale + ", productVendor=" + productVendor + ", productDescription="
				+ productDescription + ", quantityInStock=" + quantityInStock + ", buyPrice=" + buyPrice + ", MSRP="
				+ MSRP + "]";
	}

	// Getters and setters

}