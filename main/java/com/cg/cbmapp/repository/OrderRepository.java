package com.cg.cbmapp.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.cbmapp.entities.Order;
import com.cg.cbmapp.entities.OrderDetails;
import com.cg.cbmapp.entities.Product;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	

	List<Order> getAllOrdersByCustomer_CustomerNumber(Integer customerNumber);

	// -------------------------Search Order by Order number-------------------------------------------------

	Order findByOrderNumber(Integer orderNumber);

	// -----------------------Get all orders by order date---------------------------------------------------

	List<Order> findByOrderDate(LocalDate orderDate);

	// -------------------------Get all orders by required date-------------------------------------------------

	List<Order> findAllByRequiredDate(LocalDate requiredDate);

	// -------------------------Get all orders by required date-------------------------------------------------

	List<Order> findByShippedDate(LocalDate shippedDate);

	// ------------------------------GEt all orders by order status-----------------------------------------------
	List<Order> findByStatus(String status);

	// ---------------------------Get all order by custNo and status----------------------------------------------------

	List<Order> findByCustomer_CustomerNumberAndStatus(Integer customerNumber, String status);

	// -------------------------------------Get all products for given order number--------------------------------------------------

	@Query("SELECT od.product FROM OrderDetails od WHERE od.order.orderNumber = :orderNumber")
	List<Product> findAllProductsForOrder(@Param("orderNumber") Integer orderNumber);


	//-------------------------------------------Add new order----------------------------------------------------
	
	@Query("SELECT o FROM Order o JOIN o.customer c WHERE c.customerName = ?1")
    List<Order> findOrdersByCustomerName(String customerName);
}
