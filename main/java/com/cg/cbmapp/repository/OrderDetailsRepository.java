package com.cg.cbmapp.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.cbmapp.entities.OrderDetails;
import com.cg.cbmapp.entities.Customers;
import com.cg.cbmapp.entities.OrderDetailId;
import com.cg.cbmapp.entities.Product;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailId> {

	// get max Price---------60--------------------------------------
	@Query("SELECT od FROM OrderDetails od WHERE od.priceEach = (SELECT MAX(odi.priceEach) FROM OrderDetails odi)")
	List<OrderDetails> findOrderDetailsWithMaxPrice();

	// ---------------------------63---------------------------------
	@Query("SELECT od.product FROM OrderDetails od")
	List<Product> findAllProductsForAllOrders();

	// ------------------------70------------------
	@Query("SELECT od FROM OrderDetails od JOIN FETCH od.product p WHERE od.order.orderNumber = :orderNumber")
	List<OrderDetails> searchByOrderNumber(Integer orderNumber);



	// -----------------------------72-------------------------------------
	@Query("SELECT SUM(od.priceEach * od.quantityOrdered) FROM OrderDetails od")
	BigDecimal calculateTotalSale();

	// ------------------------------------71---------------------------------------

	@Query("SELECT SUM(od.priceEach * od.quantityOrdered) FROM OrderDetails od WHERE od.order.id = :orderNumber")
	Integer calculateTotalByOrderNumber(Integer orderNumber);
	
	//-----------------customer Durga-------------------------------------------------

	
List<OrderDetails> findByOrderCustomer(Customers customer);


 

    
    @Query("SELECT o FROM OrderDetails o WHERE o.order.customer.customerName = :cn")
    List<OrderDetails> orderDetailsByCustomerName(@Param("cn") String name);

 


    @Query("SELECT o FROM OrderDetails o WHERE o.order.customer.customerNumber = :cnumber")
    List<OrderDetails> orderDetailsByCustomerNumber(@Param("cnumber") Integer customerNumber);

    //-----------------------------------------61-----------------------------------------------
    
    @Query("SELECT COUNT(od) FROM OrderDetails od WHERE od.product.productCode = :productCode")
    Long countByProductCode(@Param("productCode") String productCode);
}