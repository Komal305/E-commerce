package com.cg.northwind.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.northwind.entity.Shippers;
import com.cg.northwind.exception.NoContentException;
import com.cg.northwind.exception.ShipperIdNotFoundException;
import com.cg.northwind.exception.ShippersAlreadyExistsException;
import com.cg.northwind.service.ShippersService;


@RestController
@RequestMapping("/api")
public class ShippersController {

	@Autowired
	private ShippersService service;
	
	@GetMapping("/shipper/")
	public ResponseEntity<List<Shippers>> listAllShippers() throws NoContentException
	{
		List<Shippers> allShippers = service.getAllShippers();
		if(allShippers.isEmpty()) {
			throw new NoContentException("There is no such data");
		}
		return ResponseEntity.ok(allShippers);
	}
	
	@GetMapping("/shipper/{companyName}")
	public ResponseEntity<List<Shippers>> shippersDetails(@PathVariable String companyName) throws NoContentException{
		List<Shippers> shipperByName = service.findByCompanyName(companyName);
		if(shipperByName.isEmpty()) {
			throw new NoContentException("There is no such data");
		}
		return ResponseEntity.ok(shipperByName);
	}
	
	@PutMapping("/shipper/edit/{shipperId}")
	public EntityModel<Shippers> updateShippers(@PathVariable int shipperId, @RequestBody Shippers shippers) throws ShipperIdNotFoundException, NoContentException{
		Shippers updateShipper = service.updateByShippersId(shippers, shipperId);
		EntityModel<Shippers> entityModel = EntityModel.of(updateShipper);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listAllShippers());
		entityModel.add(link.withRel("all-shippers"));
		
		return entityModel;
	}
	
	@PostMapping("/shipper/")
	public EntityModel<Shippers> addShippersDetails(@RequestBody Shippers shippers)throws ShippersAlreadyExistsException, ShipperIdNotFoundException, NoContentException{
		Shippers addedShippersDetails = service.addShippers(shippers);
		if(addedShippersDetails != null) {
			EntityModel<Shippers> entityModel = EntityModel.of(addedShippersDetails);
			WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listAllShippers());
			entityModel.add(link.withRel("all-shippers"));
			return entityModel;
		}
		throw new ShippersAlreadyExistsException("Shippers data which is inserting is present already");
	}
	
	@PatchMapping("/edit/{shipperId}")
    public ResponseEntity<Void> updateShipperDetails(@PathVariable int shipperId, @RequestBody Shippers updatedShipper) throws NoContentException {
        boolean isUpdated = service.updateShipper(shipperId, updatedShipper);
        if (isUpdated) {
        	throw new NoContentException("Record Added Successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
