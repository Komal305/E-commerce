package com.cg.northwind.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "employees")
public class Employees {
	@Id
	@Column(name = "employee_id", nullable = false)
	private int employeeId;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "title")
	private String title;

	@Column(name = "title_of_courtesy")
	private String titleOfCourtesy;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "hire_date")
	private Date hireDate;

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

	@Column(name = "home_phone")
	private String homePhone;

	@Column(name = "extension")
	private String extension;

	@Column(name = "photo")
	private String photo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reports_to")
	private Employees reportsTo;

	@Column(name = "photo_path")
	private String photoPath;

	@ManyToMany
	@JoinTable(name = "employeeterritories", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "TerritoryID"))
	@JsonIgnore
	private List<Territories> territories;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleOfCourtesy() {
		return titleOfCourtesy;
	}

	public void setTitleOfCourtesy(String titleOfCourtesy) {
		this.titleOfCourtesy = titleOfCourtesy;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Employees getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(Employees reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	

	public List<Territories> getTerritories() {
		return territories;
	}

	public void setTerritories(List<Territories> territories) {
		this.territories = territories;
	}

	public Employees(int employeeId, String lastName, String firstName, String title, String titleOfCourtesy,
			Date birthDate, Date hireDate, String address, String city, String region, String postalCode,
			String country, String homePhone, String extension, String photo, Employees reportsTo, String photoPath) {
		super();
		this.employeeId = employeeId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.title = title;
		this.titleOfCourtesy = titleOfCourtesy;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
		this.address = address;
		this.city = city;
		this.region = region;
		this.postalCode = postalCode;
		this.country = country;
		this.homePhone = homePhone;
		this.extension = extension;
		this.photo = photo;
		this.reportsTo = reportsTo;
		this.photoPath = photoPath;
	}

	@Override
	public String toString() {
		return "Employees [employeeId=" + employeeId + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", title=" + title + ", titleOfCourtesy=" + titleOfCourtesy + ", birthDate=" + birthDate
				+ ", hireDate=" + hireDate + ", address=" + address + ", city=" + city + ", region=" + region
				+ ", postalCode=" + postalCode + ", country=" + country + ", homePhone=" + homePhone + ", extension="
				+ extension + ", photo=" + photo + ", reportsTo=" + reportsTo + ", photoPath=" + photoPath + "]";
	}

	public Employees() {
		super();

	}
	
	

}
