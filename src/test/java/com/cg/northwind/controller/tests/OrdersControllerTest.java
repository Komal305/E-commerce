package com.cg.northwind.controller.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.northwind.entity.Customers;
import com.cg.northwind.entity.Employees;
import com.cg.northwind.entity.Orders;
import com.cg.northwind.entity.Shippers;
import com.cg.northwind.exception.NoContentException;
import com.cg.northwind.repository.CustomerRepository;
import com.cg.northwind.repository.EmployeeRepository;
import com.cg.northwind.repository.OrderRepository;
import com.cg.northwind.repository.ShippersRepository;
import com.cg.northwind.service.OrderServiceImpl;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
@SpringBootTest(classes = com.cg.northwind.NorthWindApplication.class)
public class OrdersControllerTest {

	@InjectMocks
	private OrderServiceImpl orderService;

	@Mock
	private OrderRepository orderRepo;
	@Mock
	private CustomerRepository customerRepo;
	@Mock
	private EmployeeRepository employeeRepo;
	@Mock
	private ShippersRepository shipperRepo;
	

	@Test
	public void getAllOrdersTest() {
		List<Orders> ordersList = Stream.of(
				Orders.builder()
				.orderID(1)
                .customers(new Customers())
                .employees(new Employees())
                .orderDate(new Date())
                .requiredDate(new Date())
                .shippedDate(new Date())
                .shipVia(new Shippers())
                .freight(10.5)
                .shipName("John Doe")
                .shipAddress("123 Main St")
                .shipCity("New York")
                .shipRegion("NY")
                .shipPostalCode("10001")
                .shipCountry("USA")
                .build())
				.collect(Collectors.toList());
		when(orderRepo.findAll()).thenReturn(ordersList);
		List<Orders> result = orderService.getAllOrders();
		assertEquals(1, result.size());
		assertEquals(ordersList, result);
	}
	
	@Test
	public void addAllOrdersTest() {
		 Orders orders = Orders.builder()
		            .orderID(1)
		            .orderDate(new Date())
		            .requiredDate(new Date())
		            .shippedDate(new Date())
		            .freight(10.5)
		            .shipName("John Doe")
		            .shipAddress("123 Main St")
		            .shipCity("New York")
		            .shipRegion("NY")
		            .shipPostalCode("10001")
		            .shipCountry("USA")
		            .build();
		 
		    Customers customers = new Customers();
		    Employees employees = new Employees();
		    Shippers shippers = new Shippers();

		    when(customerRepo.findById(anyString())).thenReturn(Optional.of(customers));
		    when(employeeRepo.findById(anyInt())).thenReturn(Optional.of(employees));
		    when(shipperRepo.findById(anyInt())).thenReturn(Optional.of(shippers));

		    when(orderRepo.save(any(Orders.class))).thenReturn(orders);

		    Orders result = orderService.addAllOrders(orders, "customerID", 1, 2);

		    assertNotNull(result);
		    assertEquals(orders, result);

		    assertEquals(customers, result.getCustomers());
		    assertEquals(employees, result.getEmployees());
		    assertEquals(shippers, result.getShipVia());
	}
	
	@Test
	public void updateParticularOrderDetails() throws NoContentException {
		 // Create a sample order
		Orders existingOrder = new Orders();
	    existingOrder.setOrderID(1);
	    existingOrder.setOrderDate(new Date());
	    existingOrder.setRequiredDate(new Date());
	    existingOrder.setShippedDate(new Date());

	    // Mock the behavior of the repository's findById method
	    when(orderRepo.findById(1)).thenReturn(Optional.of(existingOrder));

	    // Create an updated order
	    Orders updatedOrder = new Orders();
	    updatedOrder.setOrderID(1);
	    updatedOrder.setOrderDate(new Date());
	    updatedOrder.setRequiredDate(new Date());
	    updatedOrder.setShippedDate(new Date());

	    // Mock the behavior of the repository's save method
	    when(orderRepo.save(any(Orders.class))).thenReturn(updatedOrder);

	    // Call the method under test
	    Orders result = orderService.updateOrderDetails(1, updatedOrder);

	    // Verify the repository findById method was called with the correct argument
	    verify(orderRepo).findById(1);

	    // Verify the repository save method was called with the updated order
	    ArgumentCaptor<Orders> ordersCaptor = ArgumentCaptor.forClass(Orders.class);
	    verify(orderRepo).save(ordersCaptor.capture());

	    // Retrieve the captured argument
	    Orders capturedOrder = ordersCaptor.getValue();

	    // Compare the individual fields of the Orders objects
	    assertEquals(updatedOrder.getOrderID(), capturedOrder.getOrderID());
	    assertEquals(updatedOrder.getOrderDate(), capturedOrder.getOrderDate());
	    assertEquals(updatedOrder.getRequiredDate(), capturedOrder.getRequiredDate());
	    assertEquals(updatedOrder.getShippedDate(), capturedOrder.getShippedDate());
	    // Compare other fields as needed

	    // Compare the result with the updated order
	    assertEquals(updatedOrder.getOrderID(), result.getOrderID());
	    assertEquals(updatedOrder.getOrderDate(), result.getOrderDate());
	    assertEquals(updatedOrder.getRequiredDate(), result.getRequiredDate());
	    assertEquals(updatedOrder.getShippedDate(), result.getShippedDate());
	}
}
