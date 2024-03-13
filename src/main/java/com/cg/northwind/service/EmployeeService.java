package com.cg.northwind.service;

import java.util.Date;
import java.util.List;

import com.cg.northwind.DTO.EmployeeManagerDTO;
import com.cg.northwind.DTO.EmployeeTerritoryRegionDTO;
import com.cg.northwind.entity.Employees;
import com.cg.northwind.exception.IdAlreadyExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;

public interface EmployeeService {

	List<Employees> getAllEmployees();

	Employees getByFirstName(String firstName);

	List<Employees> getByTitle(String title);

	List<Employees> getEmployeeByCity(String city);

	List<Object[]> getCountOfEmployeesUnderManager();

	public Employees addNewEmployee(Employees employee) throws IdAlreadyExistsException;

	public Employees updateEmployeeDetailsById(Employees employee, int employeeId);

	List<Employees> getEmployeeDetailsByBirthDate(Date birthdate);

	List<Object[]> getDetailsOfEmployeeByBirthDate(Date birthDate);

	public List<EmployeeManagerDTO> getEmployeeUnderManager();

	List<EmployeeTerritoryRegionDTO> getEmployeeTerritoryRegionDetails();

	boolean updateEmployeeDetails(int employeeId, Employees updating) throws IdDoesNotExistsException;

}
