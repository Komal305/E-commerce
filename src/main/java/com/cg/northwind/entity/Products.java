package com.cg.northwind.entity;
/*
 * @author M V Shashank Yadav
 */

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Products {
	@Id
	@Column(name = "product_id")
	private int productID;
	@Column(name = "product_name", nullable = false)
	private String productName;
	
	@ManyToOne
    @JoinColumn(name = "supplier_id")
	private Suppliers suppliers;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Categories categories;
	
	@Column(name = "quantity_per_unit")
	private String quantityPerUnit;
	@Column(name = "unit_price")
	private double unitPrice;
	@Column(name = "unit_in_stock")
	private int unitsInStock;
	@Column(name = "unit_on_order")
	private int unitsOnOrder;
	@Column(name = "reorder_level")
	private int reorderLevel;
	@Column(name = "Discontinued", nullable = false)
	private boolean discontinued;
	
	@ManyToMany(mappedBy = "products")
	@JsonIgnore
	List<Orders> order;
	
}

