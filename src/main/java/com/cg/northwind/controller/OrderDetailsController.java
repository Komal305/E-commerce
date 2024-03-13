package com.cg.northwind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.northwind.DTO.OrderDetailsDTO;
import com.cg.northwind.DTO.SalesDTO;
import com.cg.northwind.entity.OrderDetails;
import com.cg.northwind.entity.Orders;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.service.OrderDetailsService;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService service;

	// Get All OrderDetails
	@GetMapping("")
	public ResponseEntity<List<OrderDetails>> getAllOrdersDetails() {
		List<OrderDetails> ord = service.getAllOrders();
		return ResponseEntity.ok(ord);
	}

	// Get BillAmount By OrderId
	@GetMapping("/billamount/{orderId}")
	public ResponseEntity<Double> getBillAmount(@PathVariable("orderId") Orders orderId) {

		return ResponseEntity.ok(service.calculateBillAmountByOrderId(orderId));

	}

	// Adding OrderDetails
	@PostMapping("/addorderdetails")
	public ResponseEntity<OrderDetails> addingOrderdetails(@RequestBody OrderDetails orderDetails,
			@RequestParam("orderid") int orderId, @RequestParam("productid") int productId)
			throws IdDoesNotExistsException {
		OrderDetails ord = service.addOrderDetails(orderDetails, orderId, productId);

		return ResponseEntity.ok(ord);
	}

	// Updating OrderDetails By Using OrderId
	@PutMapping("/updateorderdetails/{orderid}")
	public ResponseEntity<OrderDetails> updateOrderdetails(@RequestBody OrderDetails orderDetails,
			@PathVariable("orderid") int orderId, @RequestParam("productid") int productId)
			throws IdDoesNotExistsException {
		OrderDetails ord = service.updateOrderDetails(orderDetails, orderId, productId);

		return ResponseEntity.ok(ord);
	}

	// Get Sales By Using OrderDate
	@GetMapping("/sales")
	public List<SalesDTO> getSalesAmountAndTotalRevenue() {
		return service.getSalesAmountAndTotalRevenue();
	}

	// Get OrderDetails Particularly
	@GetMapping("/{orderId}")
	public List<OrderDetailsDTO> getOrderDetails(@PathVariable("orderId") int orderId) throws IdDoesNotExistsException {
		List<OrderDetailsDTO> ordDTO = service.getOrderDetails(orderId);
		if (ordDTO.isEmpty()) {
			throw new IdDoesNotExistsException("Id Not Found ");
		}
		return service.getOrderDetails(orderId);
	}

}
