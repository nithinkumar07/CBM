package com.cg.cbmapp.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.cbmapp.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

//	Product findByProductCode(String productCode);

//	Product findByProductName(String productName);

//	List<Product> findByProductScale(String productScale);

//	List<Product> findByProductVendor(String productVendor);

	// ---82 (get)Search Product Details by product scale---//
	List<Product> findByProductScale(String productScale);

	// ---83 (get)Search Product Details by product vendor---//
	List<Product> findByProductVendor(String productVendor);

	// ---85 (get)fetch total ordered quantity for particular product----//
	Product findByProductCode(String productCode);

	@Query("SELECT SUM(od.quantityOrdered) FROM OrderDetails od WHERE od.product.productCode = :productCode")
	Integer getTotalOrderedQuantityForProduct(String productCode);

	// ---86 & 87 (get)fetch total sale for particular product/products---//

	@Query("SELECT SUM(od.priceEach) FROM OrderDetails od WHERE od.product.productCode = :productCode")
	BigDecimal getTotalSaleAmountForProduct(String productCode);

	Product findByProductName(String productName);

	// ---88 show products which is highly demanded by customers---//

	
//	List<Product> findAllByOrderNumber(Integer orderNumber);
//--------63---------------------------------------
	List<Product> findAllByProductCode(String productCode);

	// -------------------------------------------------------------

//	   List<Product> findAllByOrderDetails_Order_OrderNumber(Integer orderNumber);
}

