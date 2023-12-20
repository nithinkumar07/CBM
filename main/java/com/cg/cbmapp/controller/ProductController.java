package com.cg.cbmapp.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cbmapp.entities.Product;
import com.cg.cbmapp.service.ProductService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@Validated
@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	// ----73 Post Add new Product object into DB-------//
	@PostMapping("/admin/products/")
	public ResponseEntity<String> addProduct(@Valid @RequestBody Product product) {
		productService.saveProduct(product);
		return new ResponseEntity<>("Product details added successfully", HttpStatus.CREATED);
	}

	// ----74 Put Update buy price of specific product----//
	@PutMapping("/admin/products/{productCode}/{buyPrice}")
	public ResponseEntity<String> updateBuyPrice(@Valid @PathVariable String productCode, @PathVariable BigDecimal buyPrice) {
		Product updatedProduct = productService.updateBuyPrice(productCode, buyPrice);
		if (updatedProduct != null) {
			return ResponseEntity.ok("Product's buy price updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Details not found");
		}
	}

	// ---- 75 Put Update Quantity in Stock for specific product----//
	@PutMapping("/admin/products/{productCode}/quantityinstocks/{quantityInStock}")
	public ResponseEntity<String> updateQuantityInStock(@Valid @PathVariable String productCode,
			@PathVariable short quantityInStock) {
		Product updatedProduct = productService.updateQuantityInStock(productCode, quantityInStock);
		if (updatedProduct != null) {
			return ResponseEntity.ok("Product's quantity updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Details not found");
		}
	}

	// ----76 Put Update MSRP in of specific product -----//
	@PutMapping("/admin/products/productcode/{productCode}/msrp/{msrp}")
	public ResponseEntity<String> updateMSRP(@Valid @PathVariable String productCode, @PathVariable BigDecimal msrp) {
		Product updatedProduct = productService.updateMSRP(productCode, msrp);
		if (updatedProduct != null) {
			return ResponseEntity.ok("Product's MSRP updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Details not found");
		}
	}

	// ----77 Put Update vendor of specific product-------//
	@PutMapping("/admin/products/{productCode}/productVendor/{productVendor}")
	public ResponseEntity<String> updateProductVendor(@Valid @PathVariable String productCode,
			@PathVariable String productVendor) {
		Product updatedProduct = productService.updateProductVendor(productCode, productVendor);
		if (updatedProduct != null) {
			return ResponseEntity.ok("Product's vendor updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Details not found");
		}
	}

	// -----78 Put Update scale of specific product--------//
	@PutMapping("/admin/products/{productCode}/productscale/{productScale}")
	public ResponseEntity<String> updateProductScale(@Valid @PathVariable String productCode,
			@PathVariable String productScale) {
		Product updatedProduct = productService.updateProductScale(productCode, productScale);
		if (updatedProduct != null) {
			return ResponseEntity.ok("Product's scale updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Details not found");
		}
	}

	// -----79 Put- Update name of specific product------//
	@PutMapping("/admin/products/{productCode}/productname/{productName}")
	public ResponseEntity<String> updateProductName(@Valid @PathVariable String productCode,
			@PathVariable String productName) {
		Product updatedProduct = productService.updateProductName(productCode, productName);
		if (updatedProduct != null) {
			return ResponseEntity.ok("Product's name updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Details not found");
		}
	}
	// -----80 get Search Product Details by product_code------// ##

	@GetMapping("/customer/products/productCode/{productCode}")
	public ResponseEntity<?> getProductByCode(@PathVariable String productCode) {
		Product product = productService.getProductByCode(productCode);
		if (product != null) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -----81 get Search Product Details by product name-------//
	@GetMapping("/customer/products/productname/{productName}")

	public ResponseEntity<?> getProductByName(@PathVariable String productName) {
		Product product = productService.getProductByName(productName);
		if (product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Details not found");
		}
	}
	// ---82 Search Product Details by product scale---//

	@GetMapping("/customer/products/{productScale}")
	public List<Product> searchByProductScale(@PathVariable("productScale") String productScale) {
		return productService.searchByProductScale(productScale);
	}

	// ---83 Search Product Details by product vendor----//
	@GetMapping("/customer/products/vendor/{productVendor}")
	public List<Product> searchByProductVendor(@PathVariable String productVendor) {
		return productService.searchByProductVendor(productVendor);
	}

	// ---85 fetch total ordered quantity for particular product----//

	@GetMapping("/customer/products/{product_code}/total_ordered_qty")
	public ResponseEntity<Integer> getTotalOrderedQuantity(@PathVariable("product_code") String productCode) {
		Integer totalOrderedQuantity = productService.getTotalOrderedQuantity(productCode);
		if (totalOrderedQuantity > 0) {
			return ResponseEntity.ok(totalOrderedQuantity);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// ---86 fetch total sale for particular product---//

	@GetMapping("/admin/products/{productCode}/total_sale")
	public BigDecimal getTotalSaleAmount(@PathVariable("productCode") String productCode) {
		return productService.getTotalSaleAmount(productCode);
	}

	// ---87 fetch total sale for each product---//

	@GetMapping("/admin/products/total_sale")
	public Map<String, BigDecimal> getTotalSaleForAllProducts() {
		return productService.getTotalSaleForAllProducts();
	}

	// ---88 show products which is highly demanded by customers---//

	@GetMapping("/admin/high-demand")
	public Product getHighDemandProduct() {
		return productService.getHighDemandProduct();
	}

	@GetMapping("/customer/products/allproduct_details")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	// --------------------------63--------------------------------------
	@GetMapping("/admin/products/byProductCode/{productCode}")
	public List<Product> getProductsByProductCode(@PathVariable String productCode) {
		return productService.getProductsByProductCode(productCode);
	}

}
