package com.cg.cbmapp.ControllerTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import com.cg.cbmapp.controller.OrderDetailsController;
import com.cg.cbmapp.entities.OrderDetails;
import com.cg.cbmapp.entities.Product;

import com.cg.cbmapp.service.OrderDetailsService;

@ExtendWith(MockitoExtension.class)
class OrderDetailsControllerTest {

    @Mock
    private OrderDetailsService orderDetailsService;

    @InjectMocks
    private OrderDetailsController orderDetailsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ----------------------------------GET(1)---------------------------------------
    @Test
    void testGetOrderDetailsWithMaxPrice_ReturnsListOfOrderDetails() {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(new OrderDetails());
        orderDetailsList.add(new OrderDetails());
        when(orderDetailsService.getOrderDetailsWithMaxPrice()).thenReturn(orderDetailsList);

        List<OrderDetails> response = orderDetailsController.getOrderDetailsWithMaxPrice();

        assertEquals(orderDetailsList.size(), response.size());
        assertEquals(orderDetailsList, response);
    }

    // ----------------------------------GET(2)---------------------------------------
//    @Test
//    void testGetCountOfOrderDetails_ReturnsCount() {
//        long count = 10;
//        when(orderDetailsService.getCountOfOrderDetails()).thenReturn(count);
//
//        long response = orderDetailsController.getCountOfOrderDetails();
//
//        assertEquals(count, response);
//    }
    


    // ----------------------------------GET(3)---------------------------------------
    @Test
    void testGetAllProductsForAllOrders_ReturnsListOfProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(orderDetailsService.getAllProductsForAllOrders()).thenReturn(productList);

        List<Product> response = orderDetailsController.getAllProductsForAllOrders();

        assertEquals(productList.size(), response.size());
        assertEquals(productList, response);
    }


    // ----------------------------------GET(5)---------------------------------------
    @Test
    void testSearchOrderDetailsByOrderNumber_ValidOrderNumber_ReturnsListOfOrderDetails() {
        Integer orderNumber = 123;
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(new OrderDetails());
        orderDetailsList.add(new OrderDetails());
        when(orderDetailsService.searchOrderDetailsByOrderNumber(orderNumber)).thenReturn(orderDetailsList);

        List<OrderDetails> response = orderDetailsController.searchOrderDetailsByOrderNumber(orderNumber);

        assertEquals(orderDetailsList.size(), response.size());
        assertEquals(orderDetailsList, response);
    }

    // ----------------------------------GET(6)---------------------------------------
    @Test
    void testGetTotalSale_ReturnsTotalSale() {
        BigDecimal totalSale = new BigDecimal("100.00");
        when(orderDetailsService.calculateTotalSale()).thenReturn(totalSale);

        BigDecimal response = orderDetailsController.getTotalSale();

        assertEquals(totalSale, response);
    }

    // ----------------------------------GET(7)---------------------------------------
    @Test
    void testGetTotalOrderAmount_ValidOrderNumber_ReturnsTotalAmount() {
        Integer orderNumber = 123;
        BigDecimal totalAmount = new BigDecimal("50.00");
        when(orderDetailsService.getTotalAmountByOrderNumber(orderNumber)).thenReturn(totalAmount);

        BigDecimal response = orderDetailsController.getTotalOrderAmount(orderNumber);

        assertEquals(totalAmount, response);
    }

    // ----------------------------------PUT(8)---------------------------------------
//    @Test
//    void testUpdateQuantityOrdered_ValidData_ReturnsOkResponse() {
//        Integer orderNumber = 123;
//        String productCode = "P123";
//        Integer quantityOrdered = 5;
//        OrderDetails updatedOrderDetails = new OrderDetails();
//        when(orderDetailsService.updateQuantityOrdered(orderNumber, productCode, quantityOrdered)).thenReturn(updatedOrderDetails);
//
//        ResponseEntity<String> response = orderDetailsController.updateQuantityOrdered(orderNumber, productCode, quantityOrdered);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Product quantity updated successfully", response.getBody());
//    }
}
