package com.cg.northwind.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.cg.northwind.entity.Customers;
import com.cg.northwind.exception.CustomerNotExistsException;
import com.cg.northwind.exception.CustomerNotfoundException;
import com.cg.northwind.exception.IdAlreadyExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.repository.CustomerRepository;




@Service
public class CustomerServiceImpl implements CustomerService {
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customers> getAllCustomers() {
		return customerRepository.findAll();
	}


	@Override
	public List<Customers> getCustomersByCompanyName(String companyName) {
		return customerRepository.findByCompanyName(companyName);
	}

	@Override
	public List<Customers> getCustomersByContactTitle(String contactTitle) {
		//		Sort sort=Sort.by(Sort.Direction.DESC,"contactTitle");
		return customerRepository.findAllByContactTitleOrderByContactTitleDesc(contactTitle);


	}

	//method to find by country
	@Override
	public List<Customers> getCustomerByCountry(String country){
		return customerRepository.findByCountry(country);
	}

	//method to find by City
	@Override
	public List<Customers> getCustomerByCity(String city){
		return customerRepository.findByCity(city);
	}

	//method to find by Region
	@Override
	public List<Customers> getCustomerByRegion(String region){
		return customerRepository.findByRegion(region);
	}
	@Override
	public List<Customers> getCustomerByFax(String fax){
		return customerRepository.findByFax(fax);
	}


	//method of regionNotNull
	@Override
	public List<Customers> getCustomersWithNotNullRegion() {
		return customerRepository.findByRegionNotNull();
	}
	@Override
	public List<Object[]> getCountOfCustomersByCountry() {
		return customerRepository.countCustomersByCountry();
	}

	@Override
	public Customers createCustomer(Customers customer) throws IdAlreadyExistsException {
		if(customerRepository.existsById(customer.getCustomerID()))
			throw new IdAlreadyExistsException("ID already exists");
		return customerRepository.save(customer);
	}
	@Override
	public Customers getCustomerById(String customerId) throws CustomerNotfoundException {
		return customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotfoundException("customer id not present") );
	}

	public void assignAddressToCustomer(String customerId, String address) {
		Customers customer = customerRepository.findById(customerId).orElse(null);
		if(customer!= null) {
			customer.setAddress(address);
			customerRepository.save(customer);
		}
	}

	@Override
	public Customers getCustomerRegionById(String customerId) {
		return customerRepository.findById(customerId).orElse(null);

	}

	@Override
	public void updateCustomerRegion(String customerId, String region) {
		Customers customer = customerRepository.findById(customerId).orElse(null);
		if(customer != null) {
			customer.setRegion(region);
			customerRepository.save(customer);
		}

	}
	@Override
	public List<String> getUniqueContactTitles() {
		return customerRepository.findByDistinctContactTitle();
	}



	public Customers assignAddress(String customerId, String address) throws IdDoesNotExistsException{

		Customers customer = customerRepository.findById(customerId).orElse(null);
		if (customer != null) {
			customer.setAddress(address);
			customerRepository.save(customer);
			return customer;
		} else {
			throw new IdDoesNotExistsException("Id Not Found!!");
		}
	}
	
	 @Override
	    public Optional<Customers> getCustomerContactNameById(String customerId) {
	        return customerRepository.findById(customerId);
	        
	    }

	    @Override
	    public void updateCustomerContactName(String customerId, String newContactName) throws CustomerNotExistsException {
	        
	        Optional<Customers> optionalCustomer = customerRepository.findById(customerId);

	        if (optionalCustomer.isPresent()) {
	            Customers customer = optionalCustomer.get();
	            customer.setContactName(newContactName);
	            customerRepository.save(customer);
	        } else {
	            throw new CustomerNotExistsException("Customer not found");
	        }

	    }
}