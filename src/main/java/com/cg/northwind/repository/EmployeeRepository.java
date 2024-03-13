package com.cg.northwind.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.northwind.entity.Employees;
@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer>{

	Employees findByFirstName(String firstName);

	List<Employees> findByTitle(String title);
	
	List<Employees> findByCity(String city);
	
	List<Employees> findByBirthDate(Date birthdate);
		
	 @Query("SELECT m.firstName, m.lastName, (SELECT COUNT(e) FROM Employees e WHERE e.reportsTo = m) FROM Employees m")
	 List<Object[]> countEmployeesByManager();

	 @Query("SELECT e.firstName, e.lastName, FUNCTION('TIMESTAMPDIFF', YEAR, e.birthDate, CURRENT_DATE()) " +
	            "FROM Employees e")
	    List<Object[]> getEmployeesWithAge(Date birthDate);

	    
	    
}


