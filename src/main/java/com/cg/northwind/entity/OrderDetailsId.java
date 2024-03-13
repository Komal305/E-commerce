package com.cg.northwind.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderDetailsId implements Serializable {
	@Column(name = "order_id")
	private int orderID;

	@Column(name = "product_id")
	private int productID;

	public int getOrderId() {
		return orderID;
	}

	public void setOrderId(int orderID) {
		this.orderID = orderID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderID, productID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsId other = (OrderDetailsId) obj;
		return Objects.equals(orderID, other.orderID) && Objects.equals(productID, other.productID);
	}

	public OrderDetailsId(int orderID, int productId2) {
		super();
		this.orderID = orderID;
		this.productID = productId2;
	}

	public OrderDetailsId() {
		super();
	}

	@Override
	public String toString() {
		return "OrderDetailsId [orderId=" + orderID + ", productID=" + productID + "]";
	}
}
