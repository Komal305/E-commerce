package com.cg.northwind.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.northwind.DTO.CustomersDTO;
import com.cg.northwind.DTO.OrderCountByCustomerDTO;
import com.cg.northwind.entity.Customers;
import com.cg.northwind.entity.Orders;
import com.cg.northwind.exception.NoContentException;
import com.cg.northwind.exception.OrderAlreadyExistException;
import com.cg.northwind.exception.OrderIdNotFoundException;
import com.cg.northwind.repository.CustomerRepository;
import com.cg.northwind.repository.EmployeeRepository;
import com.cg.northwind.repository.ShippersRepository;
import com.cg.northwind.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ShippersRepository shipperRepository;
	
	
	@GetMapping("/")
	public ResponseEntity<List<Orders>> getAllOrders() throws NoContentException{
		List<Orders> allOrders = service.getAllOrders();
		if(allOrders.isEmpty()) {
			throw new NoContentException("No Orders left");
		}
		return ResponseEntity.ok(allOrders);
	}
	
	@PutMapping("/edit/{orderID}/{customerID}/{employeeId}/{shipVia}")
	public EntityModel<Orders> updateAllOrderDetails(@PathVariable int orderID, @RequestBody Orders orders,@PathVariable String customerID,@PathVariable int employeeId,@PathVariable int shipVia) throws NoContentException{
		Orders updatedOrder = service.updateAllOrderDetails(orderID, orders, customerID, employeeId,shipVia);
		EntityModel<Orders> entityModel = EntityModel.of(updatedOrder);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllOrders());
		entityModel.add(link.withRel("all-shippers"));
		return entityModel;
	}
	
	@GetMapping("/customerId/{customerId}")
	public List<Orders> findOrderbyCustomerId(@PathVariable String customerId){
		return service.getOrdersByCustomerID(customerId);
	}
	
	@GetMapping("/customer/{orderID}")
    public ResponseEntity<Customers> getCustomerDetailsByOrderID(@PathVariable int orderID) throws NoContentException {
        Customers customerDetails = service.getCustomerDetailsByOrderID(orderID);
        if (customerDetails != null) {
            return ResponseEntity.ok(customerDetails);
        } else {
            throw new NoContentException("No such customer by this order id");
        }
    }
	
	@GetMapping("/order/{orderID}")
    public CustomersDTO getOrderDetailsByOrderId(@PathVariable int orderID) {
        CustomersDTO customerDetails = service.getFewCustomerDetailsByOrderId(orderID);
        System.out.println("Order ID : "+ orderID);
        return customerDetails;
    }
	
	@GetMapping("/OrderDate/{OrderDate}")
	public ResponseEntity<List<Orders>> searchOrdersByOrderDate(@PathVariable("OrderDate") LocalDate orderDate) {
		Date convertedOrderDate = Date.from(orderDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<Orders> orders = service.getOrdersByOrderDate(convertedOrderDate);
	    return ResponseEntity.ok(orders);
	}
	
	@GetMapping("/orderbyCompanyName/{CompanyName}")
	public ResponseEntity<List<Orders>> getOrdersByCustomerName(@PathVariable("CompanyName") String companyName) {
		List<Orders> orders = service.getOrdersByCompanyName(companyName);
		return ResponseEntity.ok(orders);
	}
	
	@GetMapping("/betweendate/{fromDate}/{toDate}")
    public List<Orders> getOrdersBetweenDates(@PathVariable("fromDate") LocalDate fromDate,
                                             @PathVariable("toDate") LocalDate toDate) {
        Date fromDateConverted = Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date toDateConverted = Date.from(toDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return service.getOrdersBetweenDates(fromDateConverted, toDateConverted);
    }
	
	@GetMapping("/toptenorders")
    public List<Orders> getTopTenOrders() {
        return service.getTopTenOrders();
    }
	
	@GetMapping("/orderbyeachcustomer")
    public ResponseEntity<List<OrderCountByCustomerDTO>> getOrderCountByCustomer() {
        List<OrderCountByCustomerDTO> orderCountList = service.getOrderCountByCustomer();
        return ResponseEntity.ok(orderCountList);
    }
	
	@GetMapping("/highestordercustomer")
    public ResponseEntity<String> getHighestOrderCustomer() {
        String highestOrderCustomer = service.getHighestOrderCustomer();
        return ResponseEntity.ok(highestOrderCustomer);
    }
	
	@GetMapping("/freight/{freight}")
    public ResponseEntity<List<String>> searchCustomersByFreightGreaterThan(@PathVariable double freight) {
        List<String> customerIDs = service.searchCustomersByFreightGreaterThan(freight);
        return ResponseEntity.ok(customerIDs);
    }
	
	@GetMapping("/shipname/{shipName}")
	public ResponseEntity<List<Orders>> searchOrdersByShipName(@PathVariable String shipName){
		List<Orders> byShipname = service.searchOrdersByShipName(shipName);
		return ResponseEntity.ok(byShipname);
	}
	
	@GetMapping("/salesmadebyemployee/{firstName}")
    public ResponseEntity<List<Orders>> searchSalesByEmployee(@PathVariable String firstName) {
        List<Orders> orders = service.searchSalesByEmployee(firstName);
        return ResponseEntity.ok(orders);
    }
	
	@GetMapping("/highestsaleemployee")
    public ResponseEntity<String> searchEmployeeWithHighestSale() throws OrderIdNotFoundException {
        String employeeName = service.searchEmployeeWithHighestSale();
        if (employeeName == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeName);
    }
	
	@PatchMapping("/{orderId}")
    public ResponseEntity<Void> updateOrderDetails(@PathVariable int orderId, @RequestBody Orders updatedOrder) throws NoContentException {
        service.updateOrderDetails(orderId, updatedOrder);
        return ResponseEntity.noContent().build();
    }
	
	@PostMapping("/{customerId}/{employeeId}/{shipVia}")
	public ResponseEntity<String> addAllOrderDetails(@RequestBody Orders orders,@PathVariable("customerId") String customerID,
			@PathVariable("employeeId") int employeeId, @PathVariable("shipVia") int shipVia) throws OrderAlreadyExistException{
		if(orders.getOrderID() != 0 && service.isOrderPresent(orders.getOrderID())) {
			throw new OrderAlreadyExistException("Order which you are inserting present already");
		}
		customerRepository.findById(customerID);
		employeeRepository.findById(employeeId);
		shipperRepository.findById(shipVia);
		service.addAllOrders(orders, customerID, employeeId, shipVia);
		return ResponseEntity.ok("Record Added Successfully");
	}
}

