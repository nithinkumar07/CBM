package com.cg.cbmapp.service;

import java.time.LocalDate;

import java.util.List;

import com.cg.cbmapp.entities.Order;

import com.cg.cbmapp.entities.Product;

public interface OrderService {

	// ----------------------------------51----------------------------------------
	Order createOrder(Order order);

	// --------------------------------------------------------------------------
	List<Order> getAllOrdersByCustomerNumber(Integer customerNumber);

	// ---------------------------55--------------------------------------

	Order getOrderDetailsByOrderNumber(Integer orderNumber);

	// ----------------------------56----------------------------------------------

	List<Order> getOrdersByOrderDate(LocalDate orderDate);

	// ----------------------------57-----------------------------------------------

	List<Order> getAllOrdersByRequiredDate(LocalDate requiredDate);

	// ------------------------------58-------------------------------------------

	List<Order> getOrdersByShippedDate(LocalDate shippedDate);

	// --------------------------------59-------------------------------------------

	List<Order> getOrdersByStatus(String status);

	// ------------------------------62------------------------------------------

	List<Order> getOrdersByCustomerNumberAndStatus(Integer customerNumber, String status);

	// --------------------------------53------------------------------------

	// for updating status and date
	Order updateOrderStatus(Integer orderNumber, String status);

	// ----------------------------------52------------------------------------------

	void updateOrderShippedDate(Integer orderNumber, String shippedDate);

	// ----------------------------------65----------------------------------------

	List<Product> getAllProductDetailsForOrder(Integer orderNumber);

	// ----------------------------------64----------------------------------------

	List<String> getAllProductNamesForOrder(Integer orderId);

}