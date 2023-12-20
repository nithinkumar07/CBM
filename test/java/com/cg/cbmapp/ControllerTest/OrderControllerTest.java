package com.cg.cbmapp.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.cbmapp.controller.OrderController;
import com.cg.cbmapp.entities.Order;
import com.cg.cbmapp.entities.Product;
import com.cg.cbmapp.service.OrderService;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

	@Mock
	private OrderService orderService;

	@InjectMocks
	private OrderController orderController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetOrdersByOrderDate_ValidDate_ReturnsOrders() {
		String orderDate = "2023-07-01";
		LocalDate date = LocalDate.parse(orderDate);
		List<Order> orders = new ArrayList<>(Arrays.asList(new Order(), new Order()));
		when(orderService.getOrdersByOrderDate(date)).thenReturn(orders);

		List<Order> response = orderController.getOrdersByOrderDate(orderDate);

		assertEquals(2, response.size());
		assertEquals(orders, response);
	}
//-------------------------------------------------------------------------------------------------
	
	@Test
	void testGetAllOrdersByRequiredDate_ValidDate_ReturnsOrders() {
		String requiredDate = "2023-07-01";
		LocalDate date = LocalDate.parse(requiredDate);
		List<Order> orders = new ArrayList<>(Arrays.asList(new Order(), new Order()));
		when(orderService.getAllOrdersByRequiredDate(date)).thenReturn(orders);

		List<Order> response = orderController.getAllOrdersByRequiredDate(requiredDate);

		assertEquals(2, response.size());
		assertEquals(orders, response);
	}
//----------------------------------------------------------------------------------------------------
	@Test
	void testGetOrdersByShippedDate_ValidDate_ReturnsOrders() {
		String shippedDate = "2023-07-01";
		LocalDate date = LocalDate.parse(shippedDate);
		List<Order> orders = new ArrayList<>(Arrays.asList(new Order(), new Order()));

		when(orderService.getOrdersByShippedDate(date)).thenReturn(orders);

		List<Order> response = orderController.getOrdersByShippedDate(shippedDate);

		assertEquals(2, response.size());
		assertEquals(orders, response);
	}
//-----------------------------------------------------------------------------------------------------
	@Test
	void testGetOrdersByStatus_ValidStatus_ReturnsOrders() {
		String status = "Completed";
		List<Order> orders = new ArrayList<>(Arrays.asList(new Order(), new Order()));
		when(orderService.getOrdersByStatus(status)).thenReturn(orders);

		List<Order> response = orderController.getOrdersByStatus(status);

		assertEquals(2, response.size());
		assertEquals(orders, response);
	}
//------------------------------------------------------------------------------------------------------
	@Test
	void testGetOrdersByCustomerNumberAndStatus_ValidData_ReturnsOrders() {
		Integer customerNumber = 123;
		String status = "Pending";
		List<Order> orders = new ArrayList<>(Arrays.asList(new Order(), new Order()));
		when(orderService.getOrdersByCustomerNumberAndStatus(customerNumber, status)).thenReturn(orders);

		List<Order> response = orderController.getOrdersByCustomerNumberAndStatus(customerNumber, status);

		assertEquals(2, response.size());
		assertEquals(orders, response);
	}
//--------------------------------------------------------------------------------------------------------
	@Test
	void testUpdateOrderStatus_ValidData_ReturnsOkResponse() {
		Integer orderNumber = 1;
		String status = "Completed";
		Map<String, String> requestBody = Map.of("status", status);

		ResponseEntity<String> response = orderController.updateOrderStatus(orderNumber, requestBody);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Order status updated successfully", response.getBody());
		verify(orderService).updateOrderStatus(orderNumber, status);
	}
//----------------------------------------------------------------------------------------------------------
	@Test
	void testUpdateOrderShippedDate_ValidData_ReturnsOkResponse() {
		Integer orderNumber = 1;
		String shippedDate = "2023-07-01";

		ResponseEntity<String> response = orderController.updateOrderShippedDate(orderNumber, shippedDate);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Order's shipped date updated successfully", response.getBody());
		verify(orderService).updateOrderShippedDate(orderNumber, shippedDate);
	}
//-----------------------------------------------------------------------------------------------------------
	@Test
	void testGetAllProductDetailsForOrder_ValidOrderNumber_ReturnsProducts() {
		Integer orderNumber = 1;
		List<Product> products = new ArrayList<>(Arrays.asList(new Product(), new Product()));
		when(orderService.getAllProductDetailsForOrder(orderNumber)).thenReturn(products);

		List<Product> response = orderController.getAllProductDetailsForOrder(orderNumber);

		assertEquals(2, response.size());
		assertEquals(products, response);
	}
//----------------------------------------------------------------------------------------------------------
	@Test
	void testGetProductNamesForOrder_ValidOrderId_ReturnsProductNames() {
		Integer orderId = 1;
		List<String> productNames = Arrays.asList("Product 1", "Product 2");
		when(orderService.getAllProductNamesForOrder(orderId)).thenReturn(productNames);

		List<String> response = orderController.getProductNamesForOrder(orderId);

		assertEquals(2, response.size());
		assertEquals(productNames, response);
	}


}
