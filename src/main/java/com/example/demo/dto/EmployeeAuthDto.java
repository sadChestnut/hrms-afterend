package com.example.demo.dto;

public class EmployeeAuthDto {

	private String empnum;

	private String empname;
	
	private String phone;
	
	private String email;
	
	private String identity;
	
	private int auth;
	
	public String getEmpnum() {
		return this.empnum;
	}

	public void setEmpnum(String empnum) {
		this.empnum = empnum;
	}
	
	public String getEmpname() {
		return this.empname;
	}
	
	public void setEmpname(String name) {
		this.empname = name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getIdentity() {
		return this.identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public int getAuth() {
		return this.auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}
	
	public EmployeeAuthDto() {}
	
	public EmployeeAuthDto(String id, String name, String identity, String phone, String email, int auth) {
		this.empnum = id;
		this.empname = name;
		this.identity = identity;
		this.phone = phone;
		this.email = email;
		this.auth = auth;
	}
}
