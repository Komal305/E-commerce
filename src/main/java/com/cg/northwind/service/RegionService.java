package com.cg.northwind.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.northwind.entity.Region;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.exception.RegionNotfoundException;


@Service

public interface RegionService {
	
    Region addRegion(Region region);
    
    List<Region> getAllRegions();
    
    
    Region getRegionById(int regionId);

	Region updateRegionById(Integer regionId, Region updatedRegion) throws IdDoesNotExistsException;
    
}
