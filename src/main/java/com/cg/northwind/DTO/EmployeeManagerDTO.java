package com.cg.northwind.DTO;

public class EmployeeManagerDTO {

	private String employeeName;
	private String managerName;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public EmployeeManagerDTO(String employeeName, String managerName) {
		super();
		this.employeeName = employeeName;
		this.managerName = managerName;
	}

	@Override
	public String toString() {
		return "EmployeeManagerDTO [employeeName=" + employeeName + ", managerName=" + managerName + "]";
	}

	public EmployeeManagerDTO() {
		super();

	}

}
