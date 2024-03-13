package com.cg.northwind.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.northwind.entity.Customers;
import com.cg.northwind.exception.CustomerNotExistsException;
import com.cg.northwind.exception.CustomerNotfoundException;
import com.cg.northwind.exception.IdAlreadyExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.service.CustomerService;
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	@Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    // get all customers
    @GetMapping("")
    public ResponseEntity<List<Customers>> getAllCustomers() {
        List<Customers> customers = customerService.getAllCustomers();
        
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    // by company
    @GetMapping("/companyname/{companyname}")
	public ResponseEntity<List<Customers>> searchCustomersByCompanyName(@PathVariable String companyname){
		if(customerService.getCustomersByCompanyName(companyname)==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(customerService.getCustomersByCompanyName(companyname));
		
	}
    
    //by contact-title
    
    @GetMapping("/contacttitle/{contacttitle}")
	public ResponseEntity<List<Customers>> searchCustomersByContactTitle(@PathVariable String contacttitle){
    	List<Customers> title=customerService.getCustomersByContactTitle(contacttitle);
    	if(title.isEmpty())
    	{
    		return ResponseEntity.notFound().build();
    	}
    
		return ResponseEntity.ok(title);
    }
    
    
    //by country
    @GetMapping("/country/{country}")
    public ResponseEntity<List<Customers>> searchCustomerByCountry(@PathVariable String country){
    	if(customerService.getCustomerByCountry(country) == null)
    		return ResponseEntity.notFound().build();
    	return ResponseEntity.ok(customerService.getCustomerByCountry(country));
    }
    
    
    // by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Customers>> searchCustomerByCity(@PathVariable String city){
    	if(customerService.getCustomerByCity(city) == null)
    		return ResponseEntity.notFound().build();
    	return ResponseEntity.ok(customerService.getCustomerByCity(city));
    }
    
    
    
    //by region
    @GetMapping("/region/{region}")
    public ResponseEntity<List<Customers>> searchCustomerByRegion(@PathVariable String region){
    	if(customerService.getCustomerByRegion(region)==null)
    		return ResponseEntity.notFound().build();
    	return ResponseEntity.ok(customerService.getCustomerByRegion(region));
    }
    
    //by fax
    @GetMapping("/fax/{fax}")
    public ResponseEntity<List<Customers>> searchCustomerByFax(@PathVariable String fax){
    	if(customerService.getCustomerByFax(fax)== null)
    		return ResponseEntity.notFound().build();
    	return ResponseEntity.ok(customerService.getCustomerByFax(fax));
    }
    
    
    // by region who has region not null
    @GetMapping("/regionnotnull")
    public ResponseEntity<List<Customers>> searchCustomersWithNonNullRegion() {
        List<Customers> customers = customerService.getCustomersWithNotNullRegion();
     
   	 return ResponseEntity.ok(customers);

    }
    
    
    // number of customers by country
    @GetMapping("/numberofcustomersbycountry")
    public ResponseEntity<List<Object[]>> getNumberOfCustomersByCountry() {
        List<Object[]> result = customerService.getCountOfCustomersByCountry();
        return ResponseEntity.ok(result);
    }
    

    // adding customer
    @PostMapping("")
    public ResponseEntity<String> addCustomer(@RequestBody Customers customer) throws IdAlreadyExistsException {
        customerService.createCustomer(customer);
            return  ResponseEntity.ok("Record Created Successfully");
        
    }
    
    
    // changing the address of the customer by using customer id
    @PutMapping("/edit/customeraddress/{customerid}")
    public ResponseEntity<String> assignAddressToCustomer(
            @PathVariable("customerid") String customerId,
            @RequestBody String address) throws CustomerNotfoundException {
        	customerService.getCustomerById(customerId);
        	customerService.assignAddressToCustomer(customerId, address);
        	return ResponseEntity.ok("Address assigned to the customer successfully.");
    }
//    update the customer id 
//    @PutMapping("/edit/customeraddress/{customerid}")
//    public ResponseEntity<Customers> assignAddress(@PathVariable("customerid") String customerId,@RequestBody String address) throws IdDoesNotExistsException {
//       
//            Customers addressAssigned = customerService.assignAddress(customerId, address);
//
//            if (addressAssigned!=null) {
//                return new ResponseEntity<Customers>(addressAssigned, HttpStatus.OK);
//            
//    }else {
//    	return new ResponseEntity<Customers>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    } 
    
   
    // updating the region of the customer by using customer id 
    @PatchMapping("/edit/customerregion/{customerid}")
    public ResponseEntity<Void> updateCustomerRegion(
            @PathVariable("customerid") String customerId,
            @RequestBody String region) throws CustomerNotfoundException {

        Customers customer = customerService.getCustomerRegionById(customerId);
        if (customer == null) {
            throw new CustomerNotfoundException("Customer ID not present");
        }

        customerService.updateCustomerRegion(customerId, region);

        String outputMessage = "Contact name changed";
        System.out.println(outputMessage);

        return ResponseEntity.noContent().build();
    }
    
    // separation of unique contact title
    @GetMapping("/uniquecontacttitle")
    public ResponseEntity<List<String>> getUniqueContactTitles() {
        List<String> uniqueContactTitles = customerService.getUniqueContactTitles();
        return new ResponseEntity<>(uniqueContactTitles, HttpStatus.OK);
    }
    
    
    
    // update the customer id 
    @PutMapping("/edit/contactname/{customerId}")
    public ResponseEntity<String> updateCustomerContactName(
            @PathVariable("customerId") String customerId,
            @RequestBody String newContactName
    ) throws CustomerNotExistsException {
        customerService.updateCustomerContactName(customerId, newContactName);
        String successMessage = "Contact name changed";
        return ResponseEntity.ok(successMessage);
    }

}
