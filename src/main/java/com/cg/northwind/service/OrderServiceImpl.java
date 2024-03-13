package com.cg.northwind.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cg.northwind.DTO.CustomersDTO;
import com.cg.northwind.DTO.EmployeeSaleDTO;
import com.cg.northwind.DTO.OrderCountByCustomerDTO;
import com.cg.northwind.entity.Customers;
import com.cg.northwind.entity.Employees;
import com.cg.northwind.entity.Orders;
import com.cg.northwind.entity.Shippers;
import com.cg.northwind.exception.NoContentException;
import com.cg.northwind.exception.OrderIdNotFoundException;
import com.cg.northwind.repository.CustomerRepository;
import com.cg.northwind.repository.EmployeeRepository;
import com.cg.northwind.repository.OrderRepository;
import com.cg.northwind.repository.ShippersRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository repo;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ShippersRepository shipperRepository;

	@Override
	public Orders addOrders(Orders orders) throws OrderIdNotFoundException {
		if(repo.existsById(orders.getOrderID())) {
			throw new OrderIdNotFoundException("The order which you are adding is present");
		}
		return repo.save(orders);
	}

	@Override
	public List<Orders> getAllOrders() {
		return repo.findAll();
	}

	@Override
	public Orders updateAllOrderDetails(int orderID,Orders orders,String customerID, int employeeId, int shipVia) {
		Optional<Orders> o = repo.findById(orderID);
		Customers customers = customerRepository.findById(customerID).get();
		Employees employees = employeeRepository.findById(employeeId).get();
		Shippers shippers = shipperRepository.findById(shipVia).get();
		Orders order = o.get();
		order.setOrderID(orders.getOrderID());
		order.setOrderDate(orders.getOrderDate());
		order.setRequiredDate(orders.getRequiredDate());
		order.setShippedDate(orders.getShippedDate());
		order.setFreight(orders.getFreight());
		order.setShipName(orders.getShipName());
		order.setShipAddress(orders.getShipAddress());
		order.setShipCity(orders.getShipCity());
		order.setShipRegion(orders.getShipRegion());
		order.setShipPostalCode(orders.getShipPostalCode());
		order.setShipCountry(orders.getShipCountry());
		order.setCustomers(customers);
		order.setEmployees(employees);
		order.setShipVia(shippers);
		
		return repo.save(order);
	}

	@Override
	public List<Orders> getOrdersByCustomerID(String customerID) {
		return repo.findByCustomersCustomerID(customerID);
	}

	@Override
	public Customers getCustomerDetailsByOrderID(int orderID) {
		return repo.findCustomerDetailsByOrderID(orderID);
	}

	@Override
	public CustomersDTO getFewCustomerDetailsByOrderId(int orderID) {
		return repo.findCustomerByOrderID(orderID);
	}

	@Override
	public List<Orders> getOrdersByOrderDate(Date orderDate) {
		return repo.findByOrderDate(orderDate);
	}

	@Override
	public List<Orders> getOrdersByCompanyName(String companyName) {
		return repo.findByCustomersCompanyNameIgnoreCase(companyName);
	}

	@Override
	public List<Orders> getOrdersBetweenDates(Date fromDate, Date toDate) {
		return repo.findByOrderDateBetween(fromDate, toDate);
	}

	@Override
	public List<Orders> getTopTenOrders() {
		Sort sort = Sort.by(Sort.Direction.ASC, "orderDate");
        Pageable pageable = PageRequest.of(0, 10, sort);
		return repo.findAll(pageable).getContent();
	}

	@Override
	public List<OrderCountByCustomerDTO> getOrderCountByCustomer() {
		return repo.getOrderCountByCustomer();
	}

	@Override
	public String getHighestOrderCustomer() {
		return repo.findCustomerWithHighestOrder();
	}

	@Override
	public List<String> searchCustomersByFreightGreaterThan(double freight) {
		return repo.findCustomersByFreightGreaterThan(freight);
	}

	@Override
	public List<Orders> searchOrdersByShipName(String shipName) {
		return repo.findByShipName(shipName);
	}

	@Override
	public List<Orders> searchSalesByEmployee(String firstName) {
		return repo.findByEmployees_FirstName(firstName);
	}

	@Override
	public String searchEmployeeWithHighestSale() throws OrderIdNotFoundException {
		List<EmployeeSaleDTO> result = repo.findEmployeeWithHighestSale();
        if (result.isEmpty()) {
            throw new OrderIdNotFoundException("Employee Not Found ");
        }
        return result.get(0).getEmployeeName();
	}

	@Override
	public Orders updateOrderDetails(int orderId, Orders updatedOrder) throws NoContentException {
		Optional<Orders> existingOrderOptional = repo.findById(orderId);
		if (existingOrderOptional.isEmpty()) {
           throw new NoContentException("No such data present");
        }
		
		Orders existingOrder = existingOrderOptional.get();
		if (updatedOrder.getOrderDate() != null) {
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
        }
        if (updatedOrder.getRequiredDate() != null) {
            existingOrder.setRequiredDate(updatedOrder.getRequiredDate());
        }
        if (updatedOrder.getShippedDate() != null) {
            existingOrder.setShippedDate(updatedOrder.getShippedDate());
        }
        
        return repo.save(existingOrder);
	}

	@Override
	public Orders addAllOrders(Orders orders, String customerID, int employeeId, int shipVia) {
		Customers customers = customerRepository.findById(customerID).get();
		Employees employees = employeeRepository.findById(employeeId).get();
		Shippers shippers = shipperRepository.findById(shipVia).get();
		orders.setCustomers(customers);
		orders.setEmployees(employees);
		orders.setShipVia(shippers);
		return repo.save(orders);
	}

	@Override
	public boolean isOrderPresent(int orderID) {
		return repo.existsById(orderID);
	}
}
