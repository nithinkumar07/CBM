package com.cg.cbmapp.controller;

import java.time.LocalDate;
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
import com.cg.cbmapp.entities.Order;
import com.cg.cbmapp.entities.Product;
import com.cg.cbmapp.repository.CustomersRepository;
import com.cg.cbmapp.service.OrderService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomersRepository customersRepository;

	public OrderController(OrderService orderService, CustomersRepository customersRepository) {
		this.orderService = orderService;
		this.customersRepository = customersRepository;
	}

//------------------------------------52(Update order by shipped date)----------------------------------------------------------------------

	@PutMapping("/admin/orders/{orderNumber}/{shippedDate}")
	public ResponseEntity<String> updateOrderShippedDate(@Valid @PathVariable("orderNumber") Integer orderNumber,
			@PathVariable("shippedDate") String shippedDate) {
		orderService.updateOrderShippedDate(orderNumber, shippedDate);
		return ResponseEntity.ok("Order's shipped date updated successfully");
	}

// ------------------------------------53(Update order by order numbers)-------------------------------------------------------------------------

	@PutMapping("/admin/orders/{orderNumber}")
	public ResponseEntity<String> updateOrderStatus(@Valid @PathVariable("orderNumber") Integer orderNumber,
			@RequestBody Map<String, String> requestBody) {
		String status = requestBody.get("status");
		orderService.updateOrderStatus(orderNumber, status);
		return ResponseEntity.ok("Order status updated successfully");
	}

	// ------------------------------------54(Get all orders by Customer number)-------------------------------------------------

	@GetMapping("/customers/orders/{customerNumber}")
	public ResponseEntity<?> getAllOrdersByCustomerNumber(@PathVariable Integer customerNumber) {
		List<Order> orders = orderService.getAllOrdersByCustomerNumber(customerNumber);
		return ResponseEntity.ok(orders);
	}

	// ------------------------------------55(Get order details by order number)--------------------------------------------------

	@GetMapping("/customers/orders/order/{orderNumber}")
	public ResponseEntity<?> getOrderDetailsByOrderNumber(@PathVariable("orderNumber") Integer orderNumber) {
		Order order = orderService.getOrderDetailsByOrderNumber(orderNumber);
		if (order != null) {
			return ResponseEntity.ok(order);
		} else {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// -----------------------------------56(Get order by order date)---------------------------------------------------------------

	@GetMapping("/admin/orders/order/date/{orderDate}")
	public List<Order> getOrdersByOrderDate(@PathVariable("orderDate") String orderDate) {
		LocalDate date = LocalDate.parse(orderDate);
		return orderService.getOrdersByOrderDate(date);
	}

	// -------------------------------------57(Get orders by Required date)-----------------------------------------------------------
	@GetMapping("/admin/orders/order/required/date/{required_date}")
	public List<Order> getAllOrdersByRequiredDate(@PathVariable("required_date") String requiredDate) {
		LocalDate date = LocalDate.parse(requiredDate);
		return orderService.getAllOrdersByRequiredDate(date);
	}

	// --------------------------------------58(Get orders by Shippeddate)-----------------------------------
	@GetMapping("/admin/orders/order/shipped/date/{shipped_date}")
	public List<Order> getOrdersByShippedDate(@PathVariable("shipped_date") String shippedDate) {
		LocalDate date = LocalDate.parse(shippedDate);
		return orderService.getOrdersByShippedDate(date);
	}

	// ---------------------------------------59(Get order by status)------------------------------------------------------

	@GetMapping("/admin/orders/order/status/{status}")
	public List<Order> getOrdersByStatus(@PathVariable String status) {
		return orderService.getOrdersByStatus(status);
	}

	//----------------------------------------------(Create a order)-------------------------------------------------------------------------

		@PostMapping("/customers/orders/")
		public ResponseEntity<?> createOrder(@Valid @RequestBody Order order) {
			Order createdOrder = orderService.createOrder(order);
			if (createdOrder != null) {
				return new ResponseEntity<>("Order Created Successfully", HttpStatus.CREATED);
			} else {return new ResponseEntity<>("Failed to Create Order", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	// -----------------------------------------62(Get order by Customer number and status)----------------------------------------------------
	@GetMapping("/admin/orders/order/{customerNumber}/{status}")
	public List<Order> getOrdersByCustomerNumberAndStatus(@PathVariable("customerNumber") Integer customerNumber,
			@PathVariable("status") String status) {
		return orderService.getOrdersByCustomerNumberAndStatus(customerNumber, status);
	}

	// ------------------------------------------65(Get all Product details by order number)----------------------------------------------------
	@GetMapping("/customer/orders/{orderNumber}/products")
	public List<Product> getAllProductDetailsForOrder(@PathVariable Integer orderNumber) {
		return orderService.getAllProductDetailsForOrder(orderNumber);
	}

	// -------------------------------------------64(Get product Names for order)---------------------------------------------------

	@GetMapping("admin/orders/{orderid}/product_names")
	public List<String> getProductNamesForOrder(@PathVariable("orderid") Integer orderId) {
		return orderService.getAllProductNamesForOrder(orderId);
	}

}
