package com.cg.cbmapp.ServiceImplTest;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.cbmapp.entities.Product;
import com.cg.cbmapp.repository.ProductRepository;
import com.cg.cbmapp.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testSaveProduct() {
        // Prepare
        Product product = new Product();

        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product savedProduct = productService.saveProduct(product);

        // Assert
        assertEquals(product, savedProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateBuyPrice() {
        // Prepare
        String productCode = "P001";
        BigDecimal buyPrice = BigDecimal.valueOf(10.0);
        Product product = new Product();
        product.setProductCode(productCode);

        when(productRepository.findByProductCode(productCode)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product updatedProduct = productService.updateBuyPrice(productCode, buyPrice);

        // Assert
        assertEquals(buyPrice, updatedProduct.getBuyPrice());
        verify(productRepository, times(1)).findByProductCode(productCode);
        verify(productRepository, times(1)).save(product);
    }



    @Test
    public void testUpdateQuantityInStock() {
        // Prepare
        String productCode = "P001";
        short quantityInStock = 100;
        Product product = new Product();
        product.setProductCode(productCode);

        when(productRepository.findByProductCode(productCode)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product updatedProduct = productService.updateQuantityInStock(productCode, quantityInStock);

        // Assert
        assertEquals(quantityInStock, updatedProduct.getQuantityInStock());
        verify(productRepository, times(1)).findByProductCode(productCode);
        verify(productRepository, times(1)).save(product);
    }
    
    @Test
    public void testUpdateMSRP() {
        // Prepare
        String productCode = "P001";
        BigDecimal msrp = BigDecimal.valueOf(20.0);
        Product product = new Product();
        product.setProductCode(productCode);

        when(productRepository.findByProductCode(productCode)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product updatedProduct = productService.updateMSRP(productCode, msrp);

        // Assert
        assertEquals(msrp, updatedProduct.getMSRP());
        verify(productRepository, times(1)).findByProductCode(productCode);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProductVendor() {
        // Prepare
        String productCode = "P001";
        String productVendor = "Vendor A";
        Product product = new Product();
        product.setProductCode(productCode);

        when(productRepository.findByProductCode(productCode)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product updatedProduct = productService.updateProductVendor(productCode, productVendor);

        // Assert
        assertEquals(productVendor, updatedProduct.getProductVendor());
        verify(productRepository, times(1)).findByProductCode(productCode);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProductScale() {
        // Prepare
        String productCode = "P001";
        String productScale = "1:12";
        Product product = new Product();
        product.setProductCode(productCode);

        when(productRepository.findByProductCode(productCode)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product updatedProduct = productService.updateProductScale(productCode, productScale);

        // Assert
        assertEquals(productScale, updatedProduct.getProductScale());
        verify(productRepository, times(1)).findByProductCode(productCode);
        verify(productRepository, times(1)).save(product);
    }

}
