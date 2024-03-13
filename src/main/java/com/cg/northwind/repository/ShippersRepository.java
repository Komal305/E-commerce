package com.cg.northwind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.northwind.entity.Shippers;

public interface ShippersRepository extends JpaRepository<Shippers, Integer>{
	
	List<Shippers> getByCompanyName(String companyName);
}
