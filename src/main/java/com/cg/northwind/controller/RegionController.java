package com.cg.northwind.controller;

import com.cg.northwind.entity.Region;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.service.RegionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/region")
public class RegionController {
	private final RegionService regionService;

	@Autowired
	public RegionController(RegionService regionService) {
		this.regionService = regionService;
	}

	@GetMapping("")
	public List<Region> getAllRegions() {
		return regionService.getAllRegions();
	}

	@PostMapping("")
	public ResponseEntity<String> addRegion(@RequestBody Region region) {
		Region newRegion = regionService.addRegion(region);
		if (newRegion != null) {
			return ResponseEntity.ok("Record Created Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Validation failed ...");
		}
	}

	@GetMapping("/regionid/{regionid}")
	public ResponseEntity<Region> getRegionById(@PathVariable int regionid) {
		Region region = regionService.getRegionById(regionid);
		if (region == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(region);
	}

	@PutMapping("/edit/{regionId}")
	public ResponseEntity<Region> updateRegionById(@PathVariable Integer regionId, @RequestBody Region updatedRegion)
			throws IdDoesNotExistsException {
		regionService.updateRegionById(regionId, updatedRegion);
		return ResponseEntity.noContent().build();
	}
}
