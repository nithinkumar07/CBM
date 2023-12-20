package com.cg.cbmapp.service;

import java.math.BigDecimal;
import java.util.List;

import com.cg.cbmapp.entities.OrderDetails;
import com.cg.cbmapp.entities.Product;

public interface OrderDetailsService {

	// --------------------60----------------------------

	List<OrderDetails> getOrderDetailsWithMaxPrice();
	// ---------------------------------------------------------


	long getCountOfOrderDetails();

	// --------------------------65------------------------------------
	List<Product> getAllProductsForAllOrders();

	// ---------70----------------------------------------------------

	List<OrderDetails> searchOrderDetailsByOrderNumber(Integer orderNumber);

	// --------------------------72------------------------------------

	BigDecimal calculateTotalSale();

	// -----------------------------71------------------------------------

	BigDecimal getTotalAmountByOrderNumber(Integer orderNumber);
	
	//-------------------------------------69------------------------------
	
	 OrderDetails updateQuantityOrdered(Integer orderNumber, String productCode, Integer quantityOrdered);

	 
	 //----------------68---------------------------------------------
	 
	 OrderDetails addOrderDetails(OrderDetails orderDetails);
	 
	 //---------------------------------61---------------------------------------------------------
	 Long countOrderDetailsByProductCode(String productCode);
	 
	 
}