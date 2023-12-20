package com.cg.cbmapp.service;

import java.time.LocalDate;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.cbmapp.entities.Order;

import com.cg.cbmapp.entities.Product;
import com.cg.cbmapp.exception.CustomException;
import com.cg.cbmapp.repository.CustomersRepository;
import com.cg.cbmapp.repository.OrderRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	@Autowired
	CustomersRepository customersRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	// --------------------------------54E---------------------------------------------------------

	@Override
	public List<Order> getAllOrdersByCustomerNumber(Integer customerNumber) {
		List<Order> allOrdersByCustomer_CustomerNumber = orderRepository
				.getAllOrdersByCustomer_CustomerNumber(customerNumber);
		if (!allOrdersByCustomer_CustomerNumber.isEmpty()) {
			return allOrdersByCustomer_CustomerNumber;
		} else {
			throw new CustomException("Customer Details not found");
		}
	}

	// -------------------------------55E-------------------------------------------------------------

	@Override
	public Order getOrderDetailsByOrderNumber(Integer orderNumber) {
		Order findByOrderNumber = orderRepository.findByOrderNumber(orderNumber);
		if (findByOrderNumber != null) {
			return findByOrderNumber;
		} else {
			throw new CustomException("Customer Details not found");
		}
	}

	// ------------------------------56---------------------------------------------------------------
	public List<Order> getOrdersByOrderDate(LocalDate orderDate) {
		List<Order> findByOrderDate = orderRepository.findByOrderDate(orderDate);
		if (!findByOrderDate.isEmpty()) {
			return findByOrderDate;
		} else {
			throw new CustomException("Customer Details not found");
		}
	}

	// -------------------------------57---------------------------------------------------------------

	@Override
	public List<Order> getAllOrdersByRequiredDate(LocalDate requiredDate) {
		List<Order> findAllByRequiredDate = orderRepository.findAllByRequiredDate(requiredDate);
		if (!findAllByRequiredDate.isEmpty()) {
			return findAllByRequiredDate;
		} else {
			throw new CustomException("Customer Details not found");
		}
	}
	// -------------------------------58---------------------------------------------------------------

	@Override
	public List<Order> getOrdersByShippedDate(LocalDate shippedDate) {
		List<Order> findByShippedDate = orderRepository.findByShippedDate(shippedDate);
		if (!findByShippedDate.isEmpty()) {
			return findByShippedDate;
		} else {
			throw new CustomException("Customer Details not found");
		}
	}

	// -------------------------------59------------------------------------------------------------

	@Override
	public List<Order> getOrdersByStatus(String status) {
		List<Order> findByStatus = orderRepository.findByStatus(status);
		if (!findByStatus.isEmpty()) {
			return findByStatus;
		} else {
			throw new CustomException("Customer Details not found");
		}
	}
	// -------------------------------62 E---------------------------------------------------------

	@Override
	public List<Order> getOrdersByCustomerNumberAndStatus(Integer customerNumber, String status) {
		List<Order> findByCustomer_CustomerNumberAndStatus = orderRepository
				.findByCustomer_CustomerNumberAndStatus(customerNumber, status);
		if (!findByCustomer_CustomerNumberAndStatus.isEmpty()) {
			return findByCustomer_CustomerNumberAndStatus;
		} else {
			throw new CustomException("Customer Details not found");
		}
	}

	// -------------------------------53 E-----------------------------------------

	// for update status and date
	public Order updateOrderStatus(Integer orderNumber, String status) {
		Order order = orderRepository.findById(orderNumber).orElse(null);

		order.setShippedDate(LocalDate.now());
		order.setStatus(status);
		Order save = orderRepository.save(order);
		if (save != null) {
			return save;
		} else {
			throw new CustomException("Customer Details not found");
		}
	}
	// --------------------------------51E--------------------------------------------------------

	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);

	}

	// -------------------------------52E-------------------------------------------

	@Override
	@Transactional
	public void updateOrderShippedDate(@Valid Integer orderNumber, String shippedDateStr) {
		Order order = orderRepository.findById(orderNumber)
				.orElseThrow(() -> new CustomException("Customer Details not found"));
		LocalDate shippedDate = LocalDate.parse(shippedDateStr);
		order.setShippedDate(shippedDate);
		orderRepository.save(order);
	}

	// -------------------------------------65E--------------------------------------------
	@Override
	public List<Product> getAllProductDetailsForOrder(Integer orderNumber) {
		List<Product> findAllProductsForOrder = orderRepository.findAllProductsForOrder(orderNumber);
		if (!findAllProductsForOrder.isEmpty()) {
			return findAllProductsForOrder;
		} else {
			throw new CustomException("Customer Details not found");
		}
	}

	// -------------------------------64E--------------------------------------------

	@Override
	public List<String> getAllProductNamesForOrder(Integer orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		return order.getOrderDetails().stream().map(orderDetail -> orderDetail.getProduct().getProductName())
				.collect(Collectors.toList());
	}



}
