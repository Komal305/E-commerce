package com.cg.northwind.entity;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "region")
public class Region {
	
	@Id
	@Column(name = "RegionID",nullable = false)
	private Integer regionId;
	
	
	
	@Column(name = "region_description",nullable = false)
	private String region_description;
	
	
	
}

