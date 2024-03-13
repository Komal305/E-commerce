package com.cg.northwind.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.northwind.DTO.CustomersDTO;
import com.cg.northwind.DTO.EmployeeSaleDTO;
import com.cg.northwind.DTO.OrderCountByCustomerDTO;
import com.cg.northwind.entity.Customers;
import com.cg.northwind.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{

	
	List<Orders> findByCustomersCustomerID(String customerID);
	
	@Query("select o.customers from Orders o where o.orderID = :orderID")
    Customers findCustomerDetailsByOrderID(@Param("orderID") int orderID);
	
	List<Orders> findByOrderDate(Date orderDate);
	
	@Query(value = "Select o.customers.customerID as customerID, o.customers.companyName as companyName from Orders o where o.orderID = :orderId")
	CustomersDTO findCustomerByOrderID(int orderId);
	
	List<Orders> findByCustomersCompanyNameIgnoreCase(String companyName);
	
	List<Orders> findByOrderDateBetween(Date fromDate, Date toDate);
	
	@Query("SELECT o.customers.customerID AS customerID, COUNT(*) AS numberOfOrders " +
	           "FROM Orders o " +
	           "GROUP BY o.customers.customerID")
	List<OrderCountByCustomerDTO> getOrderCountByCustomer();
	
	@Query("SELECT o.customers.customerID AS customerID " +
	           "FROM Orders o " +
	           "GROUP BY o.customers.customerID " +
	           "ORDER BY COUNT(*) ASC " +
	           "LIMIT 1")
	String findCustomerWithHighestOrder();
	
	@Query("SELECT DISTINCT o.customers.customerID " +
	           "FROM Orders o " +
	           "WHERE o.freight > :freight")
	List<String> findCustomersByFreightGreaterThan(@Param("freight") double freight);
	
	List<Orders> findByShipName(String shipName);
	
	List<Orders> findByEmployees_FirstName(String firstName);
	
	@Query("SELECT CONCAT(o.employees.firstName, ' ', o.employees.lastName) AS employeeName " +
	           "FROM Orders o " +
	           "GROUP BY o.employees.firstName, o.employees.lastName " +
	           "ORDER BY SUM(o.freight) DESC")
	List<EmployeeSaleDTO> findEmployeeWithHighestSale();
}
