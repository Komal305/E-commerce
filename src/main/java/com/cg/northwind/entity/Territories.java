package com.cg.northwind.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "territories")
public class Territories {

	@Id
	@Column(name = "TerritoryID",nullable = false)
	private String territoryId;
	
	@Column(name = "territory_description",nullable = false)
	private String territoryDescription;
	
	
	@ManyToOne
	@JoinColumn(name = "RegionID",nullable = false)
	private Region regionId;
	
	@ManyToMany(mappedBy="territories")
    @JsonIgnore
    private List<Employees> employee;
}
