package com.cg.northwind.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customers {
	@Id
	@Column(name = "customer_id", nullable = false)
	private String customerID;

	@Column(name = "company_name", nullable = false)
	private String companyName;

	@Column(name = "contact_name")
	private String contactName;

	@Column(name = "contact_title")
	private String contactTitle;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "region")
	private String region;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "country")
	private String country;

	@Column(name = "phone")
	private String phone;

	@Column(name = "fax")
	private String fax;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "customer_customer_demo", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "customer_typeid"))
	private List<CustomerDemographics> customerDemographics;
}
