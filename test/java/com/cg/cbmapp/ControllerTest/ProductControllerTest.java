package com.cg.cbmapp.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.cbmapp.controller.ProductController;
import com.cg.cbmapp.entities.Product;
import com.cg.cbmapp.service.ProductService;

class ProductControllerTest {

    private ProductService productService;
    private ProductController productController;

    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
        productController = new ProductController(productService);
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        when(productService.saveProduct(product)).thenReturn(product);

        ResponseEntity<String> response = productController.addProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Product details added successfully", response.getBody());
        verify(productService).saveProduct(product);
    }

    @Test
    void testUpdateBuyPrice() {
        String productCode = "P001";
        BigDecimal buyPrice = BigDecimal.valueOf(10.99);
        Product updatedProduct = new Product();
        when(productService.updateBuyPrice(productCode, buyPrice)).thenReturn(updatedProduct);

        ResponseEntity<String> response = productController.updateBuyPrice(productCode, buyPrice);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product's buy price updated successfully", response.getBody());
        verify(productService).updateBuyPrice(productCode, buyPrice);
    }

    @Test
    void testUpdateQuantityInStock() {
        String productCode = "P001";
        short quantityInStock = 100;
        Product updatedProduct = new Product();
        when(productService.updateQuantityInStock(productCode, quantityInStock)).thenReturn(updatedProduct);

        ResponseEntity<String> response = productController.updateQuantityInStock(productCode, quantityInStock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product's quantity updated successfully", response.getBody());
        verify(productService).updateQuantityInStock(productCode, quantityInStock);
    }

    @Test
    void testUpdateMSRP() {
        String productCode = "P001";
        BigDecimal msrp = BigDecimal.valueOf(19.99);
        Product updatedProduct = new Product();
        when(productService.updateMSRP(productCode, msrp)).thenReturn(updatedProduct);

        ResponseEntity<String> response = productController.updateMSRP(productCode, msrp);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product's MSRP updated successfully", response.getBody());
        verify(productService).updateMSRP(productCode, msrp);
    }

    @Test
    void testUpdateProductVendor() {
        String productCode = "P001";
        String productVendor = "Vendor ABC";
        Product updatedProduct = new Product();
        when(productService.updateProductVendor(productCode, productVendor)).thenReturn(updatedProduct);

        ResponseEntity<String> response = productController.updateProductVendor(productCode, productVendor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product's vendor updated successfully", response.getBody());
        verify(productService).updateProductVendor(productCode, productVendor);
    }
    
    @Test
    void testUpdateProductScale() {
        String productCode = "P001";
        String productScale = "1:18";
        Product updatedProduct = new Product();
        when(productService.updateProductScale(productCode, productScale)).thenReturn(updatedProduct);

        ResponseEntity<String> response = productController.updateProductScale(productCode, productScale);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product's scale updated successfully", response.getBody());
        verify(productService).updateProductScale(productCode, productScale);
    }

    @Test
    void testUpdateProductName() {
        String productCode = "P001";
        String productName = "New Product Name";
        Product updatedProduct = new Product();
        when(productService.updateProductName(productCode, productName)).thenReturn(updatedProduct);

        ResponseEntity<String> response = productController.updateProductName(productCode, productName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product's name updated successfully", response.getBody());
        verify(productService).updateProductName(productCode, productName);
    }

    @Test
    void testGetProductByCode() {
        String productCode = "P001";
        Product product = new Product();
        when(productService.getProductByCode(productCode)).thenReturn(product);

        ResponseEntity<?> response = productController.getProductByCode(productCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService).getProductByCode(productCode);
    }

    @Test
    void testGetProductByCode_NotFound() {
        String productCode = "P001";
        when(productService.getProductByCode(productCode)).thenReturn(null);

        ResponseEntity<?> response = productController.getProductByCode(productCode);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(productService).getProductByCode(productCode);
    }

    @Test
    void testGetProductByName() {
        String productName = "Product ABC";
        Product product = new Product();
        when(productService.getProductByName(productName)).thenReturn(product);

        ResponseEntity<?> response = productController.getProductByName(productName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService).getProductByName(productName);
    }

    @Test
    void testGetProductByName_NotFound() {
        String productName = "Product ABC";
        when(productService.getProductByName(productName)).thenReturn(null);

        ResponseEntity<?> response = productController.getProductByName(productName);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(productService).getProductByName(productName);
    }

    private void assertNull(Object body) {
	}

	@Test
    void testSearchByProductScale() {
        String productScale = "1:18";
        List<Product> products = new ArrayList<>();
        when(productService.searchByProductScale(productScale)).thenReturn(products);

        List<Product> result = productController.searchByProductScale(productScale);

        assertEquals(products, result);
        verify(productService).searchByProductScale(productScale);
    }

    @Test
    void testSearchByProductVendor() {
        String productVendor = "Vendor ABC";
        List<Product> products = new ArrayList<>();
        when(productService.searchByProductVendor(productVendor)).thenReturn(products);

        List<Product> result = productController.searchByProductVendor(productVendor);

        assertEquals(products, result);
        verify(productService).searchByProductVendor(productVendor);
    }

    @Test
    void testGetTotalOrderedQuantity() {
        String productCode = "P001";
        Integer totalOrderedQuantity = 100;
        when(productService.getTotalOrderedQuantity(productCode)).thenReturn(totalOrderedQuantity);

        ResponseEntity<Integer> response = productController.getTotalOrderedQuantity(productCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(totalOrderedQuantity, response.getBody());
        verify(productService).getTotalOrderedQuantity(productCode);
    }

    @Test
    void testGetTotalOrderedQuantity_NotFound() {
        String productCode = "P001";
        when(productService.getTotalOrderedQuantity(productCode)).thenReturn(0);

        ResponseEntity<Integer> response = productController.getTotalOrderedQuantity(productCode);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(productService).getTotalOrderedQuantity(productCode);
    }

    @Test
    void testGetTotalSaleAmount() {
        String productCode = "P001";
        BigDecimal totalSaleAmount = BigDecimal.valueOf(500.00);
        when(productService.getTotalSaleAmount(productCode)).thenReturn(totalSaleAmount);

        BigDecimal result = productController.getTotalSaleAmount(productCode);

        assertEquals(totalSaleAmount, result);
        verify(productService).getTotalSaleAmount(productCode);
    }

    @Test
    void testGetTotalSaleForAllProducts() {
        Map<String, BigDecimal> totalSaleMap = new HashMap<>();
        totalSaleMap.put("P001", BigDecimal.valueOf(100.00));
        totalSaleMap.put("P002", BigDecimal.valueOf(200.00));
        when(productService.getTotalSaleForAllProducts()).thenReturn(totalSaleMap);

        Map<String, BigDecimal> result = productController.getTotalSaleForAllProducts();

        assertEquals(totalSaleMap, result);
        verify(productService).getTotalSaleForAllProducts();
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = new ArrayList<>();
        when(productService.getAllProducts()).thenReturn(products);

        List<Product> result = productController.getAllProducts();

        assertEquals(products, result);
        verify(productService).getAllProducts();
    }

    @Test
    void testGetProductsByProductCode() {
        String productCode = "P001";
        List<Product> products = new ArrayList<>();
        when(productService.getProductsByProductCode(productCode)).thenReturn(products);

        List<Product> result = productController.getProductsByProductCode(productCode);

        assertEquals(products, result);
        verify(productService).getProductsByProductCode(productCode);
    }
}
