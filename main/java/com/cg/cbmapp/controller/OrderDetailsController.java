package com.cg.cbmapp.controller;

import java.math.BigDecimal;

import java.util.List;

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

import com.cg.cbmapp.entities.OrderDetails;
import com.cg.cbmapp.entities.Product;
import com.cg.cbmapp.service.OrderDetailsService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService orderDetailsService;

// --------------------------------------60(Get orderdetails with Max price)-------------------------------------------
	
	@GetMapping("/admin/orderdetails/max/price")
	public List<OrderDetails> getOrderDetailsWithMaxPrice() {
		return orderDetailsService.getOrderDetailsWithMaxPrice();
	}

//---------------------------------------61(get order count by any attribute(product code))-----------------------------------------------------

	@GetMapping("/admin/orderdetails/count/{productCode}")
	public ResponseEntity<Long> countOrderDetailsByProductCode(@PathVariable String productCode) {
		Long count = orderDetailsService.countOrderDetailsByProductCode(productCode);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

//-------------------------------------------63(Get all product)-----------------------------------------------------------

	@GetMapping("admin/orderdetails/products")
	public List<Product> getAllProductsForAllOrders() {
		return orderDetailsService.getAllProductsForAllOrders();
	}

// -----------------------------------------68(Add order details)---------------------------------------------------------------

	@PostMapping
	public ResponseEntity<String> addOrderDetails(@Valid @RequestBody OrderDetails orderDetails) {
		orderDetailsService.addOrderDetails(orderDetails);
		return new ResponseEntity<>("Record Created Successfully", HttpStatus.CREATED);
	}

// -----------------------------------------69(update quantity ordered)----------------------------------------------------

		@PutMapping("/admin/orderdetails/{order_number}/{product_code}/{quantity_ordered}")
		public OrderDetails updateQuantityOrdered(@PathVariable("order_number") Integer orderNumber,
				@Valid
				@PathVariable("product_code") String productCode,
				@PathVariable("quantity_ordered") Integer quantityOrdered) {
			OrderDetails updatedOrderDetails = orderDetailsService.updateQuantityOrdered(orderNumber, productCode,
					quantityOrdered);
			return updatedOrderDetails;
		}
		
// ----------------------------------------70(Search order details by order number)------------------------------------------------

	@GetMapping("/admin/orderdetails/{ordernumber}")
	public List<OrderDetails> searchOrderDetailsByOrderNumber(@PathVariable("ordernumber") Integer orderNumber) {
		return orderDetailsService.searchOrderDetailsByOrderNumber(orderNumber);
	}
	

// -------------------------------------------71(get total amount by order number)----------------------------------------------

	@GetMapping("/customer/orderdetails/order/{order_number}")
	public BigDecimal getTotalOrderAmount(@PathVariable("order_number") Integer orderNumber) {
		return orderDetailsService.getTotalAmountByOrderNumber(orderNumber);
	}
	
// --------------------------------------------72(Calculate total sale)-------------------------------------------------------------

		@GetMapping("/admin/orderdetails/total_sale")
		public BigDecimal getTotalSale() {
			return orderDetailsService.calculateTotalSale();
			
		}


}

// --------------------------------------------------------------------------------------------
