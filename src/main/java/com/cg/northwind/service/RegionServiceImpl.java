package com.cg.northwind.service;

import com.cg.northwind.entity.Region;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.exception.RegionNotfoundException;
import com.cg.northwind.repository.RegionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Region addRegion(Region region) {
        return regionRepository.save(region);
    }
    
    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }
    
    
    @Override
    public Region getRegionById(int regionId) {
        return regionRepository.findById(regionId).orElse(null);
    }
    
    
    @Override
    public Region updateRegionById(Integer regionId, Region updatedRegion) throws IdDoesNotExistsException {
    	Region region=regionRepository.findById(regionId).orElseThrow(()->new IdDoesNotExistsException("region is not found"));
    	region.setRegion_description(updatedRegion.getRegion_description());
    	return region;
    }
}
