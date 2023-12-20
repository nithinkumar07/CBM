package com.cg.cbmapp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cg.cbmapp.entities.Product;

public interface ProductService {

	// ----73 Post Add new Product object into DB-------//
	Product saveProduct(Product product);

	// ----74 Put Update buy price of specific product----//
	Product updateBuyPrice(String productCode, BigDecimal buyPrice);

	// ---- 75 Put Update Quantity in Stock for specific product----//
	Product updateQuantityInStock(String productCode, short quantityInStock);

	// ----76 Put Update MSRP in of specific product -----//
	Product updateMSRP(String productCode, BigDecimal msrp);

	// ----77 Put Update vendor of specific product-------//
	Product updateProductVendor(String productCode, String productVendor);

	// -----78 Put Update scale of specific product--------//
	Product updateProductScale(String productCode, String productScale);

	// -----79 PutUpdate name of specific product------//
	Product updateProductName(String productCode, String productName);

	// -----80 get Search Product Details by product_code------//
	Product getProductByCode(String productCode);

	// -----81 get Search Product Details by product name-------//
	Product getProductByName(String productName);

	// ---82 (get)Search Product Details by product scale---//
	List<Product> searchByProductScale(String productScale);

	// ---83 (get)Search Product Details by product vendor---//
	List<Product> searchByProductVendor(String productVendor);

	// ---85 (get)fetch total ordered quantity for particular product----//
	Integer getTotalOrderedQuantity(String productCode);

	// ---86 (get)fetch total sale for particular product---//
	BigDecimal getTotalSaleAmount(String productCode);

	// ---87 fetch total sale for each product---//s
	Map<String, BigDecimal> getTotalSaleForAllProducts();

	// ---88 show products which is highly demanded by customers---//
	Product getHighDemandProduct();


	// ---- show all products---//
		List<Product> getAllProducts();

	
	// -------------------------63----------------------------
//  List<Product> getProductsByOrderNumber(Integer orderNumber);
	List<Product> getProductsByProductCode(String productCode);

}
