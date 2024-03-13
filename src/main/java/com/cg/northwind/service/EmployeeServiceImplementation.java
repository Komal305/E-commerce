package com.cg.northwind.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.northwind.repository.EmployeeRepository;
import com.cg.northwind.DTO.EmployeeManagerDTO;
import com.cg.northwind.DTO.EmployeeTerritoryRegionDTO;
import com.cg.northwind.entity.Employees;
import com.cg.northwind.entity.Territories;
import com.cg.northwind.exception.IdAlreadyExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

	@Autowired
	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employees> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employees getByFirstName(String firstName) {
		return employeeRepository.findByFirstName(firstName);

	}

	@Override
	public List<Employees> getByTitle(String title) {

		return employeeRepository.findByTitle(title);
	}

	@Override
	public List<Employees> getEmployeeByCity(String city) {

		return employeeRepository.findByCity(city);
	}

	@Override
	public List<Object[]> getCountOfEmployeesUnderManager() {

		return employeeRepository.countEmployeesByManager();
	}

	@Override
	public Employees addNewEmployee(Employees employee) throws IdAlreadyExistsException {

		if (employeeRepository.existsById(employee.getEmployeeId())) {
			throw new IdAlreadyExistsException("Employee Already Exists");
		}
		return employeeRepository.save(employee);
	}

	@Override
	public Employees updateEmployeeDetailsById(Employees employee, int employeeId) {
		Optional<Employees> update = employeeRepository.findById(employeeId);

		Employees employees = update.get();
		employees.setEmployeeId(employee.getEmployeeId());
		employees.setFirstName(employee.getFirstName());
		employees.setLastName(employee.getLastName());
		employees.setTitle(employee.getTitle());
		employees.setTitleOfCourtesy(employee.getTitleOfCourtesy());
		employees.setBirthDate(employee.getBirthDate());
		employees.setHireDate(employee.getHireDate());
		employees.setCity(employee.getCity());
		employees.setCountry(employee.getCountry());
		employees.setAddress(employee.getAddress());
		employees.setExtension(employee.getExtension());
		employees.setPhoto(employee.getPhoto());
		employees.setHomePhone(employee.getHomePhone());
		employees.setRegion(employee.getRegion());
		employees.setPostalCode(employee.getPostalCode());
		employees.setPhotoPath(employee.getPhotoPath());
		employees.setReportsTo(employee.getReportsTo());

		return employeeRepository.save(employees);
	}

	@Override
	public List<Employees> getEmployeeDetailsByBirthDate(Date birthdate) {

		return employeeRepository.findByBirthDate(birthdate);
	}

	@Override
	public boolean updateEmployeeDetails(int employeeId, Employees updating) throws IdDoesNotExistsException {
		Employees employee = employeeRepository.findById(employeeId).orElse(null);
		if (employee != null) {
			employee.setCity(updating.getCity());
			employeeRepository.save(employee);
			return true;

		} else {
			throw new IdDoesNotExistsException("Employee Id Not Found :" + employeeId);
		}

	}

	@Override
	public List<Object[]> getDetailsOfEmployeeByBirthDate(Date birthDate) {
		return employeeRepository.getEmployeesWithAge(birthDate);
	}

	@Override
	public List<EmployeeTerritoryRegionDTO> getEmployeeTerritoryRegionDetails() {
		List<Employees> employees = employeeRepository.findAll();
		List<EmployeeTerritoryRegionDTO> employeeDTOs = new ArrayList<>();

		for (Employees employee : employees) {
			for (Territories territory : employee.getTerritories()) {
				EmployeeTerritoryRegionDTO employeeDTO = new EmployeeTerritoryRegionDTO();
				employeeDTO.setFirstName(employee.getFirstName());
				employeeDTO.setTerritoryDescription(territory.getTerritoryDescription());
				employeeDTO.setRegionDescription(territory.getRegionId().getRegion_description());
				employeeDTOs.add(employeeDTO);
			}
		}

		return employeeDTOs;
	}

	public List<EmployeeManagerDTO> getEmployeeUnderManager() {
		List<Employees> employee = employeeRepository.findAll();
		List<EmployeeManagerDTO> empMangerDTO = new ArrayList<>();
		for (Employees emp : employee) {
			EmployeeManagerDTO empDTO = new EmployeeManagerDTO();
			empDTO.setEmployeeName(emp.getFirstName() + " " + emp.getLastName());

			if (emp.getReportsTo() != null) {
				empDTO.setManagerName(emp.getReportsTo().getFirstName() + " " + emp.getReportsTo().getLastName());

			} else {
				empDTO.setManagerName("Not Reporting To Anyone");
			}
			empMangerDTO.add(empDTO);
		}

		return empMangerDTO;

	}
}
