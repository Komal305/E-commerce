package com.cg.northwind.entity;
/*
 * @author
 * M V Shashank Yadav
 * 
 * Entity Class For Supplier
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "suppliers")
public class Suppliers {
	@Id
	@Column(name = "supplier_id")
	private int supplierID;
	@Column(name = "company_name", nullable = false)
	private String companyName;
	@Column(name = "contact_name")
	private String contactName;
	@Column(name = "contact_title")
	private String contactTitle;
	@Column(name = "Address")
	private String address;
	@Column(name = "City")
	private String city;
	@Column(name = "Region")
	private String region;
	@Column(name = "postal_code")
	private String postalCode;
	@Column(name = "Country")
	private String country;
	@Column(name = "Phone")
	private String phone;
	@Column(name = "Fax")
	private String fax;
	@Column(name = "home_page")
	private String homePage;
}
