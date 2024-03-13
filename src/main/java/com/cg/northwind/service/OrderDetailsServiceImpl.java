package com.cg.northwind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.northwind.DTO.OrderDetailsDTO;
import com.cg.northwind.DTO.SalesDTO;
import com.cg.northwind.entity.OrderDetails;
import com.cg.northwind.entity.OrderDetailsId;
import com.cg.northwind.entity.Orders;
import com.cg.northwind.entity.Products;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.repository.OrderDetailsRepository;
import com.cg.northwind.repository.OrderRepository;
import com.cg.northwind.repository.ProductsRepository;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	private final OrderDetailsRepository orderDetailsRepo;

	@Autowired
	public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepo) {
		this.orderDetailsRepo = orderDetailsRepo;
	}

	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<OrderDetails> getAllOrders() {
		return orderDetailsRepo.findAll();
	}

	@Override
	public Double calculateBillAmountByOrderId(Orders odi) {
		List<OrderDetails> orderDetailsList = orderDetailsRepo.findByOrds(odi);
		Double billAmount = 0.0;

		for (OrderDetails orderDetails : orderDetailsList) {
			Double totalAmount = orderDetails.getUnitPrice() * orderDetails.getQuantity() - orderDetails.getDiscount();
			billAmount += totalAmount;
		}
		return billAmount;
	}

	@Override
	public OrderDetails addOrderDetails(OrderDetails orderDetails, int orderId, int productId)
			throws IdDoesNotExistsException {
		Orders orders = orderRepository.findById(orderId)
				.orElseThrow(() -> new IdDoesNotExistsException("Order Id Not Found"));
		Products products = productsRepository.findById(productId)
				.orElseThrow(() -> new IdDoesNotExistsException("Product Id Not Found"));
		OrderDetailsId orderDetailsId = new OrderDetailsId(orderId, productId);
		orderDetails.setOrds(orders);
		orderDetails.setProduct(products);
		orderDetails.setUnitPrice(products.getUnitPrice());
		orderDetails.setOdi(orderDetailsId);

		return orderDetailsRepo.save(orderDetails);
	}

	@Override
	public List<SalesDTO> getSalesAmountAndTotalRevenue() {

		return orderDetailsRepo.findSalesAmountAndTotalRevenue();
	}

	@Override
	public OrderDetails updateOrderDetails(OrderDetails orderDetails, int orderId, int productId)
			throws IdDoesNotExistsException {
		Orders orDetails = orderRepository.findById(orderId)
				.orElseThrow(() -> new IdDoesNotExistsException("Order Id Not Found"));
		Products products = productsRepository.findById(productId)
				.orElseThrow(() -> new IdDoesNotExistsException("Product Id Not Found"));
		OrderDetailsId orderDetailsId = new OrderDetailsId(orderId, productId);

		orderDetails.setUnitPrice(products.getUnitPrice());
		orderDetails.setOrds(orDetails);
		orderDetails.setProduct(products);
		orderDetails.setOdi(orderDetailsId);

		return orderDetailsRepo.save(orderDetails);
	}

	@Override
	public List<OrderDetailsDTO> getOrderDetails(int orderId) {

		return orderDetailsRepo.findByOrderdetails(orderId);
	}

}
