package com.cg.northwind.repository;

import java.util.List;
import java.util.Set;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.northwind.entity.Customers;



@Repository
public interface CustomerRepository extends JpaRepository<Customers, String> {
	
	
	List<Customers> findByCompanyName(String companyName);
	
    List<Customers> findByCountry(String country);
    
    List<Customers> findByCity(String city);
    
    List<Customers> findByRegion(String region);
    
    List<Customers> findByFax(String fax);
    
    List<Customers> findByRegionNotNull();
    
    List<Customers> findAllByContactTitleOrderByContactTitleDesc(String contactTitle);
    
    @Query("SELECT c.country, COUNT(c) FROM Customers c GROUP BY c.country")
    
    List<Object[]> countCustomersByCountry();
    
    @Query("select distinct c.contactTitle from Customers c") 
    
    List<String> findByDistinctContactTitle();
}