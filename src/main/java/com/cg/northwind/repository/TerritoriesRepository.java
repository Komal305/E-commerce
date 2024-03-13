package com.cg.northwind.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cg.northwind.entity.Territories;

@Repository
public interface TerritoriesRepository extends JpaRepository<Territories, String> {
	
	Optional<Territories> findByTerritoryId(String territoryId);
}
	
	
