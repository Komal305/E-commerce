package com.cg.northwind.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

import com.cg.northwind.service.EmployeeService;
import com.cg.northwind.DTO.EmployeeManagerDTO;
import com.cg.northwind.DTO.EmployeeTerritoryRegionDTO;
import com.cg.northwind.entity.Employees;
import com.cg.northwind.exception.EmployeeDoesNotExist;
import com.cg.northwind.exception.IdAlreadyExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empservice;
	
	//Get All Employee Details
	@GetMapping("")
	public ResponseEntity<List<Employees>> getAllEmployees() {
		List<Employees> empList = empservice.getAllEmployees();
		if (empList == null) {
			return new ResponseEntity<>(empList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	//Get Employee Details By FirstName
	@GetMapping("/firstname/{firstName}")
	public ResponseEntity<Employees> getByFirstName(@PathVariable String firstName) throws EmployeeDoesNotExist {
		Employees findByName = empservice.getByFirstName(firstName);
		if (findByName != null) {
			return ResponseEntity.ok(findByName);
		}
		throw new EmployeeDoesNotExist("Employee Does Not Exist ");
	}

	//Get Employee Details By Title
	@GetMapping("/title/{title}")
	public ResponseEntity<List<Employees>> getByTitle(@PathVariable String title) throws EmployeeDoesNotExist {
		List<Employees> bytitle = empservice.getByTitle(title);
		if (bytitle.isEmpty()) {
			throw new EmployeeDoesNotExist("Employee Title Does Not Exist");
		}
		return ResponseEntity.ok(bytitle);
	}

	//Get Employee Details By City
	@GetMapping("/city/{city}")
	public ResponseEntity<List<Employees>> getEmployeeByCity(@PathVariable String city) throws EmployeeDoesNotExist {
		List<Employees> cityname = empservice.getEmployeeByCity(city);
		if (cityname.isEmpty()) {
			throw new EmployeeDoesNotExist("Employee City Does Not Exist ");
		}
		return ResponseEntity.ok(cityname);
	}

	//Get Manager And Employee Count
	@GetMapping("/managerandemployeecount")
	public ResponseEntity<List<Object[]>> getCountOfEmployeesUnderManager() {
		List<Object[]> count = empservice.getCountOfEmployeesUnderManager();
		return ResponseEntity.ok(count);
	}

	// Adding New Employee
	@PostMapping("/addemployee")
	public ResponseEntity<String> addNewEmployee(@RequestBody Employees employee) throws IdAlreadyExistsException {
		empservice.addNewEmployee(employee);

		return ResponseEntity.ok("Record Stored Successfully");

	}

	//Updating Existing Employee Details
	@PutMapping("/update/{employeeid}")
	public ResponseEntity<Employees> updateEmployeeDetailsById(@RequestBody Employees employee,
			@PathVariable("employeeid") int employeeId) {
		empservice.updateEmployeeDetailsById(employee, employeeId);

		return ResponseEntity.noContent().build();

	}

	//Get Employee Details By BirthDate
	@GetMapping("/birthdate/{birthdate}")
	public ResponseEntity<List<Employees>> getEmployeeDetailsByBirthDate(@PathVariable("birthdate") LocalDate birthdate)
			throws EmployeeDoesNotExist {
		Date convertedbirthDate = Date.from(birthdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<Employees> birth = empservice.getEmployeeDetailsByBirthDate(convertedbirthDate);

		if (birth.isEmpty()) {
			throw new EmployeeDoesNotExist("Employee BirthDate Does Not Exist");
		}
		return ResponseEntity.ok(birth);
	}

	//Editing Employee Particular Details By EmployeeId
	@PatchMapping("/edit/{employeeid}")
	public ResponseEntity<Employees> updateEmployeeDetails(@PathVariable("employeeid") int employeeId,
			@RequestBody Employees city) throws IdDoesNotExistsException {
		empservice.updateEmployeeDetails(employeeId, city);

		return ResponseEntity.noContent().build();

	}

	//Get Employee Details By BirthDate And Calculating The Age Of Employee
	@GetMapping("/age/birthdate/{birthDate}")
	public ResponseEntity<List<Object[]>> getDetailsOfEmployeeByBirthDate(
			@PathVariable("birthDate") LocalDate birthDate) throws EmployeeDoesNotExist {
		Date convertage = Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<Object[]> empl = empservice.getDetailsOfEmployeeByBirthDate(convertage);
		if (empl.isEmpty()) {
			throw new EmployeeDoesNotExist("Employee BirthDate Does Not Exist");
		}
		return ResponseEntity.ok(empl);

	}

	//Get Manager And EmployeeName
	@GetMapping("/managerandemployeename")
	public ResponseEntity<List<EmployeeManagerDTO>> getEmployeeUnderManager() {
		List<EmployeeManagerDTO> empManger = empservice.getEmployeeUnderManager();
		return ResponseEntity.ok(empManger);
	}

	//Get EmployeeName Territory And Region
	@GetMapping("/employeeterritoriesregion")
	public ResponseEntity<List<EmployeeTerritoryRegionDTO>> getEmployeeDetails() {
		List<EmployeeTerritoryRegionDTO> employeeTerritoryRegionDetails = empservice
				.getEmployeeTerritoryRegionDetails();
		return ResponseEntity.ok(employeeTerritoryRegionDetails);
	}

}
