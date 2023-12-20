
package com.cg.cbmapp.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cbmapp.entities.OrderDetails;
import com.cg.cbmapp.entities.OrderDetailId;
import com.cg.cbmapp.entities.Product;
import com.cg.cbmapp.exception.CustomException;
import com.cg.cbmapp.repository.OrderDetailsRepository;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	// ----------------------------------------60--------------------------------------------
	@Override
	public List<OrderDetails> getOrderDetailsWithMaxPrice() {
		List<OrderDetails> findOrderDetailsWithMaxPrice = orderDetailsRepository.findOrderDetailsWithMaxPrice();
		if (findOrderDetailsWithMaxPrice.size() > 0) {
			return findOrderDetailsWithMaxPrice;
		} else {
			throw new CustomException("Order Details not found");
		}
	}

	// -----------------------------------------61---------------------------------------------

	@Override
	public Long countOrderDetailsByProductCode(String productCode) {
		Long countByProductCode = orderDetailsRepository.countByProductCode(productCode);
		if (countByProductCode != null) {
			return countByProductCode;
		} else {
			throw new CustomException("InsufficientStockException");
		}

	}

	// -------------------------------------------63E----------------------------------------
	@Override
	public List<Product> getAllProductsForAllOrders() {
		List<Product> findAllProductsForAllOrders = orderDetailsRepository.findAllProductsForAllOrders();
		if (findAllProductsForAllOrders.size() > 0) {
			return findAllProductsForAllOrders;
		} else {
			throw new CustomException("Order Details not found");
		}
	}

	// ----------------------------------------------70E------------------------------------
	@Override
	public List<OrderDetails> searchOrderDetailsByOrderNumber(Integer orderNumber) {
		List<OrderDetails> orders = orderDetailsRepository.searchByOrderNumber(orderNumber);

		if (orders.size() > 0) {
			return orders;
		} else {
			throw new CustomException("Order Details not found");
		}
	}
	// ------------------------------------------72E---------------------------------------------

	@Override
	public BigDecimal calculateTotalSale() {

		BigDecimal bd = orderDetailsRepository.calculateTotalSale();
		if (bd != null) {
			return bd;
		} else {
			throw new CustomException("Order Details not found");
		}
	}
	// -------------------------------------------71E------------------------------------------------

	@Override
	public BigDecimal getTotalAmountByOrderNumber(Integer orderNumber) {
		Integer total = orderDetailsRepository.calculateTotalByOrderNumber(orderNumber);
		BigDecimal valueOf = BigDecimal.valueOf(total);
		if (valueOf != null) {
			return valueOf;
		} else {
			throw new CustomException("Order Details not found");
		}

	}

	// ---------------------------------69E---------------------------------------------------

	@Override
	public OrderDetails updateQuantityOrdered(Integer orderNumber, String productCode, Integer quantityOrdered) {
		OrderDetailId orderDetailsId = new OrderDetailId(orderNumber, productCode);
		OrderDetails orderDetails = orderDetailsRepository.findById(orderDetailsId).orElse(null);
		if (orderDetails != null) {
			orderDetails.setQuantityOrdered(quantityOrdered);
			return orderDetailsRepository.save(orderDetails);
		}

		else {
			throw new CustomException("Order Details not found");
		}

	}

	// ------------------------------------68E-----------------------------------------------

	@Override
	public OrderDetails addOrderDetails(OrderDetails orderDetails) {

		if (orderDetails == null) {
			throw new IllegalArgumentException("Order details cannot be null");
		}

		// return orderDetailsRepository.save(orderDetails);
		else {
			throw new CustomException("InsufficientStockException");
		}
	}
	// --------------------------------------61-----------------------------------------------------------

	@Override
	public long getCountOfOrderDetails() {
		return getCountOfOrderDetails();
	}

}