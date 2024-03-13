package com.cg.northwind.controller;
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
import com.cg.northwind.entity.Territories;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.exception.TerritoryNotfoundexception;
import com.cg.northwind.service.TerritoriesService;

import java.util.List;

@RestController
@RequestMapping("/api/territory")
public class TerritoriesController {
    private  TerritoriesService territoriesService;

    @Autowired
    public TerritoriesController(TerritoriesService territoriesService) {
        this.territoriesService = territoriesService;
    }

    @GetMapping("")
    public ResponseEntity<List<Territories>> getAllTerritories() {
        List<Territories> territories = territoriesService.getAllTerritories();

        if (territories != null && !territories.isEmpty()) {
            return new ResponseEntity<>(territories, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("")
    public ResponseEntity<String> addTerritory(@RequestBody Territories territory) {
        Territories newTerritory = territoriesService.addTerritory(territory);
        if (newTerritory != null) {
            return ResponseEntity.ok("Record Created Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed ...");
        }
    }
    
    @PutMapping("/edit/{territoryid}")
    public ResponseEntity<Territories> updateTerritoryById(@PathVariable String territoryid, @RequestBody Territories updatedTerritory) throws TerritoryNotfoundexception, IdDoesNotExistsException {

            return ResponseEntity.ok(territoriesService.updateTerritoryById(territoryid, updatedTerritory));
    }

    
    @GetMapping("/territoryid/{territoryId}")
    public ResponseEntity<Territories> getTerritoryById(@PathVariable String territoryId) {
        Territories territory = territoriesService.getTerritoryById(territoryId);

        if (territory != null) {
            return new ResponseEntity<>(territory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

