package com.cg.cbmapp.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "productlines")
public class ProductsLine {

	@Id
	@Column(name = "productline")
	@NotBlank(message = "Product line is required")
    @Size(max = 50, message = "Product line must be at most 50 characters")
	private String productLine;

	@Size(max = 4000, message = "Text description must be at most 4000 characters")
	@Column(name = "textdescription")
	private String textDescription;

	@Column(name = "htmldescription")
	private String htmlDescription;

	
	@Column(name = "image")
	private String image;

	public ProductsLine(String productLine, String textDescription, String htmlDescription, String image) {
		super();
		this.productLine = productLine;
		this.textDescription = textDescription;
		this.htmlDescription = htmlDescription;
		this.image=image; 
	}

	public ProductsLine() {
		super();

	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getTextDescription() {
		return textDescription;
	}

	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}

	public String getHtmlDescription() {
		return htmlDescription;
	}

	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}
	public String getimage() {
		return image;
	}
	public void setimage(String image) {
		this.image = image;
	}
	

	@Override
	public String toString() {
		return "ProductsLine [productLine=" + productLine + ", textDescription=" + textDescription
				+ ", htmlDescription=" + htmlDescription + ", image=" + image + "]";
	}

	// Getters and setters

}