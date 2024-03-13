package com.cg.northwind.repository;
/*
 * @author 
 * M V Shashank Yadav
 * 
 * Supplier Repository
 * 
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.northwind.entity.Suppliers;

public interface SuppliersRepository extends JpaRepository<Suppliers, Integer> {
	List<Suppliers> findByCountryIgnoreCase(String country);

	List<Suppliers> findByRegionNotNull();

//	@Query(value = "select * from Suppliers where lower(contact_title) like %:title%" ,nativeQuery = true)
//	List<Suppliers> findByContactTitleManagerIgnoreCase(String title);

	List<Suppliers> findByContactTitleContainingIgnoreCase(String title);

	@Query("select s.country,count(*) from Suppliers s group by s.country")
	List<Object[]> countSupplierByCountry();
}
