package com.cg.northwind.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Entity
@Table(name ="shippers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shippers {

	@Id
	@Column(name = "shipper_id", nullable = false)
	private int shipperId;
	
	@Column(name = "company_name", nullable = false)
	private String companyName;
	
	@Column(name = "Phone")
	private String Phone;
	
}
