package com.cg.northwind.service;

import java.util.List;
import java.util.Optional;

import com.cg.northwind.entity.Territories;
import com.cg.northwind.exception.IdDoesNotExistsException;

public interface TerritoriesService {
	
    List<Territories> getAllTerritories();
    
    Territories addTerritory(Territories territory);
    
    
    Territories updateTerritoryById(String territoryid, Territories updatedTerritory) throws IdDoesNotExistsException;
    

    Territories getTerritoryById(String territoryId);
}


