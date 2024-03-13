package com.cg.northwind.entity;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Orders {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id", nullable = false)
	private int orderID;
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customers customers;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private Employees employees;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name = "required_date")
	private Date requiredDate;
	
	@Column(name = "shipped_date")
	private Date shippedDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ship_via")
	private Shippers shipVia;
	
	@Column(name = "Freight")
	private double freight;
	
	@Column(name = "ship_name")
	private String shipName;
	
	@Column(name = "ship_address")
	private String shipAddress;
	
	@Column(name = "ship_city")
	private String shipCity;
	
	@Column(name = "ship_region")
	private String shipRegion;
	
	@Column(name = "ship_postal_code")
	private String shipPostalCode;
	
	@Column(name = "ship_country")
	private String shipCountry;
	
	@ManyToMany
    @JoinTable(name = "order_details",joinColumns = @JoinColumn(name = "order_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonIgnore
    private List<Products> products;
	
}
