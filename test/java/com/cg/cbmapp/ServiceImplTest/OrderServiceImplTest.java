package com.cg.cbmapp.ServiceImplTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.cbmapp.entities.Order;
import com.cg.cbmapp.repository.OrderRepository;
import com.cg.cbmapp.service.OrderServiceImpl;

@SpringBootTest
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    // ---------------51-------------------Test case for createOrder method
    @Test
    public void testCreateOrder() {
        Order order = new Order(); // Create an instance of Order

        // Mock the behavior of orderRepository.save() method
        when(orderRepository.save(order)).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        // Verify that orderRepository.save() was called once
        verify(orderRepository, times(1)).save(order);

        // Assert that the returned order is the same as the one saved
        assertEquals(order, createdOrder);
    }

    // -----------54--------------Test case for getAllOrdersByCustomerNumber method
    @Test
    public void testGetAllOrdersByCustomerNumber() {
        Integer customerNumber = 12345;
        List<Order> orders = Arrays.asList(new Order(), new Order());

        // Mock the behavior of orderRepository.getAllOrdersByCustomer_CustomerNumber() method
        when(orderRepository.getAllOrdersByCustomer_CustomerNumber(customerNumber)).thenReturn(orders);

        List<Order> result = orderService.getAllOrdersByCustomerNumber(customerNumber);

        // Verify that orderRepository.getAllOrdersByCustomer_CustomerNumber() was called once
        verify(orderRepository, times(1)).getAllOrdersByCustomer_CustomerNumber(customerNumber);

        // Assert that the returned list of orders is the same as the mocked list
        assertEquals(orders, result);
    }

    //-----------------------------55------------------------------------------------------------------
    @Test
    public void testGetOrderDetailsByOrderNumber() {
        Integer orderNumber = 12345;
        Order order = new Order();

        // Mock the behavior of orderRepository.findByOrderNumber() method
        when(orderRepository.findByOrderNumber(orderNumber)).thenReturn(order);

        Order result = orderService.getOrderDetailsByOrderNumber(orderNumber);

        // Verify that orderRepository.findByOrderNumber() was called once
        verify(orderRepository, times(1)).findByOrderNumber(orderNumber);

        // Assert that the returned order is the same as the mocked order
        assertEquals(order, result);
    }

    //---------56---------- Test case for getOrdersByOrderDate method------------------------------------
    @Test
    public void testGetOrdersByOrderDate() {
        LocalDate orderDate = LocalDate.now();
        List<Order> orders = Arrays.asList(new Order(), new Order());

        // Mock the behavior of orderRepository.findByOrderDate() method
        when(orderRepository.findByOrderDate(orderDate)).thenReturn(orders);

        List<Order> result = orderService.getOrdersByOrderDate(orderDate);

        // Verify that orderRepository.findByOrderDate() was called once
        verify(orderRepository, times(1)).findByOrderDate(orderDate);

        // Assert that the returned list of orders is the same as the mocked list
        assertEquals(orders, result);
    }

    //------------------57----- Test case for getAllOrdersByRequiredDate method-----------------------
    @Test
    public void testGetAllOrdersByRequiredDate() {
        LocalDate requiredDate = LocalDate.now();
        List<Order> orders = Arrays.asList(new Order(), new Order());

        // Mock the behavior of orderRepository.findAllByRequiredDate() method
        when(orderRepository.findAllByRequiredDate(requiredDate)).thenReturn(orders);

        List<Order> result = orderService.getAllOrdersByRequiredDate(requiredDate);

        // Verify that orderRepository.findAllByRequiredDate() was called once
        verify(orderRepository, times(1)).findAllByRequiredDate(requiredDate);

        // Assert that the returned list of orders is the same as the mocked list
        assertEquals(orders, result);
    }

    //------------------58--- Test case for getOrdersByShippedDate method---------------------------
    @Test
    public void testGetOrdersByShippedDate() {
        LocalDate shippedDate = LocalDate.now();
        List<Order> orders = Arrays.asList(new Order(), new Order());

        // Mock the behavior of orderRepository.findByShippedDate() method
        when(orderRepository.findByShippedDate(shippedDate)).thenReturn(orders);

        List<Order> result = orderService.getOrdersByShippedDate(shippedDate);

        // Verify that orderRepository.findByShippedDate() was called once
        verify(orderRepository, times(1)).findByShippedDate(shippedDate);

        // Assert that the returned list of orders is the same as the mocked list
        assertEquals(orders, result);
    }

    // -----------------59--------Test case for getOrdersByStatus method
    @Test
    public void testGetOrdersByStatus() {
        String status = "Pending";
        List<Order> orders = Arrays.asList(new Order(), new Order());

        // Mock the behavior of orderRepository.findByStatus() method
        when(orderRepository.findByStatus(status)).thenReturn(orders);

        List<Order> result = orderService.getOrdersByStatus(status);

        // Verify that orderRepository.findByStatus() was called once
        verify(orderRepository, times(1)).findByStatus(status);

        // Assert that the returned list of orders is the same as the mocked list
        assertEquals(orders, result);
    }

    // ----------62---------------Test case for getOrdersByCustomerNumberAndStatus method
    @Test
    public void testGetOrdersByCustomerNumberAndStatus() {
        Integer customerNumber = 12345;
        String status = "Pending";
        List<Order> orders = Arrays.asList(new Order(), new Order());

        // Mock the behavior of orderRepository.findByCustomer_CustomerNumberAndStatus() method
        when(orderRepository.findByCustomer_CustomerNumberAndStatus(customerNumber, status)).thenReturn(orders);

        List<Order> result = orderService.getOrdersByCustomerNumberAndStatus(customerNumber, status);

        // Verify that orderRepository.findByCustomer_CustomerNumberAndStatus() was called once
        verify(orderRepository, times(1)).findByCustomer_CustomerNumberAndStatus(customerNumber, status);

        // Assert that the returned list of orders is the same as the mocked list
        assertEquals(orders, result);
    }


}
