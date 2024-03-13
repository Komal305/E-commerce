package com.cg.northwind.service;

import java.util.List;

import com.cg.northwind.DTO.OrderDetailsDTO;
import com.cg.northwind.DTO.SalesDTO;
import com.cg.northwind.entity.OrderDetails;
import com.cg.northwind.entity.Orders;
import com.cg.northwind.exception.IdDoesNotExistsException;

public interface OrderDetailsService {
	
	List<OrderDetails> getAllOrders();
	
    public Double calculateBillAmountByOrderId(Orders orderId);

    public OrderDetails addOrderDetails(OrderDetails orderDetails, int orderId, int productId) throws IdDoesNotExistsException;

   

    List<SalesDTO> getSalesAmountAndTotalRevenue();

	List<OrderDetailsDTO> getOrderDetails(int orderId);

	OrderDetails updateOrderDetails(OrderDetails orderDetails, int orderID,int productId) throws IdDoesNotExistsException;
  
}
