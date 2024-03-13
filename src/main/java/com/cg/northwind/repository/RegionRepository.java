package com.cg.northwind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.northwind.entity.Region;


@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
	
List<Region> findRegionByRegionId(Integer regionId);
Region save(Integer regionid);
}

    
