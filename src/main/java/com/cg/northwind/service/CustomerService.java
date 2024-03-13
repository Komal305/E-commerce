package com.cg.northwind.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.cg.northwind.entity.Customers;
import com.cg.northwind.exception.CustomerNotExistsException;
import com.cg.northwind.exception.CustomerNotfoundException;
import com.cg.northwind.exception.IdAlreadyExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;



public interface CustomerService {

    List<Customers> getAllCustomers();
 
	List<Customers> getCustomersByCompanyName(String companyName);
	
	List<Customers> getCustomerByCountry(String country);
	
	List<Customers> getCustomerByCity(String city);
	
	List<Customers> getCustomerByRegion(String region);
	
	List<Customers> getCustomerByFax(String fax);
	
	List<Customers> getCustomersWithNotNullRegion();
	
	List<Customers> getCustomersByContactTitle(String contactTitle);
	
	List<Object[]> getCountOfCustomersByCountry();
	
	List<String> getUniqueContactTitles();
	
	Customers assignAddress(String customerId, String address) throws IdDoesNotExistsException;
	 
	Customers createCustomer(Customers customer) throws IdAlreadyExistsException;

	Customers getCustomerRegionById(String customerId);
    void updateCustomerRegion(String customerId, String region);
    
    Customers getCustomerById(String customerId) throws CustomerNotfoundException;
    void assignAddressToCustomer(String customerId, String address);
    
    
    Optional<Customers> getCustomerContactNameById(String customerId);
    void updateCustomerContactName(String  customerId, String newContactName) throws CustomerNotExistsException;
	
}
