package com.cg.northwind.service;

import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Repository;

import com.cg.northwind.DTO.CustomersDTO;
import com.cg.northwind.DTO.OrderCountByCustomerDTO;
import com.cg.northwind.entity.Customers;
import com.cg.northwind.entity.Orders;
import com.cg.northwind.exception.NoContentException;
import com.cg.northwind.exception.OrderIdNotFoundException;

@Repository
public interface OrderService {
	
	Orders addOrders(Orders orders) throws OrderIdNotFoundException;
	
	List<Orders> getAllOrders();
	
//	Orders updateAllOrderDetails(int orderID,Orders orders);
	
	List<Orders> getOrdersByCustomerID(String customerID);
	
	Customers getCustomerDetailsByOrderID(int orderID);
	
	CustomersDTO getFewCustomerDetailsByOrderId(int orderID);
	
	List<Orders> getOrdersByOrderDate(Date orderDate);
	
	List<Orders> getOrdersByCompanyName(String companyName);
	
	List<Orders> getOrdersBetweenDates(Date fromDateConverted, Date toDateConverted);
	
	List<Orders> getTopTenOrders();
	
	List<OrderCountByCustomerDTO> getOrderCountByCustomer();
	
	String getHighestOrderCustomer();
	
	List<String> searchCustomersByFreightGreaterThan(double freight);
	
	List<Orders> searchOrdersByShipName(String shipName);

	List<Orders> searchSalesByEmployee(String firstName);
	
	String searchEmployeeWithHighestSale() throws OrderIdNotFoundException;
	
	Orders updateOrderDetails(int orderId, Orders updatedOrder) throws NoContentException;
	
	Orders addAllOrders(Orders orders,String customerID, int employeeId, int shipVia);
	
	boolean isOrderPresent(int orderID);

	public Orders updateAllOrderDetails(int orderID, Orders orders, String customerID, int employeeId, int shipVia);

	
}
