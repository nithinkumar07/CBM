package com.cg.cbmapp.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cbmapp.repository.OrderDetailsRepository;
//
import com.cg.cbmapp.entities.Product;
import com.cg.cbmapp.exception.CustomException;
//import com.cg.cbmapp.repository.OrderDetailsRepository;
import com.cg.cbmapp.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final OrderDetailsRepository orderDetailRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, OrderDetailsRepository orderDetailRepository) {
		this.productRepository = productRepository;
		this.orderDetailRepository = orderDetailRepository;
	}

	// ----73 Post Add new Product object into DB-------//

	@Override
	public Product saveProduct(Product product) {
		Product save = productRepository.save(product);
		if (save != null) {
			return save;
		} else {
			throw new CustomException("Product Details not found");
		}
	}

	// ----74 Put Update buy price of specific product----//
	@Override
	public Product updateBuyPrice(String productCode, BigDecimal buyPrice) {
		Product product = productRepository.findByProductCode(productCode);
		if (product != null) {
			product.setBuyPrice(buyPrice);
			return productRepository.save(product);
		}
		throw new CustomException("Product Details not found");
	}

	// ---- 75 Put Update Quantity in Stock for specific product----//
	@Override
	public Product updateQuantityInStock(String productCode, short quantityInStock) {
		Product product = productRepository.findByProductCode(productCode);
		if (product != null) {
			product.setQuantityInStock(quantityInStock);
			return productRepository.save(product);
		}
		throw new CustomException("Product Details not found");
	}

	// ----76 Put Update MSRP in of specific product -----//

	@Override
	public Product updateMSRP(String productCode, BigDecimal msrp) {
		Product product = productRepository.findByProductCode(productCode);
		if (product != null) {
			product.setMSRP(msrp);
			return productRepository.save(product);
		}
		throw new CustomException("Product Details not found");

	}
	// ----77 Put Update vendor of specific product-------//

	@Override
	public Product updateProductVendor(String productCode, String productVendor) {
		Product product = productRepository.findByProductCode(productCode);
		if (product != null) {
			product.setProductVendor(productVendor);
			return productRepository.save(product);
		}
		throw new CustomException("Product Details not found");
	}

	// -----78 Put Update scale of specific product--------//
	@Override
	public Product updateProductScale(String productCode, String productScale) {
		Product product = productRepository.findByProductCode(productCode);
		if (product != null) {
			product.setProductScale(productScale);
			return productRepository.save(product);
		}
		throw new CustomException("Product Details not found");
	}

	// --------------79 E----------------PutUpdate name of specific product------
	@Override
	public Product updateProductName(String productCode, String productName) {
		Product product = productRepository.findByProductCode(productCode);
		if (product != null) {
			product.setProductName(productName);
			return productRepository.save(product);
		}
		throw new CustomException("Product Details Not Found");
	}

	// -------------80-E----------------get Search Product Details by
	// product_code------//
	@Override
	public Product getProductByCode(String productCode) {
		Product product = productRepository.findByProductCode(productCode);
		if (product != null) {
			return product;
		}
		throw new CustomException("Product Details Not Found");
	}

	// -----81 get Search Product Details by product name-------//
	@Override
	public Product getProductByName(String productName) {
		Product findByProductName = productRepository.findByProductName(productName);
		if (findByProductName != null) {
			return findByProductName;
		} else {
			throw new CustomException("Product Details Not Found");
		}
	}
	// ---82 Search Product Details by product scale---//

	@Override
	public List<Product> searchByProductScale(String productScale) {
		List<Product> findByProductScale = productRepository.findByProductScale(productScale);
		if (findByProductScale.size() > 0) {
			return findByProductScale;
		} else {
			throw new CustomException("Product Details Not Found");
		}
	}
	// ---83 Search Product Details by product vendor---//

	@Override
	public List<Product> searchByProductVendor(String productVendor) {
		List<Product> findByProductVendor = productRepository.findByProductVendor(productVendor);
		if (findByProductVendor.size() > 0) {
			return findByProductVendor;
		} else {
			throw new CustomException("Product Details Not Found");
		}
	}
	// -----------------85-E---------fetch total ordered quantity for particular product----

	@Override
	public Integer getTotalOrderedQuantity(String productCode) {
		Integer totalOrderedQuantityForProduct = productRepository.getTotalOrderedQuantityForProduct(productCode);
		if (totalOrderedQuantityForProduct != null) {
			return totalOrderedQuantityForProduct;
		} else {
			throw new CustomException("Product Details Not Found");
		}

	}
	// ---86- E (get)fetch total sale for particular product---//
	@Override
	public BigDecimal getTotalSaleAmount(String productCode) {
		BigDecimal totalSaleAmountForProduct = productRepository.getTotalSaleAmountForProduct(productCode);
		if (totalSaleAmountForProduct != null) {
			Integer productQuantity = getTotalOrderedQuantity(productCode);
			BigDecimal quant = BigDecimal.valueOf(productQuantity);

			BigDecimal total = totalSaleAmountForProduct.multiply(quant);
			return total;
		} else {
			throw new CustomException("Product Details Not Found");
		}
	}

	// ----------------87-E----fetch total sale for each product---//

	@Override
	public Map<String, BigDecimal> getTotalSaleForAllProducts() {
		Map<String, BigDecimal> totalSalesMap = new HashMap<>();
		List<Product> products = productRepository.findAll();

		for (Product product : products) {
			BigDecimal totalSale = getTotalSaleAmount(product.getProductCode());
			totalSalesMap.put(product.getProductCode(), totalSale);
		}
		return totalSalesMap;
	}

	

	// ---88 show products which is highly demanded by customers---//

	@Override
	public Product getHighDemandProduct() {
		Long max = (long) 0;
		Product maxProduct = null;

		List<Product> allProducts = getAllProducts();

		for (Product product : allProducts) {
			Long quantity = getTotalOrders(product.getProductCode());
			if (quantity > max) {
				max = quantity;
				maxProduct = product;
			}
		}
		return maxProduct;

	}

	public Long getTotalOrders(String productCode) {

		Long orders = orderDetailRepository.countByProductCode(productCode);
		return orders;
	}


	@Override
	public List<Product> getProductsByProductCode(String productCode) {
		List<Product> findAllByProductCode = productRepository.findAllByProductCode(productCode);
		if (findAllByProductCode.size() > 0) {
			return findAllByProductCode;
		} else {
			throw new CustomException("Product Details Not Found");
		}
	}
	
	@Override
	public List<Product> getAllProducts() {
		List<Product> findAll = productRepository.findAll();
		if (findAll.size() > 0) {
			return findAll;
		} else {
			throw new CustomException("Product Details Not Found");
		}
	}

}