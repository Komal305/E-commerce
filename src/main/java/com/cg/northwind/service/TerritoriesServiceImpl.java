package com.cg.northwind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.northwind.entity.Territories;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.repository.TerritoriesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TerritoriesServiceImpl implements TerritoriesService {
    private final TerritoriesRepository territoriesRepository;

    @Autowired
    public TerritoriesServiceImpl(TerritoriesRepository territoriesRepository) {
        this.territoriesRepository = territoriesRepository;
    }

    @Override
    public List<Territories> getAllTerritories() {
        return territoriesRepository.findAll();
    }
    
    @Override
    public Territories addTerritory(Territories territory) {
        
        return territoriesRepository.save(territory);
    }
    
    
    @Override
    public Territories updateTerritoryById(String territoryid, Territories updatedTerritory) throws IdDoesNotExistsException {
        Territories territories=territoriesRepository.findById(territoryid).orElseThrow(()-> new IdDoesNotExistsException("Id Doesnt Exist"));
        territories.setTerritoryDescription(updatedTerritory.getTerritoryDescription());
        return territoriesRepository.save(territories);

    }
    

    @Override
    public Territories getTerritoryById(String territoryId) {
        return territoriesRepository.findById(territoryId).orElse(null);
    }

}


