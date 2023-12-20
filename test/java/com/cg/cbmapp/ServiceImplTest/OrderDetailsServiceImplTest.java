package com.cg.cbmapp.ServiceImplTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.cbmapp.entities.OrderDetailId;
import com.cg.cbmapp.entities.OrderDetails;
import com.cg.cbmapp.entities.Product;
import com.cg.cbmapp.repository.OrderDetailsRepository;
import com.cg.cbmapp.service.OrderDetailsServiceImpl;

@SpringBootTest
public class OrderDetailsServiceImplTest {

	@Mock
	private OrderDetailsRepository orderDetailsRepository;

	@InjectMocks
	private OrderDetailsServiceImpl orderDetailsService;

	// ------------------------------Test case for getOrderDetailsWithMaxPrice method
	@Test
	public void testGetOrderDetailsWithMaxPrice() {
		List<OrderDetails> orderDetails = Arrays.asList(new OrderDetails(), new OrderDetails());

		// method
		when(orderDetailsRepository.findOrderDetailsWithMaxPrice()).thenReturn(orderDetails);

		List<OrderDetails> result = orderDetailsService.getOrderDetailsWithMaxPrice();

		verify(orderDetailsRepository, times(1)).findOrderDetailsWithMaxPrice();

		// Assert that the returned list of order details is the same as the mocked list
		assertEquals(orderDetails, result);
	}

	// -------------------------------Test case for countOrderDetailsByProductCode method
	@Test
	 void testCountOrderDetailsByProductCode() {
		String productCode = "P12345";
		Long count = 5L;

		when(orderDetailsRepository.countByProductCode(productCode)).thenReturn(count);

		Long result = orderDetailsService.countOrderDetailsByProductCode(productCode);

		verify(orderDetailsRepository, times(1)).countByProductCode(productCode);

		assertEquals(count, result);
	}

	//--------------------- Test case for getAllProductsForAllOrders method
	@Test
	void testGetAllProductsForAllOrders() {
		List<Product> products = Arrays.asList(new Product(), new Product());

		when(orderDetailsRepository.findAllProductsForAllOrders()).thenReturn(products);

		List<Product> result = orderDetailsService.getAllProductsForAllOrders();

		verify(orderDetailsRepository, times(1)).findAllProductsForAllOrders();

		assertEquals(products, result);
	}
	// ---------------------Test case for searchOrderDetailsByOrderNumber method
	@Test
	 void testSearchOrderDetailsByOrderNumber() {
		Integer orderNumber = 12345;
		List<OrderDetails> orderDetails = Arrays.asList(new OrderDetails(), new OrderDetails());

		when(orderDetailsRepository.searchByOrderNumber(orderNumber)).thenReturn(orderDetails);

		List<OrderDetails> result = orderDetailsService.searchOrderDetailsByOrderNumber(orderNumber);

		verify(orderDetailsRepository, times(1)).searchByOrderNumber(orderNumber);

		// Assert that the returned list of order details is the same as the mocked list
		assertEquals(orderDetails, result);
	}

	// ---------------------------Test case for calculateTotalSale method
	@Test
	 void testCalculateTotalSale() {
		BigDecimal totalSale = BigDecimal.valueOf(1000);

		when(orderDetailsRepository.calculateTotalSale()).thenReturn(totalSale);

		BigDecimal result = orderDetailsService.calculateTotalSale();

		verify(orderDetailsRepository, times(1)).calculateTotalSale();

		assertEquals(totalSale, result);
	}

	// ---------------------------------Test case for getTotalAmountByOrderNumber method
	@Test
	 void testGetTotalAmountByOrderNumber() {
		Integer orderNumber = 12345;
		Integer total = 500;
		BigDecimal expectedAmount = BigDecimal.valueOf(500);

		when(orderDetailsRepository.calculateTotalByOrderNumber(orderNumber)).thenReturn(total);

		BigDecimal result = orderDetailsService.getTotalAmountByOrderNumber(orderNumber);

		verify(orderDetailsRepository, times(1)).calculateTotalByOrderNumber(orderNumber);

		assertEquals(expectedAmount, result);
	}

	// --------------------------------Test case for updateQuantityOrdered method
	@Test

	 void testUpdateQuantityOrdered() {
		Integer orderNumber = 12345;
		String productCode = "P12345";
		Integer quantityOrdered = 10;
		OrderDetailId orderDetailId = new OrderDetailId(orderNumber, productCode);
		OrderDetails orderDetails = new OrderDetails();

		when(orderDetailsRepository.findById(orderDetailId)).thenReturn(Optional.of(orderDetails));

		when(orderDetailsRepository.save(orderDetails)).thenReturn(orderDetails);

		OrderDetails result = orderDetailsService.updateQuantityOrdered(orderNumber, productCode, quantityOrdered);

		verify(orderDetailsRepository, times(1)).findById(orderDetailId);

		verify(orderDetailsRepository, times(1)).save(orderDetails);

		assertEquals(orderDetails, result);
	}

}
