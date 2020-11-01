package com.example.demo.vo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "employee_project")
public class employeeProject implements Serializable{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "empnum")
	private String empnum;
	
	@Column(name = "proname")
	private String proname;
	
	@Column(name = "prodetail")
	private String prodetail;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "rolecontribution")
	private String rolecontribution;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmpnum() {
		return this.empnum;
	}

	public void setEmpnum(String empnum) {
		this.empnum = empnum;
	}
	
	public String getProname() {
		return this.proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}
	
	public String getProdetail() {
		return this.prodetail;
	}

	public void setProdetail(String prodetail) {
		this.prodetail = prodetail;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRolecontribution() {
		return this.rolecontribution;
	}
	
	public void setRolecontribution(String rolecontribution) {
		this.rolecontribution = rolecontribution;
	}
	
	public employeeProject() {}
	
	public employeeProject(String empnum, String proname, String prodetail, String role, String rolecontribution) {
		this.empnum = empnum;
		this.proname = proname;
		this.prodetail = prodetail;
		this.role = role;
		this.rolecontribution = rolecontribution;
	}
}
