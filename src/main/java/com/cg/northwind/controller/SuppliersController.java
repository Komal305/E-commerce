package com.cg.northwind.controller;
/*
 * @author 
 * M V Shashank Yadav
 * 
 * Supplier Controller
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.northwind.entity.Suppliers;
import com.cg.northwind.exception.IdAlreadyExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.service.SuppliersService;


@RestController
@RequestMapping("/api/supplier")
public class SuppliersController {

	@Autowired
	private SuppliersService suppliersService;

	/*
	 * Inserting Supplier and returns Supplier Object with Hateoas Link for Displaying all records
	 */
	@PostMapping("")
	public EntityModel<Suppliers> addSupplier(@RequestBody Suppliers suppliers) throws IdAlreadyExistsException {
		if (suppliersService.isSupplierIdPresent(suppliers.getSupplierID())) {
		throw new IdAlreadyExistsException("Validation failed. ID Already present.");
		}
		suppliersService.addSupplier(suppliers);
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllSupplier());
		return EntityModel.of(suppliers).add(link.withRel("Record Added Successfully!!  View All Products -->"));
	}

	/*
	 * Displaying all records in List
	 */
	@GetMapping("")
	public ResponseEntity<List<Suppliers>> getAllSupplier() {
		List<Suppliers> suppliers = suppliersService.getAllSuppliers();
		if (suppliers.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(suppliers);
	}
	
	/*
	 * updating all details of supplier by their Supplier ID
	 */
	@PutMapping("/edit/{id}")
	public EntityModel<Suppliers> updateBySupplierId(@RequestBody Suppliers suppliers, @PathVariable int id) throws IdAlreadyExistsException, IdDoesNotExistsException {
		suppliersService.findById(id);
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllSupplier());
		return EntityModel.of(suppliersService.updateBySupplierId(suppliers, id)).add(link.withRel("View All Products -->"));
	}
	
	/*
	 * Search by Country Name
	 */
	@GetMapping("/country/{country}")
	public ResponseEntity<List<Suppliers>> searchSuppliersByCountry(@PathVariable String country) {
		List<Suppliers> suppliers = suppliersService.searchSuppliersByCountry(country);
		if (suppliers.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(suppliers);
	}
	
	/*
	 * Display All Region which are not NULL values
	 */
	@GetMapping("/regionnotnull")
	public ResponseEntity<List<Suppliers>> searchSupplierRegionNotNull() {
		List<Suppliers> suppliers = suppliersService.getSuppliersRegionNotNull();
		if (suppliers.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(suppliers);
	}

	/*
	 * Search by Contact Title
	 */
	@GetMapping("/title/{Manager}")
	public ResponseEntity<List<Suppliers>> searchContactTitleManager(@PathVariable("Manager") String manager) {
		List<Suppliers> suppliers = suppliersService.searchContactTitleManager(manager);
		if (suppliers.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(suppliers);
	}

	/*
	 * Display number of supplier from each country 
	 */
	@GetMapping("/numberofsupplierbycountry")
	public ResponseEntity<List<Object[]>> getNumberOfSupplierFromEachCountry() {
		List<Object[]> suppliers = suppliersService.getNumberOfSupplierFromEachCountry();
		if (suppliers.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(suppliers);
	}

}
