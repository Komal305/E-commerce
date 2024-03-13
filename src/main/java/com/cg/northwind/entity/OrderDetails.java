package com.cg.northwind.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="order_details")
public class OrderDetails {
	@EmbeddedId
	@JsonIgnore
	private OrderDetailsId odi;
	
	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name="order_id")
	private Orders ords;
	
	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name="product_id")
	private Products product;
	
	@Column(name="unit_price",nullable=false)
	private double unitPrice;
	
	@Column(name="Quantity",nullable=false)
	private short quantity;
	
	@Column(name="Discount",nullable=false)
	private Double discount;

	public OrderDetailsId getOdi() {
		return odi;
	}

	public void setOdi(OrderDetailsId odi) {
		this.odi = odi;
	}

	 
	

	public Orders getOrds() {
		return ords;
	}

	public void setOrds(Orders ords) {
		this.ords = ords;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public short getQuantity() {
		return quantity;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public OrderDetails(OrderDetailsId odi, Orders ords, Products product, double unitPrice, short quantity,
			Double discount) {
		super();
		this.odi = odi;
		this.ords = ords;
		this.product = product;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.discount = discount;
	}
	

	@Override
	public String toString() {
		return "OrderDetails [odi=" + odi + ", ords=" + ords + ", product=" + product + ", unitPrice=" + unitPrice
				+ ", quantity=" + quantity + ", discount=" + discount + "]";
	}

	public OrderDetails() {
		super();
	}

	


	
}
